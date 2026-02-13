package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //error when no article name is provided
        if (args.length == 0) {
            System.err.println("Error: Please provide a Wikipedia article name.");
            System.exit(1);
        }

        String articleName = args[0];

        WikipediaApiClient client = new WikipediaApiClient();
        RevisionParser parser = new RevisionParser();
        RevisionFormatter formatter = new RevisionFormatter();

        try {
            //integrate API
            String json = client.fetchRevisionsJson(articleName);

            RevisionResult result = parser.parse(
                    new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
            );

            if (result.isRedirect()) {
                System.out.println("Note: This page was redirected.");
            }

            List<Revision> revisions = result.getRevisions();

            int count = 1;
            for (Revision revision : revisions) {
                String line = formatter.format(count, revision);
                System.out.println(line);
                count++;
            }

        } catch (WikipediaApiException e) {
            // network failure handling
            System.err.println("Network error: " + e.getMessage());
            System.exit(2);

        } catch (Exception e) {
            System.err.println("Error: Could not retrieve revisions for article.");
            System.exit(3);
        }
    }
}
