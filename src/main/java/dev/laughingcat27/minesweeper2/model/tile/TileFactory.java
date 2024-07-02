package dev.laughingcat27.minesweeper2.model.tile;

import dev.laughingcat27.minesweeper2.model.item.Item;
import dev.laughingcat27.minesweeper2.model.item.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {

    public static Tile createTile() {
        Item item = ItemFactory.createItem();
        return new Tile(item);
    }

    public static List<Tile> createTileColumn(int length) {
        List<Tile> tiles = new ArrayList<>();
        for (int y = 0; y < length; y++) {
            tiles.add(createTile());
        }
        return tiles;
    }

    public static List<List<Tile>> createGrid(int columns, int rows) {
        List<List<Tile>> grid = new ArrayList<>();
        for (int x = 0; x < columns; x++) {
            grid.add(createTileColumn(rows));
        }
        return grid;
    }
}
