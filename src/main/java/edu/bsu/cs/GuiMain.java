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
        TextField titleField = new TextField();
        titleField.setPromptText("Enter Wikipedia page title");

        Button fetchButton = new Button("Fetch Revisions");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        fetchButton.setOnAction(e -> {
            String title = titleField.getText();

            WikipediaApiClient client = new WikipediaApiClient();
            RevisionParser parser = new RevisionParser();

            try {
                // get JSON as String
                String json = client.fetchRevisions(title);

                // convert String -> InputStream
                InputStream stream = new ByteArrayInputStream(
                        json.getBytes(StandardCharsets.UTF_8)
                );

                // parse
                RevisionResult result = parser.parse(stream);

                outputArea.setText(result.toString());

            } catch (Exception ex) {
                outputArea.setText("Error: Could not load or parse Wikipedia data.");
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, titleField, fetchButton, outputArea);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("Wikipedia Revision Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}