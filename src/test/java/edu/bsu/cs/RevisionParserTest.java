package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionParserTest {

    @Test
    void parsesMultipleRevisionFromSampleJson() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.json");

        assertNotNull(stream, "Test JSON file not found in test/resources");

        RevisionParser parser = new RevisionParser();
        List<Revision> revisions = parser.parse(stream);

        assertEquals(2, revisions.size());

        Revision first = revisions.get(0);
        assertEquals("Username1", first.getUsername());
        assertEquals(Instant.parse("2026-01-13T22:47:03Z"), first.getTimestamp());
    }
}