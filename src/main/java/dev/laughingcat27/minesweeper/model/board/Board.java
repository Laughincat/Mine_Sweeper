package dev.laughingcat27.minesweeper.model.board;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.item.Item;
import dev.laughingcat27.minesweeper.model.item.ItemFactory;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.minesweeper.model.tile.TileFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

import java.util.List;

public abstract class Board {
    private final List<List<Tile>> grid;
    private ObjectProperty<Game> gameProperty;

    public Board(int columns, int rows, int bombs) {
        this.grid = TileFactory.createGrid(columns, rows, bombs);
        this.gameProperty = new SimpleObjectProperty<>();

        List<Tile> tiles = Tile.toTiles(this.grid);

        // Maintain parent-child relationship with each tile
        tiles.forEach(tile -> tile.setBoard(this));

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
                this.populateGridWithItems(firstOpenedTile, bombs);
            }
        };

        tiles.forEach(tile -> tile.getOpenProperty().addListener(listener));
    }

    private void populateGridWithItems(Tile firstOpenedTile, int bombs) {
        // Get list of tiles which can have a bomb
        List<Tile> tiles = Tile.toTiles(this.grid);
        tiles.remove(firstOpenedTile);

        // Make sure the amount of bombs is the same as the amount of tiles
        bombs = Math.min(bombs, tiles.size());

        double bombChance = (double) bombs / tiles.size();

        // Make sure the first opened tile gets populated LAST:

        // The item which gets set in the first tile will automatically be used,
        // while some items rely on a complete grid upon use!

        // Loop through the tiles and populate them until the set amount of bombs has been placed
        while (bombs > 0) {
            for (Tile tile : tiles) {

                // In the future maybe we'll want set amounts of other items, too.
                // This function will need some reworking to keep that into account but for now this will do.

                // If tile contains a bomb, do not overwrite its item
                if (tile.getItem() == null || tile.getItem() instanceof Item item && !item.getClass().isAssignableFrom(MineItem.class)) {
                    Item newItem = ItemFactory.createItem(grid, bombChance);

                    if (newItem.getClass().isAssignableFrom(MineItem.class)) bombs--;

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

    public List<List<Tile>> getGrid() {
        return this.grid;
    }

    public Game getGame() {
        return this.gameProperty.get();
    }

    public void setGame(Game game) {
        this.gameProperty.set(game);
    }
}
