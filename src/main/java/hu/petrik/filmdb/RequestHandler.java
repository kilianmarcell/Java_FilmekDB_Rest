package hu.petrik.filmdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHandler {
    private RequestHandler() {}
    public static Response get(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        int responseCode = conn.getResponseCode();
        InputStream is;
        if (responseCode < 400) {
            is = conn.getInputStream();
        } else {
            is = conn.getErrorStream();
        }

        is = conn.getInputStream();
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String sor = br.readLine();
        while (sor != null) {
            builder.append(sor);
            sor = br.readLine();
        }
        br.close();
        is.close();
        return new Response(responseCode, builder.toString());
    }

    public static Response get(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        int responseCode = conn.getResponseCode();
        InputStream is;
        if (responseCode < 400) {
            is = conn.getInputStream();
        } else {
            is = conn.getErrorStream();
        }

        is = conn.getInputStream();
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String sor = br.readLine();
        while (sor != null) {
            builder.append(sor);
            sor = br.readLine();
        }
        br.close();
        is.close();
        return new Response(responseCode, builder.toString());
    }
}
