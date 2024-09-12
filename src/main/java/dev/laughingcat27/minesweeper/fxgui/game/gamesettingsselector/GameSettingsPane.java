package dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector;

import dev.laughingcat27.util.observer.Observable;
import javafx.scene.layout.StackPane;

public abstract class GameSettingsPane extends StackPane {
    private final Observable newGameSettingsObservable;

    public GameSettingsPane() {
        this.newGameSettingsObservable = new Observable() {};
    }

    public Observable getGameSettingsObservable() {
        return this.newGameSettingsObservable;
    }
}
