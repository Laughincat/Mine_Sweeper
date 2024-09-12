package dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector;

import dev.laughingcat27.minesweeper.model.game.gamesettings.EasyGameSettings;
import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;
import dev.laughingcat27.minesweeper.model.game.gamesettings.HardGameSettings;
import dev.laughingcat27.minesweeper.model.game.gamesettings.MediumGameSettings;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.awt.event.ActionEvent;

public class PresetGameSettingsPane extends GameSettingsPane {
    @FXML
    private ComboBox<GameSettings> comboBox;
    @FXML
    private Button startButton;

    public PresetGameSettingsPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, PresetGameSettingsPane.class);

        ObservableList<GameSettings> gameSettings = FXCollections.observableArrayList();
        gameSettings.add(new EasyGameSettings());
        gameSettings.add(new MediumGameSettings());
        gameSettings.add(new HardGameSettings());

        this.comboBox.setItems(gameSettings);
    }

    @FXML
    private void onStartButtonAction() {
        GameSettings gameSettings = this.comboBox.getValue();

        super.getGameSettingsObservable().notifyObservers(gameSettings);
    }
}
