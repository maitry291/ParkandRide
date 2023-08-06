package com.airbnb.lottie.network;

import com.airbnb.lottie.utils.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class DefaultLottieFetchResult implements LottieFetchResult {
    private final HttpURLConnection connection;

    public DefaultLottieFetchResult(HttpURLConnection connection2) {
        this.connection = connection2;
    }

    public boolean isSuccessful() {
        try {
            return this.connection.getResponseCode() / 100 == 2;
        } catch (IOException e) {
            return false;
        }
    }

    public InputStream bodyByteStream() throws IOException {
        return this.connection.getInputStream();
    }

    public String contentType() {
        return this.connection.getContentType();
    }

    public String error() {
        try {
            if (isSuccessful()) {
                return null;
            }
            return "Unable to fetch " + this.connection.getURL() + ". Failed with " + this.connection.getResponseCode() + "\n" + getErrorFromConnection(this.connection);
        } catch (IOException e) {
            Logger.warning("get error failed ", e);
            return e.getMessage();
        }
    }

    public void close() {
        this.connection.disconnect();
    }

    private String getErrorFromConnection(HttpURLConnection connection2) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(connection2.getErrorStream()));
        StringBuilder error = new StringBuilder();
        while (true) {
            try {
                String readLine = r.readLine();
                String line = readLine;
                if (readLine != null) {
                    error.append(line).append(10);
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                    }
                }
            } finally {
                try {
                    r.close();
                } catch (Exception e2) {
                }
            }
        }
        return error.toString();
    }
}
