package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionTest {
    @Test
    void storesUsernameAndTimeStamp() {
        Revision revision = new Revision("TestUser", "2025-12-06T06:06:54");
        assertEquals("TestUser", revision.getUsername());
        assertEquals("2025-12-06T06:06:54", revision.getTimestamp());
    } // end void
} // close class
