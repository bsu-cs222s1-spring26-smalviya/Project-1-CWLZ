package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

public class RevisionParser {
    public RevisionResult parse(InputStream jsonStream) {
        String json = readAll(jsonStream);

        if(allPagesMissing(json)) {
            throw new IllegalArgumentException("No Wikipedia page found");
        } // end if

        List<Map<String, String>> revisions = JsonPath.read(json, "$.query.pages.*.revisions[*]");
        List<Revision> results = new ArrayList<>();
        for (Map<String, String> revision : revisions) {
            String user = revision.get("user");
            String timestamp = revision.get("timestamp");
            results.add(new Revision(user, Instant.parse(timestamp)));
        } // end for

        String redirectedTo = findRedirectTarget(json);
        return new RevisionResult(results, redirectedTo);
    } // end List<Revision>

    private boolean allPagesMissing(String json) {
        try {
            List<Map<String, Object>> pages = JsonPath.read(json, "$.query.pages.*");
            for (Map<String, Object> page : pages) {
                if (!page.containsKey("missing")) {
                    return false; // at least one real page exists
                } // end if
            }
            return true; // all pages were missing
        } catch (Exception e) {
            return false;
        } // end try/catch
    } // end pageMissing

    private String findRedirectTarget(String json) {
        try {
            List<Map<String, String>> redirects = JsonPath.read(json, "$.query.redirects");

            if (!redirects.isEmpty()) {
                return redirects.get(0).get("to");
            } // end if
            return null;
        } catch (Exception ignored) {
            return null;
        } // end try/catch
    } // end findRedirectTarget

    private String readAll(InputStream stream) {
        try {
            return new String(stream.readAllBytes(),StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to read JSON input stream", e);
        } // end try/catch
    } // end readAll
} // close class
