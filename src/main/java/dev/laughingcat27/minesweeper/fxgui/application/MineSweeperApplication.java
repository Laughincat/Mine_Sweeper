package dev.laughingcat27.minesweeper.fxgui.application;

import dev.laughingcat27.minesweeper.fxgui.root.RootPane;
import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.game.GameSettings;
import dev.laughingcat27.minesweeper.model.game.TestGame;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.item.SimpleMineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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