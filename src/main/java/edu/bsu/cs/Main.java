package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    private static final int MAX_REVISIONS = 15;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Error: Please provide a Wikipedia article name.");
            System.exit(1);
        }

        String articleName = args[0];

        WikipediaApiClient client = new WikipediaApiClient();
        RevisionParser parser = new RevisionParser();
        RevisionFormatter formatter = new RevisionFormatter();

        try {
            String json = client.fetchRevisionsJson(articleName);

            RevisionResult result = parser.parse(
                    new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
            );

            if (result.isRedirect()) {
                System.out.println("Note: This page was redirected.");
            }

            printRevisions(result.getRevisions(), formatter);

        } catch (WikipediaApiException e) {
            System.err.println("Network error: " + e.getMessage());
            System.exit(2);

        } catch (Exception e) {
            System.err.println("Error: Could not retrieve revisions for article.");
            System.exit(3);
        }
    }

    // separate output
    private static void printRevisions(List<Revision> revisions,
                                       RevisionFormatter formatter) {

        int count = 1;

        for (Revision revision : revisions) {
            if (count > MAX_REVISIONS) {
                break; // counter limit to 15
            }

            String line = formatter.format(count, revision);
            System.out.println(line);
            count++;
        }
    }
}
