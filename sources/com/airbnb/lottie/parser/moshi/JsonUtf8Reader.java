package com.airbnb.lottie.parser.moshi;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.EOFException;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

final class JsonUtf8Reader extends JsonReader {
    private static final ByteString CLOSING_BLOCK_COMMENT = ByteString.encodeUtf8("*/");
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("\"\\");
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = ByteString.encodeUtf8("\n\r");
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_BUFFERED_NAME = 15;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 18;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 16;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 17;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private static final ByteString SINGLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("'\\");
    private static final ByteString UNQUOTED_STRING_TERMINALS = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private final Buffer buffer;
    private int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final BufferedSource source;

    JsonUtf8Reader(BufferedSource source2) {
        if (source2 != null) {
            this.source = source2;
            this.buffer = source2.buffer();
            pushScope(6);
            return;
        }
        throw new NullPointerException("source == null");
    }

    public void beginArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 3) {
            pushScope(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    public void endArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPath());
    }

    public void beginObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 1) {
            pushScope(3);
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    public void endObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 2) {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPath());
    }

    public boolean hasNext() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        return (p == 2 || p == 4 || p == 18) ? false : true;
    }

    public JsonReader.Token peek() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        switch (p) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private int doPeek() throws IOException {
        int peekStack = this.scopes[this.stackSize - 1];
        if (peekStack == 1) {
            this.scopes[this.stackSize - 1] = 2;
        } else if (peekStack == 2) {
            int c = nextNonWhitespace(true);
            this.buffer.readByte();
            switch (c) {
                case 44:
                    break;
                case 59:
                    checkLenient();
                    break;
                case 93:
                    this.peeked = 4;
                    return 4;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else if (peekStack == 3 || peekStack == 5) {
            this.scopes[this.stackSize - 1] = 4;
            if (peekStack == 5) {
                int c2 = nextNonWhitespace(true);
                this.buffer.readByte();
                switch (c2) {
                    case 44:
                        break;
                    case 59:
                        checkLenient();
                        break;
                    case 125:
                        this.peeked = 2;
                        return 2;
                    default:
                        throw syntaxError("Unterminated object");
                }
            }
            int c3 = nextNonWhitespace(true);
            switch (c3) {
                case 34:
                    this.buffer.readByte();
                    this.peeked = 13;
                    return 13;
                case 39:
                    this.buffer.readByte();
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                case 125:
                    if (peekStack != 5) {
                        this.buffer.readByte();
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                default:
                    checkLenient();
                    if (isLiteral((char) c3)) {
                        this.peeked = 14;
                        return 14;
                    }
                    throw syntaxError("Expected name");
            }
        } else if (peekStack == 4) {
            this.scopes[this.stackSize - 1] = 5;
            int c4 = nextNonWhitespace(true);
            this.buffer.readByte();
            switch (c4) {
                case 58:
                    break;
                case 61:
                    checkLenient();
                    if (this.source.request(1) && this.buffer.getByte(0) == 62) {
                        this.buffer.readByte();
                        break;
                    }
                default:
                    throw syntaxError("Expected ':'");
            }
        } else if (peekStack == 6) {
            this.scopes[this.stackSize - 1] = 7;
        } else if (peekStack == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 18;
                return 18;
            }
            checkLenient();
        } else if (peekStack == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (nextNonWhitespace(true)) {
            case 34:
                this.buffer.readByte();
                this.peeked = 9;
                return 9;
            case 39:
                checkLenient();
                this.buffer.readByte();
                this.peeked = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.buffer.readByte();
                this.peeked = 3;
                return 3;
            case 93:
                if (peekStack == 1) {
                    this.buffer.readByte();
                    this.peeked = 4;
                    return 4;
                }
                break;
            case 123:
                this.buffer.readByte();
                this.peeked = 1;
                return 1;
            default:
                int result = peekKeyword();
                if (result != 0) {
                    return result;
                }
                int result2 = peekNumber();
                if (result2 != 0) {
                    return result2;
                }
                if (isLiteral(this.buffer.getByte(0))) {
                    checkLenient();
                    this.peeked = 10;
                    return 10;
                }
                throw syntaxError("Expected value");
        }
        if (peekStack == 1 || peekStack == 2) {
            checkLenient();
            this.peeked = 7;
            return 7;
        }
        throw syntaxError("Unexpected value");
    }

    private int peekKeyword() throws IOException {
        int peeking;
        String keywordUpper;
        String keyword;
        byte c = this.buffer.getByte(0);
        if (c == 116 || c == 84) {
            keyword = "true";
            keywordUpper = "TRUE";
            peeking = 5;
        } else if (c == 102 || c == 70) {
            keyword = "false";
            keywordUpper = "FALSE";
            peeking = 6;
        } else if (c != 110 && c != 78) {
            return 0;
        } else {
            keyword = "null";
            keywordUpper = "NULL";
            peeking = 7;
        }
        int length = keyword.length();
        for (int i = 1; i < length; i++) {
            if (!this.source.request((long) (i + 1))) {
                return 0;
            }
            byte c2 = this.buffer.getByte((long) i);
            if (c2 != keyword.charAt(i) && c2 != keywordUpper.charAt(i)) {
                return 0;
            }
        }
        if (this.source.request((long) (length + 1)) && isLiteral(this.buffer.getByte((long) length))) {
            return 0;
        }
        this.buffer.skip((long) length);
        this.peeked = peeking;
        return peeking;
    }

    private int peekNumber() throws IOException {
        byte c;
        long value = 0;
        boolean negative = false;
        boolean fitsInLong = true;
        int last = 0;
        int i = 0;
        while (true) {
            boolean z = false;
            if (this.source.request((long) (i + 1))) {
                c = this.buffer.getByte((long) i);
                switch (c) {
                    case 43:
                        if (last != 5) {
                            return 0;
                        }
                        last = 6;
                        continue;
                    case 45:
                        if (last == 0) {
                            negative = true;
                            last = 1;
                            continue;
                        } else if (last == 5) {
                            last = 6;
                            break;
                        } else {
                            return 0;
                        }
                    case 46:
                        if (last != 2) {
                            return 0;
                        }
                        last = 3;
                        continue;
                    case 69:
                    case 101:
                        if (last != 2 && last != 4) {
                            return 0;
                        }
                        last = 5;
                        continue;
                    default:
                        if (c >= 48 && c <= 57) {
                            if (last != 1 && last != 0) {
                                if (last != 2) {
                                    if (last != 3) {
                                        if (last != 5 && last != 6) {
                                            break;
                                        } else {
                                            last = 7;
                                            break;
                                        }
                                    } else {
                                        last = 4;
                                        break;
                                    }
                                } else if (value != 0) {
                                    long newValue = (10 * value) - ((long) (c - 48));
                                    if (value > MIN_INCOMPLETE_INTEGER || (value == MIN_INCOMPLETE_INTEGER && newValue < value)) {
                                        z = true;
                                    }
                                    fitsInLong &= z;
                                    value = newValue;
                                    break;
                                } else {
                                    return 0;
                                }
                            } else {
                                value = (long) (-(c - 48));
                                last = 2;
                                continue;
                            }
                        } else {
                            break;
                        }
                }
            }
            i++;
        }
        if (isLiteral(c)) {
            return 0;
        }
        if (last == 2 && fitsInLong && ((value != Long.MIN_VALUE || negative) && (value != 0 || !negative))) {
            this.peekedLong = negative ? value : -value;
            this.buffer.skip((long) i);
            this.peeked = 16;
            return 16;
        } else if (last != 2 && last != 4 && last != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i;
            this.peeked = 17;
            return 17;
        }
    }

    private boolean isLiteral(int c) throws IOException {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case 32:
            case 44:
            case 58:
            case 91:
            case 93:
            case 123:
            case 125:
                return false;
            case 35:
            case 47:
            case 59:
            case 61:
            case 92:
                checkLenient();
                return false;
            default:
                return true;
        }
    }

    public String nextName() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 14) {
            result = nextUnquotedValue();
        } else if (p == 13) {
            result = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (p == 12) {
            result = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (p == 15) {
            result = this.peekedString;
        } else {
            throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = result;
        return result;
    }

    public int selectName(JsonReader.Options options) throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p < 12 || p > 15) {
            return -1;
        }
        if (p == 15) {
            return findName(this.peekedString, options);
        }
        int result = this.source.select(options.doubleQuoteSuffix);
        if (result != -1) {
            this.peeked = 0;
            this.pathNames[this.stackSize - 1] = options.strings[result];
            return result;
        }
        String lastPathName = this.pathNames[this.stackSize - 1];
        String nextName = nextName();
        int result2 = findName(nextName, options);
        if (result2 == -1) {
            this.peeked = 15;
            this.peekedString = nextName;
            this.pathNames[this.stackSize - 1] = lastPathName;
        }
        return result2;
    }

    public void skipName() throws IOException {
        if (!this.failOnUnknown) {
            int p = this.peeked;
            if (p == 0) {
                p = doPeek();
            }
            if (p == 14) {
                skipUnquotedValue();
            } else if (p == 13) {
                skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else if (p == 12) {
                skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
            } else if (p != 15) {
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
            }
            this.peeked = 0;
            this.pathNames[this.stackSize - 1] = "null";
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    private int findName(String name, JsonReader.Options options) {
        int size = options.strings.length;
        for (int i = 0; i < size; i++) {
            if (name.equals(options.strings[i])) {
                this.peeked = 0;
                this.pathNames[this.stackSize - 1] = name;
                return i;
            }
        }
        return -1;
    }

    public String nextString() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 10) {
            result = nextUnquotedValue();
        } else if (p == 9) {
            result = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (p == 8) {
            result = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (p == 11) {
            result = this.peekedString;
            this.peekedString = null;
        } else if (p == 16) {
            result = Long.toString(this.peekedLong);
        } else if (p == 17) {
            result = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else {
            throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public boolean nextBoolean() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (p == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
        }
    }

    public double nextDouble() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 16) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return (double) this.peekedLong;
        }
        if (p == 17) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (p == 9) {
            this.peekedString = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (p == 8) {
            this.peekedString = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (p == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (p != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double result = Double.parseDouble(this.peekedString);
            if (this.lenient || (!Double.isNaN(result) && !Double.isInfinite(result))) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result;
            }
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + result + " at path " + getPath());
        } catch (NumberFormatException e) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + getPath());
        }
    }

    private String nextQuotedValue(ByteString runTerminator) throws IOException {
        StringBuilder builder = null;
        while (true) {
            long index = this.source.indexOfElement(runTerminator);
            if (index == -1) {
                throw syntaxError("Unterminated string");
            } else if (this.buffer.getByte(index) == 92) {
                if (builder == null) {
                    builder = new StringBuilder();
                }
                builder.append(this.buffer.readUtf8(index));
                this.buffer.readByte();
                builder.append(readEscapeCharacter());
            } else if (builder == null) {
                String result = this.buffer.readUtf8(index);
                this.buffer.readByte();
                return result;
            } else {
                builder.append(this.buffer.readUtf8(index));
                this.buffer.readByte();
                return builder.toString();
            }
        }
    }

    private String nextUnquotedValue() throws IOException {
        long i = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        int i2 = (i > -1 ? 1 : (i == -1 ? 0 : -1));
        Buffer buffer2 = this.buffer;
        return i2 != 0 ? buffer2.readUtf8(i) : buffer2.readUtf8();
    }

    private void skipQuotedValue(ByteString runTerminator) throws IOException {
        while (true) {
            long index = this.source.indexOfElement(runTerminator);
            if (index == -1) {
                throw syntaxError("Unterminated string");
            } else if (this.buffer.getByte(index) == 92) {
                this.buffer.skip(1 + index);
                readEscapeCharacter();
            } else {
                this.buffer.skip(1 + index);
                return;
            }
        }
    }

    private void skipUnquotedValue() throws IOException {
        long i = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer2 = this.buffer;
        buffer2.skip(i != -1 ? i : buffer2.size());
    }

    public int nextInt() throws IOException {
        String str;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 16) {
            long j = this.peekedLong;
            int result = (int) j;
            if (j == ((long) result)) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i = this.stackSize - 1;
                iArr[i] = iArr[i] + 1;
                return result;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
        }
        if (p == 17) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (p == 9 || p == 8) {
            if (p == 9) {
                str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else {
                str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
            }
            this.peekedString = str;
            try {
                int result2 = Integer.parseInt(str);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result2;
            } catch (NumberFormatException e) {
            }
        } else if (p != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double asDouble = Double.parseDouble(this.peekedString);
            int result3 = (int) asDouble;
            if (((double) result3) == asDouble) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr3 = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr3[i3] = iArr3[i3] + 1;
                return result3;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        } catch (NumberFormatException e2) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        }
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.scopes[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    public void skipValue() throws IOException {
        if (!this.failOnUnknown) {
            int count = 0;
            do {
                int p = this.peeked;
                if (p == 0) {
                    p = doPeek();
                }
                if (p == 3) {
                    pushScope(1);
                    count++;
                } else if (p == 1) {
                    pushScope(3);
                    count++;
                } else if (p == 4) {
                    count--;
                    if (count >= 0) {
                        this.stackSize--;
                    } else {
                        throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                    }
                } else if (p == 2) {
                    count--;
                    if (count >= 0) {
                        this.stackSize--;
                    } else {
                        throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                    }
                } else if (p == 14 || p == 10) {
                    skipUnquotedValue();
                } else if (p == 9 || p == 13) {
                    skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                } else if (p == 8 || p == 12) {
                    skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
                } else if (p == 17) {
                    this.buffer.skip((long) this.peekedNumberLength);
                } else if (p == 18) {
                    throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                }
                this.peeked = 0;
            } while (count != 0);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.pathNames[this.stackSize - 1] = "null";
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    private int nextNonWhitespace(boolean throwOnEof) throws IOException {
        int c = 0;
        while (this.source.request((long) (c + 1))) {
            int p = c + 1;
            int c2 = this.buffer.getByte((long) c);
            if (c2 == 10 || c2 == 32 || c2 == 13 || c2 == 9) {
                c = p;
            } else {
                this.buffer.skip((long) (p - 1));
                if (c2 == 47) {
                    if (!this.source.request(2)) {
                        return c2;
                    }
                    checkLenient();
                    switch (this.buffer.getByte(1)) {
                        case 42:
                            this.buffer.readByte();
                            this.buffer.readByte();
                            if (skipToEndOfBlockComment()) {
                                c = 0;
                                break;
                            } else {
                                throw syntaxError("Unterminated comment");
                            }
                        case 47:
                            this.buffer.readByte();
                            this.buffer.readByte();
                            skipToEndOfLine();
                            c = 0;
                            break;
                        default:
                            return c2;
                    }
                } else if (c2 != 35) {
                    return c2;
                } else {
                    checkLenient();
                    skipToEndOfLine();
                    c = 0;
                }
            }
        }
        if (!throwOnEof) {
            return -1;
        }
        throw new EOFException("End of input");
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        long index = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        Buffer buffer2 = this.buffer;
        buffer2.skip(index != -1 ? 1 + index : buffer2.size());
    }

    private boolean skipToEndOfBlockComment() throws IOException {
        BufferedSource bufferedSource = this.source;
        ByteString byteString = CLOSING_BLOCK_COMMENT;
        long index = bufferedSource.indexOf(byteString);
        boolean found = index != -1;
        Buffer buffer2 = this.buffer;
        buffer2.skip(found ? ((long) byteString.size()) + index : buffer2.size());
        return found;
    }

    public String toString() {
        return "JsonReader(" + this.source + ")";
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        if (this.source.request(1)) {
            byte escaped = this.buffer.readByte();
            switch (escaped) {
                case 10:
                case 34:
                case 39:
                case 47:
                case 92:
                    return (char) escaped;
                case 98:
                    return 8;
                case 102:
                    return 12;
                case 110:
                    return 10;
                case 114:
                    return 13;
                case 116:
                    return 9;
                case 117:
                    if (this.source.request(4)) {
                        char result = 0;
                        int end = 0 + 4;
                        for (int i2 = 0; i2 < end; i2++) {
                            byte c = this.buffer.getByte((long) i2);
                            char result2 = (char) (result << 4);
                            if (c >= 48 && c <= 57) {
                                i = c - 48;
                            } else if (c >= 97 && c <= 102) {
                                i = (c - 97) + 10;
                            } else if (c < 65 || c > 70) {
                                throw syntaxError("\\u" + this.buffer.readUtf8(4));
                            } else {
                                i = (c - 65) + 10;
                            }
                            result = (char) (i + result2);
                        }
                        this.buffer.skip(4);
                        return result;
                    }
                    throw new EOFException("Unterminated escape sequence at path " + getPath());
                default:
                    if (this.lenient) {
                        return (char) escaped;
                    }
                    throw syntaxError("Invalid escape sequence: \\" + ((char) escaped));
            }
        } else {
            throw syntaxError("Unterminated escape sequence");
        }
    }
}
