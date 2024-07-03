package dev.laughingcat27.minesweeper.model.tile;

import dev.laughingcat27.minesweeper.model.item.Item;
import dev.laughingcat27.minesweeper.model.item.ItemFactory;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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

    public static void populateGridWithItems(List<List<Tile>> grid, int bombs) {
        List<Tile> tiles = Tile.toTiles(grid);
        double bombChance = (double) bombs / tiles.size();

        // Make sure the first opened tile gets populated LAST:

        // The item which gets set in the first tile will instantly be used,
        // while some items rely on a complete grid upon use!

        tiles.forEach(tile -> {
            Item item = ItemFactory.createItem(grid, bombChance);
            //if (item.getClass().isAssignableFrom(MineItem.class)) {}
            tile.setItem(item);
        });
    }

    public static List<List<Tile>> createGrid(int columns, int rows, int bombs) {
        List<List<Tile>> grid = new ArrayList<>();

        for (int x = 0; x < columns; x++) {
            grid.add(createTileColumn(rows, grid));
        }

        List<Tile> tiles = Tile.toTiles(grid);

        // Populate with items the first time a tile has been opened
        ChangeListener<Boolean> listener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                // This should only fire the first time a tile gets opened, so remove the listener
                tiles.forEach(tile -> tile.getOpenProperty().removeListener(this));

                // Populate the grid
                TileFactory.populateGridWithItems(grid, bombs);
            }
        };

        tiles.forEach(tile -> tile.getOpenProperty().addListener(listener));

        return grid;
    }
}
