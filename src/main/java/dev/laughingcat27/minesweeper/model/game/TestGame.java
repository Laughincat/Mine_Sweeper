package dev.laughingcat27.minesweeper.model.game;

import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;

public class TestGame extends Game {

    public TestGame(GameSettings gameSettings) {
        super(gameSettings);

        System.out.println("Game constructor is done");
    }
}
