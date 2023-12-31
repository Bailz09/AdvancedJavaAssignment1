package com.example.advancedjavaassignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("graph-view.fxml"));//start with the graph view
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Spotify Streams");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/icon.png")));//add a custom icon to the taskbar and window
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}