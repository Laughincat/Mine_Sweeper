package dev.laughingcat27.minesweeper.model.game;

import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.minesweeper.model.board.SimpleBoard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Game {
    private GameSettings gameSettings;
    private GameStats gameStats;
    private ObjectProperty<Board> boardProperty;

    public Game(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.gameStats = new GameStats();

        // Create board
        Board board = new SimpleBoard(gameSettings.getColumns(), gameSettings.getRows(), gameSettings.getBombs());

        // Maintain parent-child relationship
        board.setGame(this);

        this.boardProperty = new SimpleObjectProperty<>(board);
    }

    public GameStats getGameStats() {
        return this.gameStats;
    }

    public ObjectProperty<Board> getBoardProperty() {
        return this.boardProperty;
    }
}
