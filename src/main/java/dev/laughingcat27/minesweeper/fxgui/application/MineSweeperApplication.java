package dev.laughingcat27.minesweeper.fxgui.application;

import dev.laughingcat27.minesweeper.fxgui.root.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MineSweeperApplication extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new RootPane(), 640, 480);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Mine Sweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}