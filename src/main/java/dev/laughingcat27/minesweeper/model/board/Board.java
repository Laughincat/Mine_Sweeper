package dev.laughingcat27.minesweeper.model.board;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.minesweeper.model.item.itemfactory.ItemFactory;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.minesweeper.model.tile.TileFactory;
import dev.laughingcat27.util.observer.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public abstract class Board {
    private final List<List<Tile>> grid;
    private ObjectProperty<Tile> firstOpenedTileProperty;
    private ObjectProperty<Game> gameProperty;

    private Observable tileOpenedObservable;
    private Observable tileLockedObservable;

    public Board(int columns, int rows, int bombs) {
        this.grid = TileFactory.createGrid(columns, rows, bombs);
        this.firstOpenedTileProperty = new SimpleObjectProperty<>();
        this.gameProperty = new SimpleObjectProperty<>();

        List<Tile> tiles = Tile.toTiles(this.grid);

        // Maintain parent-child relationship with each tile
        tiles.forEach(tile -> tile.setBoard(this));

        Board board = this;

        // The first time a tile gets opened, it should be set to the firstOpenedTile property
        ChangeListener<Boolean> firstTileOpenedListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean b, Boolean t1) {
                // This should only fire the first time a tile gets opened,
                // so remove the listener right away.
                tiles.forEach(tile -> tile.getOpenProperty().removeListener(this));

                // Find the opened tile
                for (Tile tile : tiles) {
                    if (tile.getOpenProperty().equals(observableValue)) board.firstOpenedTileProperty.set(tile);
                }
            }
        };

        tiles.forEach(tile -> tile.getOpenProperty().addListener(firstTileOpenedListener));

        // Once the first opened tile is found, populate everything
        this.firstOpenedTileProperty.addListener(_ -> this.populateGridWithItems(bombs));

        // Add events
        this.addEvents();
    }

    private void addEvents() {
        this.tileOpenedObservable = new Observable() {};
        this.tileLockedObservable = new Observable() {};

        List<Tile> tiles = Tile.toTiles(this.grid);

        tiles.forEach(tile -> {
            tile.getOpenProperty().addListener((_, _, open) -> this.tileOpenedObservable.notifyObservers(open));
            tile.getLockedProperty().addListener((_, _, locked) -> this.tileLockedObservable.notifyObservers(locked));
        });
    }

    protected abstract void checkForVictory();

    private void populateGridWithItems(int bombs) {
        System.out.println("Populating grid with items!");

        // Get list of tiles which can have a bomb
        List<Tile> tiles = Tile.toTiles(this.grid);

        // Make sure the first opened tile gets populated LAST:

        // The item which gets set in the first tile will automatically be used,
        // while some items rely on a complete grid upon use!
        Tile firstOpenedTile = this.getFirstOpenedTile();
        tiles.remove(firstOpenedTile);

        double bombChance = (double) bombs / tiles.size();

        // Loop through the tiles and populate them until the set amount of bombs has been placed
        do {
            //System.out.printf("Not enough bombs placed (%d left)%n", bombs);;
            for (Tile tile : tiles) {

                // In the future maybe we'll want set amounts of other items, too.
                // This function will need some reworking to keep that into account but for now this will do.

                // If tile contains nothing or any item but a bomb, add a bomb
                if (tile.getItem() == null || !(tile.getItem() instanceof MineItem)) {
                    Item newItem = ItemFactory.createItem(grid, bombChance);

                    //System.out.println(newItem.getClass());
                    if (tile.getItem() != null) System.out.println("overwriting " + tile.getItem());

                    if (newItem instanceof MineItem) bombs--;

                    tile.setItem(newItem);

                    // We don't want more bombs than the set amount!
                    if (bombs == 0) bombChance = 0;
                }
            }
        } while (bombs > 0);

        // Set the last tile's item
        Item item = ItemFactory.createItem(grid, 0);
        firstOpenedTile.setItem(item);
    }

    public List<List<Tile>> getGrid() {
        return this.grid;
    }

    public Tile getFirstOpenedTile() {
        return this.firstOpenedTileProperty.get();
    }

    public Game getGame() {
        return this.gameProperty.get();
    }

    public void setGame(Game game) {
        this.gameProperty.set(game);
    }
}
