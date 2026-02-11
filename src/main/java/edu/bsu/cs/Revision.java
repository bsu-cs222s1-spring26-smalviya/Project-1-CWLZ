package edu.bsu.cs;

import java.time.Instant;

public final class Revision {
    private final String username;
    private final Instant timestamp;

    public Revision(String username, Instant timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    } // end Revision

    public String getUsername() {
        return username;
    } // end getUsername

    public Instant getTimestamp() {
        return timestamp;
    } // end getTimestamp
} // close class