package dev.laughingcat27.minesweeper2.model.board;

import dev.laughingcat27.minesweeper2.model.tile.Tile;
import dev.laughingcat27.minesweeper2.model.tile.TileFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Board {
    private List<List<Tile>> grid;

    public Board(int columns, int rows) {
        this.grid = TileFactory.createTileBoard(columns, rows);

        // Whenever a tile's item gets changed, update the counters so they represent the new bomb layout
        this.grid.forEach(row -> row.forEach(tile -> tile.getItemProperty().addListener(observable -> updateCounters())));
    }

    public List<List<Tile>> getGrid() {
        return this.grid;
    }

    public List<Tile> getTiles() {
        List<Tile> tiles = new ArrayList<>();
        this.grid.forEach(tiles::addAll);
        return tiles;
    }

    public Vector<Integer> getTilePosition(Tile tile) {
        for (List<Tile> row : this.grid) {
            for (Tile tile1 : row) {
                if (tile1.equals(tile)) {
                    Vector<Integer> position = new Vector<>();
                    position.add(row.indexOf(tile1), this.grid.indexOf(row));
                    return position;
                }
            }
        }
        return null;
    }

    // Updates the bomb counters on all tiles
    private void updateCounters() {

    }
}
