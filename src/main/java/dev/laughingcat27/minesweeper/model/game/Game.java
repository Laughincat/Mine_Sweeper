package dev.laughingcat27.minesweeper.model.game;

import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.minesweeper.model.board.SimpleBoard;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

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

        List<Tile> tiles = Tile.toTiles(board.getGrid());

        // Whenever a tile gets opened, check if the unopened tile count is at most same as bomb count ,
        // When true, give a victory
        tiles.forEach(tile -> tile.getOpenProperty().addListener(observable -> {
            int openTileCount = Tile.getOpenTiles(tiles).size();
            int unOpenTileCount = tiles.size() - openTileCount;

            if (unOpenTileCount <= this.gameSettings.getBombs()) this.gameStats.setVictory(true);
        }));
    }

    public GameSettings getGameSettings() {
        return this.gameSettings;
    }

    public GameStats getGameStats() {
        return this.gameStats;
    }

    public ObjectProperty<Board> getBoardProperty() {
        return this.boardProperty;
    }
}
