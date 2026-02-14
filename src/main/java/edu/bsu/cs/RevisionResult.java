package edu.bsu.cs;

import java.util.*;

public final class RevisionResult {

    private final List<Revision> revisions;
    private final String redirectedTo; // null if not redirected

    public RevisionResult(List<Revision> revisions, String redirectedTo) {
        this.revisions = List.copyOf(revisions);
        this.redirectedTo = redirectedTo;
    } // end RevisionResult

    public List<Revision> getRevisions() {
        return revisions;
    } // end getRevisions

    public boolean wasRedirected() {
        return redirectedTo != null;
    } // end isRedirect

    public String getRedirectedTo() {
        return redirectedTo;
    } // end getRedirectedTo
} // close class
