package org.example.happypaws;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainApplication extends Application {
    public static void main(String[] args) {
        launch();
    }
    /*Comentario para pushear a branch*/

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 380);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}