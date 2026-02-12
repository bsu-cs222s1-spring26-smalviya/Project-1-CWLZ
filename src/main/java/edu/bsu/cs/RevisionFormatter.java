package edu.bsu.cs;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class RevisionFormatter {
    public String format(int index, Revision revision) {
        return index + " " + formatTimestamp(revision.getTimestamp()) + " " + revision.getUsername();
    } // end format

    public String formatList(List<Revision> revisions) {
        List<Revision> mostRecentFirst = revisions.stream().sorted(Comparator.comparing(Revision::getTimestamp).reversed()).limit(15).collect(Collectors.toList());

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        int counter = 1;

        for (Revision revision : mostRecentFirst) {
            joiner.add(format(counter, revision));
            counter++;
        } // end for

        return joiner.toString();
    } // end formatList

    private String formatTimestamp(Instant timestamp) {
        return timestamp.toString(); // ISO-8601 UTC by default
    } // end formatTimestamp
} // close class
