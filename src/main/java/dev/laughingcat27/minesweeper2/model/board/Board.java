package dev.laughingcat27.minesweeper2.model.board;

import dev.laughingcat27.minesweeper2.model.item.Item;
import dev.laughingcat27.minesweeper2.model.tile.Tile;
import dev.laughingcat27.minesweeper2.model.tile.TileFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {
    private final List<List<Tile>> grid;

    public Board(int columns, int rows) {
        this.grid = TileFactory.createGrid(columns, rows);
    }

    public List<List<Tile>> getGrid() {
        return this.grid;
    }
}
