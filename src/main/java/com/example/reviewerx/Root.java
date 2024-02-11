package com.example.reviewerx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Root extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(com.example.reviewerx.Root.class.getResource("root.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("File Reviewer :)");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }



}
