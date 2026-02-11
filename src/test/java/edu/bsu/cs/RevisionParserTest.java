package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionParserTest {

    @Test
    void parsesMultipleRevisionsFromSampleJson() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.json");
        assertNotNull(stream, "Test JSON file not found in test/resources");

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(stream);

        assertEquals(2, revisions.size());

        Revision first = revisions.get(0);
        assertEquals("Username1", first.getUsername());
        assertEquals(Instant.parse("2026-01-13T22:47:03Z"), first.getTimestamp());
    } // end void

    @Test
    void parsesAllRevisionsWhenFewerThan15Exist() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.json");
        assertNotNull(stream, "Test JSON file not found in test/resources");

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(stream);

        assertTrue(revisions.size() < 15, "Test data should contain fewer than 15 revisions");
        assertEquals(2, revisions.size());
    } // end void

    @Test
    void parsesRevisionsInReverseChronologicalOrder() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.json");
        assertNotNull(stream, "Test JSON file not found in test/resources");

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(stream);

        Instant firstTimestamp = revisions.get(0).getTimestamp();
        Instant secondTimestamp = revisions.get(1).getTimestamp();

        assertTrue(firstTimestamp.isAfter(secondTimestamp),
                "Revisions should be in reverse chronological order (newest first)");
    } // end void
} // close class
