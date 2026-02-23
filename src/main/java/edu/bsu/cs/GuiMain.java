package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiMain extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello GUI");
        VBox root = new VBox(label);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Wikipedia GUI");
        stage.show();
    }
}