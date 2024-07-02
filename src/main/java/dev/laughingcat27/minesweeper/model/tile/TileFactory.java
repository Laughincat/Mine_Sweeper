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

        tiles.forEach(tile -> {
            Item item = ItemFactory.createItem(grid, tile, bombChance);
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

        // Populate with items once a tile has been opened
        ChangeListener<Boolean> listener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("Populating grid with items!");
                tiles.forEach(tile -> tile.getOpenProperty().removeListener(this));
                TileFactory.populateGridWithItems(grid, bombs);
            }
        };

        tiles.forEach(tile -> tile.getOpenProperty().addListener(listener));

        return grid;
    }
}
