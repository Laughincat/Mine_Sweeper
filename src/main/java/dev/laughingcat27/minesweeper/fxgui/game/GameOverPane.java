package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.Stack;

public class GameOverPane extends StackPane {
    @FXML
    private Button continueButton;

    public GameOverPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, GameOverPane.class);
    }

    public Button getContinueButton() {
        return this.continueButton;
    }
}
