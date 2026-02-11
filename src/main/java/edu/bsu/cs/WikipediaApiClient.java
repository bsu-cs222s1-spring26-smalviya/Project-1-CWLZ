package edu.bsu.cs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class WikipediaApiClient {

    private static final String BASE_URL =
            "https://en.wikipedia.org/w/api.php?action=query&format=json" +
                    "&prop=revisions&rvprop=timestamp|user&rvlimit=15&redirects&titles=";

    public String buildUrl(String articleName) {
        String encoded = URLEncoder.encode(articleName, StandardCharsets.UTF_8);
        return BASE_URL + encoded;
    }

    public String fetchRevisionsJson(String articleName) throws Exception {
        String urlString = buildUrl(articleName);
        URL url = new URL(urlString);

        URLConnection connection = url.openConnection();
        connection.setRequestProperty(
                "User-Agent",
                "RevisionReporter/0.1 (yourbsuusername@bsu.edu)"
        );

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream(),
                                StandardCharsets.UTF_8
                        )
                );

        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        return json.toString();
    }
}