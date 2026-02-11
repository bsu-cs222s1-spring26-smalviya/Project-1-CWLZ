package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WikipediaRevisionServiceTest {

    @Test
    void getRevisionCount_returnsPositiveNumberForRealArticle() {
        WikipediaApiClient client = new WikipediaApiClient();
        WikipediaRevisionService service = new WikipediaRevisionService(client);

        int count = service.getRevisionCount("Albert Einstein");

        assertTrue(count > 0);
    }
}
