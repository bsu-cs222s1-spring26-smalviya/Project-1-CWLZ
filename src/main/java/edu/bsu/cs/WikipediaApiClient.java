package edu.bsu.cs;

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

}
