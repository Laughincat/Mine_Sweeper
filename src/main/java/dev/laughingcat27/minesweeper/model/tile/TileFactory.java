package dev.laughingcat27.minesweeper.model.tile;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {

    public static Tile createTile(List<List<Tile>> grid) {
        return new Tile(grid);
    }

    public static List<Tile> createTileColumn(int length, List<List<Tile>> grid) {
        List<Tile> tiles = new ArrayList<>();
        for (int y = 0; y < length; y++) {
            tiles.add(createTile(grid));
        }
        return tiles;
    }

    public static List<List<Tile>> createGrid(int columns, int rows, int bombs) {
        List<List<Tile>> grid = new ArrayList<>();

        for (int x = 0; x < columns; x++) {
            grid.add(createTileColumn(rows, grid));
        }

        return grid;
    }
}
