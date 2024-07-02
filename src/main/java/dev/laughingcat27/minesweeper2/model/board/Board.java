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

        List<Tile> tiles = this.getTiles();

        tiles.forEach(tile -> {
            // Whenever a tile's item changes, update the counters so they represent the new bomb layout
            tile.getItemProperty().addListener(_ -> this.updateCounters());

            // Make every item's action fire when consumed,
            // pass along the grid and tile, so it can do its thing.
            Item item = tile.getItem();
            item.getConsumedProperty().addListener(_ -> item.action(this.grid, tile));
        });

    }

    public List<List<Tile>> getGrid() {
        return this.grid;
    }

    public List<Tile> getTiles() {
        List<Tile> tiles = new ArrayList<>();
        this.grid.forEach(tiles::addAll);
        return tiles;
    }

    // Updates the bomb counters on all tiles
    private void updateCounters() {

    }
}
