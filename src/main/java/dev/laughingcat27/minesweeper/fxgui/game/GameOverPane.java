package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GameOverPane extends StackPane {
    @FXML
    private Button retryButton;
    @FXML
    private Button quitButton;

    public GameOverPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, GameOverPane.class);
    }

    public Button getRetryButton() {
        return this.retryButton;
    }

    public Button getQuitButton() {
        return this.quitButton;
    }
}
