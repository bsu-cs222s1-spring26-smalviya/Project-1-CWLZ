package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.*;

public class RevisionFormatterTest {
    @Test
    void formatsSingleRevisionCorrectly() {
        Revision revision = new Revision("TestUser", Instant.parse("2025-08-13T22:47:03Z"));
        RevisionFormatter formatter = new RevisionFormatter();

        String formatted = formatter.format(1, revision);
        assertEquals("1 2025-08-13T22:47:03Z TestUser", formatted);
    } // end void

    @Test
    void formatsMultipleRevisionsCorrectly() {
        RevisionFormatter formatter = new RevisionFormatter();

        List<Revision> revisions = List.of(new Revision("UserA", Instant.parse("2025-08-13T22:47:03Z")), new Revision("UserB", Instant.parse("2025-08-13T22:46:33Z")));

        String result = formatter.formatList(revisions);
        String expected = String.join(System.lineSeparator(), "1 2025-08-13T22:47:03Z UserA", "2 2025-08-13T22:46:33Z UserB");

        assertEquals(expected, result);
    }
} // close class
