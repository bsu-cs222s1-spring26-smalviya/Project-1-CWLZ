package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiMain extends Application {

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Wikipedia Revision Tracker");

        TextField articleField = new TextField();
        articleField.setPromptText("Enter Wikipedia article name");

        Button searchButton = new Button("Search");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, articleField, searchButton, outputArea);

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("Wikipedia Tool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}