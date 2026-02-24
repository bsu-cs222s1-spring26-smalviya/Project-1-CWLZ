package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class GuiMain extends Application {

    @Override
    public void start(Stage stage) {
        TextField pageField = new TextField();
        pageField.setPromptText("Enter Wikipedia page title");

        Button fetchButton = new Button("Fetch Revisions");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        VBox root = new VBox(10, pageField, fetchButton, outputArea);
        Scene scene = new Scene(root, 500, 500);

        fetchButton.setOnAction(event -> {
            String page = pageField.getText().trim();

            if (page.isEmpty()) {
                outputArea.setText("Please enter a page title.");
                return;
            }

            try {
                WikipediaApiClient client = new WikipediaApiClient();
                RevisionParser parser = new RevisionParser();

                // IMPORTANT: this must return InputStream
                InputStream jsonStream = client.fetchRevisions(page);

                RevisionResult result = parser.parse(jsonStream);

                StringBuilder sb = new StringBuilder();

                if (result.wasRedirected()) {
                    sb.append("Redirected to: ")
                            .append(result.getRedirectedTo())
                            .append("\n\n");
                } else {
                    sb.append("No redirect\n\n");
                }

                for (Revision rev : result.getRevisions()) {
                    sb.append("User: ").append(rev.getUser()).append("\n");
                    sb.append("Timestamp: ").append(rev.getTimestamp()).append("\n");
                    sb.append("Comment: ").append(rev.getComment()).append("\n");
                    sb.append("---------------------------\n");
                }

                outputArea.setText(sb.toString());

            } catch (Exception e) {
                outputArea.setText("Error: " + e.getMessage());
                e.printStackTrace();
            }
        });

        stage.setTitle("Wikipedia Revision Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}