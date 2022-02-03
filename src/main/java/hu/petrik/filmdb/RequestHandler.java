package hu.petrik.filmdb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHandler {
    private RequestHandler() {}
    public static void get(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

    }
}
