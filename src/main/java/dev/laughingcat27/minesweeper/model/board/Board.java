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

        Board board = this;

        // Whenever the first tile gets opened, populate all the tiles with items
        ChangeListener<Boolean> firstTileOpenedListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean b, Boolean t1) {
                System.out.println("First tile opened, adding items");

                // This should only fire the first time a tile gets opened, so remove the listener
                tiles.forEach(tile -> tile.getOpenProperty().removeListener(this));

                // Find the opened tile
                Tile firstOpenedTile = null;
                for (Tile tile : tiles) {
                    if (tile.getOpenProperty().equals(observableValue)) firstOpenedTile = tile;
                }

                // Populate the grid
                board.populateGridWithItems(firstOpenedTile, bombs);
            }
        };

        tiles.forEach(tile -> tile.getOpenProperty().addListener(firstTileOpenedListener));

        // Remember the bug where you'd instantly open bombs sometimes when starting a new game?
        // I believe it might be caused by the fact the first tile tries opening its neighbours when they have nothing in 'em,
        // and afterwards they get filled and the item realizes its tile is already open so it uses itself.

        // Below is bugged: it runs BEFORE tiles get populated! that means EVERY tile is still a non-bomb tile! i fucking love this shit!


        // == FOR VICTORY! ==

        // Whenever a tile gets opened, check if all non-bomb tiles are open,
        // When true, give a victory
        tiles.forEach(tile -> tile.getOpenProperty().addListener(observable -> {
            List<Tile> nonBombTiles = Tile.getNonBombTiles(tiles);

            //System.out.println(nonBombTiles.size());

            int nonBombCount = nonBombTiles.size();
            int openNonBombCount = Tile.getOpenTiles(nonBombTiles).size();

            // Are all non-bombs open?
            if (openNonBombCount == nonBombCount) this.getGame().getGameStats().setVictory(true);
        }));
    }

    private void populateGridWithItems(Tile firstOpenedTile, int bombs) {
        // Get list of tiles which can have a bomb
        List<Tile> tiles = Tile.toTiles(this.grid);

        // Make sure the first opened tile gets populated LAST:

        // The item which gets set in the first tile will automatically be used,
        // while some items rely on a complete grid upon use!
        tiles.remove(firstOpenedTile);

        double bombChance = (double) bombs / tiles.size();

        // Loop through the tiles and populate them until the set amount of bombs has been placed
        while (bombs > 0) {
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
