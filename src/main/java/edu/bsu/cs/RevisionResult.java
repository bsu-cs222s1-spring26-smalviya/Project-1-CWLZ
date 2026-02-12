package edu.bsu.cs;

import java.util.*;

public final class RevisionResult {

    private final List<Revision> revisions;
    private final boolean redirect;

    public RevisionResult(List<Revision> revisions, boolean redirect) {
        this.revisions = revisions;
        this.redirect = redirect;
    } // end RevisionResult

    public List<Revision> getRevisions() {
        return revisions;
    } // end getRevisions

    public boolean isRedirect() {
        return redirect;
    } // end isRedirect
} // close class
