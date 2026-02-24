package edu.bsu.cs;

import java.time.Instant;

public final class Revision {
    private final String username;
    private final Instant timestamp;

    public Revision(String username, Instant timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}