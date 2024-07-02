package dev.laughingcat27.minesweeper.model.tile;

import dev.laughingcat27.minesweeper.model.item.Item;
import dev.laughingcat27.minesweeper.model.item.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {

    public static Tile createTile(List<List<Tile>> grid) {
        Tile tile = new Tile();

        // Create & add item to tile
        Item item = ItemFactory.createItem(grid, tile);
        tile.setItem(item);

        return tile;
    }

    public static List<Tile> createTileColumn(int length, List<List<Tile>> grid) {
        List<Tile> tiles = new ArrayList<>();
        for (int y = 0; y < length; y++) {
            tiles.add(createTile(grid));
        }
        return tiles;
    }

    public static List<List<Tile>> createGrid(int columns, int rows) {
        List<List<Tile>> grid = new ArrayList<>();
        for (int x = 0; x < columns; x++) {
            grid.add(createTileColumn(rows, grid));
        }
        return grid;
    }
}
