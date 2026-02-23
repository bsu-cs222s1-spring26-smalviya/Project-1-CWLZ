package edu.bsu.cs;

import java.util.*;

public final class RevisionResult {

    private final List<Revision> revisions;
    private final String redirectedTo; // null if not redirected

    public RevisionResult(List<Revision> revisions, String redirectedTo) {
        this.revisions = List.copyOf(revisions);
        this.redirectedTo = redirectedTo;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public boolean wasRedirected() {
        return redirectedTo != null;
    }

    // ✅ ADD THIS
    public boolean isRedirect() {
        return wasRedirected();
    }

    public String getRedirectedTo() {
        return redirectedTo;
    }
}