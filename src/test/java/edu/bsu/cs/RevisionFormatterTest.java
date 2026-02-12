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
    } // end void

    @Test
    void formatsUpTo15RevisionsInReverseChronologicalOrder() {
        RevisionFormatter formatter = new RevisionFormatter();

        List<Revision> revisions = List.of(
                new Revision("User1", Instant.parse("2025-01-01T00:00:01Z")),
                new Revision("User2", Instant.parse("2025-01-01T00:00:02Z")),
                new Revision("User3", Instant.parse("2025-01-01T00:00:03Z")),
                new Revision("User4", Instant.parse("2025-01-01T00:00:04Z")),
                new Revision("User5", Instant.parse("2025-01-01T00:00:05Z")),
                new Revision("User6", Instant.parse("2025-01-01T00:00:06Z")),
                new Revision("User7", Instant.parse("2025-01-01T00:00:07Z")),
                new Revision("User8", Instant.parse("2025-01-01T00:00:08Z")),
                new Revision("User9", Instant.parse("2025-01-01T00:00:09Z")),
                new Revision("User10", Instant.parse("2025-01-01T00:00:10Z")),
                new Revision("User11", Instant.parse("2025-01-01T00:00:11Z")),
                new Revision("User12", Instant.parse("2025-01-01T00:00:12Z")),
                new Revision("User13", Instant.parse("2025-01-01T00:00:13Z")),
                new Revision("User14", Instant.parse("2025-01-01T00:00:14Z")),
                new Revision("User15", Instant.parse("2025-01-01T00:00:15Z")),
                new Revision("User16", Instant.parse("2025-01-01T00:00:16Z")) // should be ignored
        );

        String result = formatter.formatList(revisions);

        String expected = String.join(System.lineSeparator(),
                "1 2025-01-01T00:00:16Z User16",
                "2 2025-01-01T00:00:15Z User15",
                "3 2025-01-01T00:00:14Z User14",
                "4 2025-01-01T00:00:13Z User13",
                "5 2025-01-01T00:00:12Z User12",
                "6 2025-01-01T00:00:11Z User11",
                "7 2025-01-01T00:00:10Z User10",
                "8 2025-01-01T00:00:09Z User9",
                "9 2025-01-01T00:00:08Z User8",
                "10 2025-01-01T00:00:07Z User7",
                "11 2025-01-01T00:00:06Z User6",
                "12 2025-01-01T00:00:05Z User5",
                "13 2025-01-01T00:00:04Z User4",
                "14 2025-01-01T00:00:03Z User3",
                "15 2025-01-01T00:00:02Z User2"
        );

        assertEquals(expected, result);
    } // end void
} // close class
