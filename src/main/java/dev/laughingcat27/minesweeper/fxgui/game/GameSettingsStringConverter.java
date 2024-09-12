package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;
import javafx.util.StringConverter;

public class GameSettingsStringConverter extends StringConverter<GameSettings> {
    @Override
    public String toString(GameSettings gameSettings) {
        return gameSettings == null ? null : gameSettings.getName();
    }

    @Override
    public GameSettings fromString(String s) {
        return null;
    }
}
