package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class GuiMain extends Application {

    private final WikipediaApiClient client = new WikipediaApiClient();
    private final RevisionParser parser = new RevisionParser();
    private final RevisionFormatter formatter = new RevisionFormatter();

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Wikipedia Revision Tracker");

        TextField articleField = new TextField();
        articleField.setPromptText("Enter Wikipedia article name");

        Button searchButton = new Button("Search");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        searchButton.setOnAction(e -> {
            String article = articleField.getText();

            if (article == null || article.isBlank()) {
                showError("You must enter an article name.");
                return;
            }

            outputArea.setText("Loading...");

            try {
                String json = client.fetchRevisionsJson(article);
                InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
                RevisionResult result = parser.parse(stream);

                StringBuilder builder = new StringBuilder();

                if (result.wasRedirected()) {
                    builder.append("Redirected to: ")
                            .append(result.getRedirectedTo())
                            .append("\n\n");
                }

                int count = 0;
                for (Revision rev : result.getRevisions()) {
                    builder.append(formatter.format(count + 1, rev)).append("\n");
                    count++;
                    if (count == 15) break;
                }

                outputArea.setText(builder.toString());

            } catch (Exception ex) {
                showError("Error fetching article.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, articleField, searchButton, outputArea);

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("Wikipedia Tool");
        stage.setScene(scene);
        stage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}