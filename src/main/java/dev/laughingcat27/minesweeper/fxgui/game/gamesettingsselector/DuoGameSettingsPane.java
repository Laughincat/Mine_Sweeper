package dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector;

import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import dev.laughingcat27.util.observer.IObserver;
import javafx.fxml.FXML;

public class DuoGameSettingsPane extends GameSettingsPane {
    @FXML
    private PresetGameSettingsPane presetGameSettingsPane;
    @FXML
    private CustomGameSettingsPane customGameSettingsPane;

    public DuoGameSettingsPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, DuoGameSettingsPane.class);

        GameSettingsPane gameSettingsPane = this;

        IObserver newGameSettingsObserver = (_, arg) -> {
            GameSettings gameSettings = (GameSettings) arg;
            gameSettingsPane.getGameSettingsObservable().notifyObservers(gameSettings);
        };

        this.customGameSettingsPane.getGameSettingsObservable().addObserver(newGameSettingsObserver);
        this.presetGameSettingsPane.getGameSettingsObservable().addObserver(newGameSettingsObserver);
    }
}
