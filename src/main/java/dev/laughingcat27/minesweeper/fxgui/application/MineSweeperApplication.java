package dev.laughingcat27.minesweeper.fxgui.application;

import dev.laughingcat27.minesweeper.fxgui.root.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MineSweeperApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        RootPane rootPane = new RootPane();
        Scene scene = new Scene(rootPane, 320, 240);

        stage.setTitle("Mine Sweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}