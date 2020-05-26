package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private double xoffset;
    private double yoffset;

    /**
     * @param primaryStage is the inital stage to start
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Curvesketching.fxml"));
        root.setOnMousePressed(event -> {
            xoffset=event.getSceneX();
            yoffset=event.getSceneY();
        });

        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX()-xoffset);
            primaryStage.setY(e.getScreenY()-yoffset);
        });


        root.getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());
        Font.loadFont(getClass().getResourceAsStream("Gilroy-Medium.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("Gilroy-Bold.ttf"), 16);
        primaryStage.setTitle("Curve sketching");
        root.setId("rootnode");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) { launch(args); }

}