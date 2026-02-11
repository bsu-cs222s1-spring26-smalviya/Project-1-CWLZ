package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WikipediaApiClientTest {

    @Test
    void fetchRevisionsJson_throwsCustomExceptionOnFailure() {
        WikipediaApiClient client = new WikipediaApiClient() {
            protected URLConnection openConnection(String urlString) throws Exception {
                throw new IOException("Simulated network failure");
            }
        };

        assertThrows(WikipediaApiException.class, () -> {
            client.fetchRevisionsJson("Anything");
        });
    }
}
