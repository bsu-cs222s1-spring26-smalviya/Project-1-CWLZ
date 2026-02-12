package edu.bsu.cs;

import java.time.Instant;
import java.util.*;

public class RevisionFormatter {
    public String format(int index, Revision revision) {
        return index + " " + formatTimestamp(revision.getTimestamp()) + " " + revision.getUsername();
    } // end format

    public String formatList(List<Revision> revisions) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        int counter = 1;

        for (Revision revision : revisions) {
            joiner.add(format(counter, revision));
            counter++;
        } // end for
        return joiner.toString();
    } // end formatList

    private String formatTimestamp(Instant timestamp) {
        return timestamp.toString(); // ISO-8601 UTC by default
    } // end formatTimestamp
} // close class
