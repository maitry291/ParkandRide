package com.airbnb.lottie.parser.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public abstract class JsonReader implements Closeable {
    private static final String[] REPLACEMENT_CHARS = new String[128];
    boolean failOnUnknown;
    boolean lenient;
    int[] pathIndices = new int[32];
    String[] pathNames = new String[32];
    int[] scopes = new int[32];
    int stackSize;

    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    public abstract void beginArray() throws IOException;

    public abstract void beginObject() throws IOException;

    public abstract void endArray() throws IOException;

    public abstract void endObject() throws IOException;

    public abstract boolean hasNext() throws IOException;

    public abstract boolean nextBoolean() throws IOException;

    public abstract double nextDouble() throws IOException;

    public abstract int nextInt() throws IOException;

    public abstract String nextName() throws IOException;

    public abstract String nextString() throws IOException;

    public abstract Token peek() throws IOException;

    public abstract int selectName(Options options) throws IOException;

    public abstract void skipName() throws IOException;

    public abstract void skipValue() throws IOException;

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public static JsonReader of(BufferedSource source) {
        return new JsonUtf8Reader(source);
    }

    JsonReader() {
    }

    /* access modifiers changed from: package-private */
    public final void pushScope(int newTop) {
        int i = this.stackSize;
        int[] iArr = this.scopes;
        if (i == iArr.length) {
            if (i != 256) {
                this.scopes = Arrays.copyOf(iArr, iArr.length * 2);
                String[] strArr = this.pathNames;
                this.pathNames = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                int[] iArr2 = this.pathIndices;
                this.pathIndices = Arrays.copyOf(iArr2, iArr2.length * 2);
            } else {
                throw new JsonDataException("Nesting too deep at " + getPath());
            }
        }
        int[] iArr3 = this.scopes;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        iArr3[i2] = newTop;
    }

    /* access modifiers changed from: package-private */
    public final JsonEncodingException syntaxError(String message) throws JsonEncodingException {
        throw new JsonEncodingException(message + " at path " + getPath());
    }

    public final String getPath() {
        return JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }

    public static final class Options {
        final okio.Options doubleQuoteSuffix;
        final String[] strings;

        private Options(String[] strings2, okio.Options doubleQuoteSuffix2) {
            this.strings = strings2;
            this.doubleQuoteSuffix = doubleQuoteSuffix2;
        }

        public static Options of(String... strings2) {
            try {
                ByteString[] result = new ByteString[strings2.length];
                Buffer buffer = new Buffer();
                for (int i = 0; i < strings2.length; i++) {
                    JsonReader.string(buffer, strings2[i]);
                    buffer.readByte();
                    result[i] = buffer.readByteString();
                }
                return new Options((String[]) strings2.clone(), okio.Options.of(result));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void string(BufferedSink sink, String value) throws IOException {
        String replacement;
        String[] replacements = REPLACEMENT_CHARS;
        sink.writeByte(34);
        int last = 0;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            if (c < 128) {
                replacement = replacements[c];
                if (replacement == null) {
                }
            } else if (c == 8232) {
                replacement = "\\u2028";
            } else if (c == 8233) {
                replacement = "\\u2029";
            }
            if (last < i) {
                sink.writeUtf8(value, last, i);
            }
            sink.writeUtf8(replacement);
            last = i + 1;
        }
        if (last < length) {
            sink.writeUtf8(value, last, length);
        }
        sink.writeByte(34);
    }
}
