package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

public class RevisionParser {
    public List<Revision> parse(InputStream jsonStream) {
        String json = readAll(jsonStream);

        List<Map<String, String>> revisions = JsonPath.read(json, "$.query.pages.*.revisions[*]");
        List<Revision> results = new ArrayList<>();
        for (Map<String, String> revision : revisions) {
            String user = revision.get("user");
            String timestamp = revision.get("timestamp");

            results.add(new Revision(user, Instant.parse(timestamp)));
        } // end for
        return results;
    } // end List<Revision>

    private String readAll(InputStream stream) {
        try {
            return new String(stream.readAllBytes(),StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to read JSON input stream", e);
        } // end try/catch
    } // end readAll
} // close class
