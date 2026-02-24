package edu.bsu.cs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GuiMain extends Application {

    @Override
    public void start(Stage stage) {

        Label titleLabel = new Label("Wikipedia Revision Viewer");

        TextField pageField = new TextField();
        pageField.setPromptText("Enter Wikipedia page title");

        Button fetchButton = new Button("Fetch Revisions");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(titleLabel, pageField, fetchButton, outputArea);

        Scene scene = new Scene(root, 600, 500);

        fetchButton.setOnAction(event -> {
            String page = pageField.getText().trim();

            if (page.isEmpty()) {
                outputArea.setText("Please enter a page title.");
                return;
            }

            try {
                WikipediaApiClient client = new WikipediaApiClient();
                RevisionParser parser = new RevisionParser();
                String json = client.fetchRevisionsJson(page);

                // Convert String to InputStream for parser
                InputStream jsonStream =
                        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

                RevisionResult result = parser.parse(jsonStream);

                StringBuilder sb = new StringBuilder();
                sb.append("Page: ").append(page).append("\n\n");

                if (result.wasRedirected()) {
                    sb.append("Redirected to: ")
                            .append(result.getRedirectedTo())
                            .append("\n\n");
                }

                if (result.getRevisions().isEmpty()) {
                    sb.append("No revisions found.");
                } else {
                    for (Revision rev : result.getRevisions()) {
                        sb.append("User: ").append(rev.getUsername()).append("\n");
                        sb.append("Timestamp: ").append(rev.getTimestamp()).append("\n");
                        sb.append("--------------------------------\n");
                    }
                }

                outputArea.setText(sb.toString());

            } catch (Exception e) {
                outputArea.setText("Failed to fetch revisions.\n" + e.getMessage());
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