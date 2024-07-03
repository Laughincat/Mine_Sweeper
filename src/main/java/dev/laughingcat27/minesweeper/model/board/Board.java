package dev.laughingcat27.minesweeper.model.board;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.minesweeper.model.tile.TileFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public abstract class Board {
    private final List<List<Tile>> grid;
    private ObjectProperty<Game> gameProperty;

    public Board(int columns, int rows, int bombs) {
        this.grid = TileFactory.createGrid(columns, rows, bombs);
        this.gameProperty = new SimpleObjectProperty<>();

        // Maintain parent-child relationship with each tile
        Tile.toTiles(this.grid).forEach(tile -> tile.setBoard(this));
    }

    public List<List<Tile>> getGrid() {
        return this.grid;
    }

    public Game getGame() {
        return this.gameProperty.get();
    }

    public void setGame(Game game) {
        this.gameProperty.set(game);
    }
}
