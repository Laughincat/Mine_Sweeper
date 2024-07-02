package dev.laughingcat27.minesweeper.model.board;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.minesweeper.model.tile.TileFactory;

import java.util.List;

public abstract class Board {
    private final List<List<Tile>> grid;

    public Board(int columns, int rows, int bombs) {
        this.grid = TileFactory.createGrid(columns, rows, bombs);
    }

    public List<List<Tile>> getGrid() {
        return this.grid;
    }
}
