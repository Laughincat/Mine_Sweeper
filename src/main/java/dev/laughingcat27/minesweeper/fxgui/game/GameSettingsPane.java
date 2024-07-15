package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.model.game.GameSettings;
import dev.laughingcat27.minesweeper.model.game.GameStats;
import dev.laughingcat27.util.fx.controls.IntegerField;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.awt.event.ActionEvent;

public class GameSettingsPane extends StackPane {
    @FXML
    private IntegerField columnsField;
    @FXML
    private IntegerField rowsField;
    @FXML
    private IntegerField bombsField;
    @FXML
    private IntegerField bombDamageField;

    private ObjectProperty<GameSettings> gameSettingsProperty;

    public GameSettingsPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, GameSettingsPane.class);

        this.gameSettingsProperty = new SimpleObjectProperty<>();
    }

    public ObjectProperty<GameSettings> getGameSettingsProperty() {
        return this.gameSettingsProperty;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettingsProperty.set(gameSettings);
    }

    @FXML
    private void onStartButtonAction() {
        int columns = this.columnsField.getInt();
        int rows = this.rowsField.getInt();
        int bombs = this.bombsField.getInt();
        int bombDamage = this.bombDamageField.getInt();

        GameSettings gameSettings = new GameSettings(columns, rows, bombs, bombDamage);

        this.setGameSettings(gameSettings);
    }
}
