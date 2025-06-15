package com.example.game_math;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    public static int[] rnd_numbers = new int[4];
    public static int rnd_number_to_match;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController controller = fxmlLoader.getController();
        AnchorPane ap1 = controller.ap1;
        Pane pane1 = controller.pane1;

        ap1.layoutXProperty().bind(
                pane1.widthProperty().subtract(ap1.widthProperty()).divide(2)
        );
        ap1.layoutYProperty().bind(
                pane1.heightProperty().subtract(ap1.heightProperty()).divide(2)
        );

        stage.setTitle("Ping Pong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}