package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Error: Please provide a Wikipedia article name.");
            System.exit(1);
        } // end if

        String articleName = args[0];

        WikipediaApiClient client = new WikipediaApiClient();
        RevisionParser parser = new RevisionParser();
        RevisionFormatter formatter = new RevisionFormatter();

        try {
            String json = client.fetchRevisionsJson(articleName);

            RevisionResult result = parser.parse(
                    new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
            );

            if (result.wasRedirected()) {
                System.out.println("Redirected to " + result.getRedirectedTo());
            }

            System.out.println(formatter.formatList(result.getRevisions()));

        } catch (IllegalArgumentException e) {
            System.err.println("Error: No Wikipedia page found for that article.");
            System.exit(4);


        } catch (WikipediaApiException e) {
            System.err.println("Network error: " + e.getMessage());
            System.exit(2);

        } catch (Exception e) {
            System.err.println("Error: Could not retrieve revisions for article.");
            System.exit(3);
        }
    }
}
