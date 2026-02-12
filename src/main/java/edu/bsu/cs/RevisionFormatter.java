package edu.bsu.cs;

import java.time.Instant;

public class RevisionFormatter {
    public String format(int index, Revision revision) {
        return index + " " + formatTimestamp(revision.getTimestamp()) + " " + revision.getUsername();
    } // end format

    private String formatTimestamp(Instant timestamp) {
        return timestamp.toString(); // ISO-8601 UTC by default
    } // end formatTimestamp
} // close class
