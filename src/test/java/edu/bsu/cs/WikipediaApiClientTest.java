package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WikipediaApiClientTest {
    @Test
    void fetchRevisionsJson_returnsJsonForValidArticle() throws Exception {
        WikipediaApiClient client = new WikipediaApiClient();
        String json = client.fetchRevisionsJson("Zappa");

        assertNotNull(json);
        assertTrue(json.contains("\"query\""));

    }
}