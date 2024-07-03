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

    public static void populateGridWithItems(List<List<Tile>> grid, Tile firstOpenedTile, int bombs) {
        // Get list of tiles which can have a bomb
        List<Tile> tiles = Tile.toTiles(grid);
        tiles.remove(firstOpenedTile);

        double bombChance = (double) bombs / tiles.size();

        // Make sure the first opened tile gets populated LAST:

        // The item which gets set in the first tile will instantly be used,
        // while some items rely on a complete grid upon use!

        // Loop through the tiles and populate them until the set amount of bombs has been placed
        while (bombs > 0) {
            for (Tile tile : tiles) {

                // In the future maybe we'll want set amounts of other items, too.
                // This function will need some reworking to keep that into account but for now this will do.

                // If tile contains a bomb, do not overwrite its item
                if (tile.getItem() == null || tile.getItem() instanceof Item item && !ItemFactory.isMine(item)) {
                    Item newItem = ItemFactory.createItem(grid, bombChance);

                    if (ItemFactory.isMine(newItem)) bombs--;

                    tile.setItem(newItem);

                    // We don't want more bombs than the set amount!
                    if (bombs == 0) bombChance = 0;
                }
            }
        }

        // Set the last tile's item
        Item item = ItemFactory.createItem(grid, 0);
        firstOpenedTile.setItem(item);
    }

    public static List<List<Tile>> createGrid(int columns, int rows, int bombs) {
        List<List<Tile>> grid = new ArrayList<>();

        for (int x = 0; x < columns; x++) {
            grid.add(createTileColumn(rows, grid));
        }

        List<Tile> tiles = Tile.toTiles(grid);

        // Whenever the first tile gets opened, populate all the tiles with items
        ChangeListener<Boolean> listener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                // This should only fire the first time a tile gets opened, so remove the listener
                tiles.forEach(tile -> tile.getOpenProperty().removeListener(this));

                // Find the opened tile
                Tile firstOpenedTile = null;
                for (Tile tile : tiles) {
                    if (tile.getOpenProperty().equals(observableValue)) firstOpenedTile = tile;
                }

                // Populate the grid
                TileFactory.populateGridWithItems(grid, firstOpenedTile, bombs);
            }
        };

        tiles.forEach(tile -> tile.getOpenProperty().addListener(listener));

        return grid;
    }
}
