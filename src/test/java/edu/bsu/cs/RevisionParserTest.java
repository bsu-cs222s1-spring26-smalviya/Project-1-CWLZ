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
        assertNotNull(stream);

        RevisionParser parser = new RevisionParser();
        RevisionResult result = parser.parse(stream);

        List<Revision> revisions = result.getRevisions();
        assertEquals(2, revisions.size());

        Revision first = revisions.get(0);
        assertEquals("Username1", first.getUsername());
        assertEquals(Instant.parse("2026-01-13T22:47:03Z"), first.getTimestamp());
    } // end void

    @Test
    void reportsNotRedirectWhenPageIsNotRedirect() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.json");
        assertNotNull(stream);

        RevisionParser parser = new RevisionParser();
        RevisionResult result = parser.parse(stream);

        assertTrue(result.isRedirect());
    } // end void
} // close class