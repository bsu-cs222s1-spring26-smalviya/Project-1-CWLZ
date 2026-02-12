package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

public class RevisionFormatterTest {
    @Test
    void formatsSingleRevisionCorrectly() {
        Revision revision = new Revision("TestUser", Instant.parse("2025-08-13T22:47:03Z"));
        RevisionFormatter formatter = new RevisionFormatter();

        String formatted = formatter.format(1, revision);
        assertEquals("1 2025-08-13T22:47:03Z TestUser", formatted);
    }
}
