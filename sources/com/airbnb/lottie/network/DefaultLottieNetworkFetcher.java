package com.airbnb.lottie.network;

import androidx.browser.trusted.sharing.ShareTarget;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DefaultLottieNetworkFetcher implements LottieNetworkFetcher {
    public LottieFetchResult fetchSync(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(ShareTarget.METHOD_GET);
        connection.connect();
        return new DefaultLottieFetchResult(connection);
    }
}
