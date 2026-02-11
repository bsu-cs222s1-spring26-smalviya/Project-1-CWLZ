package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionTest {

    @Test
    void storesUsernameAndTimeStamp() {
        Instant time = Instant.parse("2025-12-06T06:06:54Z");

        Revision revision = new Revision("TestUser", time);

        assertEquals("TestUser", revision.getUsername());
        assertEquals(time, revision.getTimestamp());
    } // end void
} // close class
