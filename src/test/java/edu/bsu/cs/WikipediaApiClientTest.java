package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WikipediaApiClientTest {
    @Test
    void buildUrl_encodesSpacesCorrectly() {
        WikipediaApiClient client = new WikipediaApiClient();
        String url = client.buildUrl("Frank Zappa");

        assertTrue(url.contains("Frank+Zappa"));
    }

    @Test
    void buildUrl_containsBaseApiParameters() {
        WikipediaApiClient client = new WikipediaApiClient();
        String url = client.buildUrl("Zappa");

        assertTrue(url.startsWith("https://en.wikipedia.org/w/api.php"));
        assertTrue(url.contains("prop=revisions"));
    }
}
