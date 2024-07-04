package dev.laughingcat27.minesweeper.model.tile;

import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.minesweeper.model.item.Item;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import javafx.beans.property.*;

import java.util.*;

public class Tile {
    private List<List<Tile>> grid;
    private ObjectProperty<Item> itemProperty;
    private ObjectProperty<Board> boardProperty;
    private BooleanProperty openProperty;
    private BooleanProperty lockedProperty;

    public Tile(List<List<Tile>> grid) {
        this.grid = grid;
        this.itemProperty = new SimpleObjectProperty<>();
        this.boardProperty = new SimpleObjectProperty<>();
        this.openProperty = new SimpleBooleanProperty(false);
        this.lockedProperty = new SimpleBooleanProperty(false);

        // Set actions

        // Whenever a new item gets set, set this tile in the item to maintain parent-child relationship
        this.itemProperty.addListener((_, _, newItem) -> newItem.setTile(this));

        // I should maybe put this logic in the item class itself, it does these checks whenever it itself gets created.
        // Items are made after the tiles anyways...
        /*
        this.itemProperty.addListener((_, _, newItem) -> {
            if (newItem instanceof UsableItem usableItem && !newItem.getConsumed()) usableItem.use();
        });
         */

    }

    public static Map<String, Integer> getTilePosition(List<List<Tile>> grid, Tile tile) {
        for (List<Tile> column : grid) {
            for (Tile tile1 : column) {
                if (tile1.equals(tile)) {
                    Map<String, Integer> position = new HashMap<>();

                    position.put("x", grid.indexOf(column));
                    position.put("y", column.indexOf(tile1));

                    return position;
                }
            }
        }
        return null;
    }

    public static List<Tile> getNeighbouringTiles(List<List<Tile>> grid, Tile tile) {
        Map<String, Integer> position = getTilePosition(grid, tile);
        int x = position.get("x");
        int y = position.get("y");

        List<Tile> tiles = new ArrayList<>();

        int xMin = x-1;
        int xMax = x+1;
        int yMin = y-1;
        int yMax = y+1;

        boolean xNotLow = !(xMin == -1);
        boolean xNotHigh = !(xMax == grid.size());
        boolean yNotLow = !(yMin == -1);
        boolean yNotHigh = !(yMax == grid.getFirst().size());

        if (xNotLow) {
            if (yNotLow) tiles.add(grid.get(xMin).get(yMin));
            tiles.add(grid.get(xMin).get(y));
            if (yNotHigh) tiles.add(grid.get(xMin).get(yMax));
        }

        if (yNotLow) tiles.add(grid.get(x).get(yMin));
        if (yNotHigh) tiles.add(grid.get(x).get(yMax));

        if (xNotHigh) {
            if (yNotLow) tiles.add(grid.get(xMax).get(yMin));
            tiles.add(grid.get(xMax).get(y));
            if (yNotHigh) tiles.add(grid.get(xMax).get(yMax));
        }

        return tiles;
    }

    public static List<Tile> getDetectableTiles(List<Tile> tiles) {
        List<Tile> detectableTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.getItem().getDetectable()) {
                detectableTiles.add(tile);
            }
        });
        return detectableTiles;
    }

    public static List<Tile> getOpenTiles(List<Tile> tiles) {
        List<Tile> openTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.getOpen()) openTiles.add(tile);
        });

        return openTiles;
    }

    public static List<Tile> getDetectableNeighbours(List<List<Tile>> grid, Tile tile) {
        return Tile.getDetectableTiles(Tile.getNeighbouringTiles(grid, tile));
    }

    public static List<Tile> getLockedNeighbours(List<List<Tile>> grid, Tile tile) {
        List<Tile> neighbours = Tile.getNeighbouringTiles(grid, tile);
        List<Tile> lockedNeighbours = new ArrayList<>();

        neighbours.forEach(neighbour -> {
            if (neighbour.getLocked()) lockedNeighbours.add(neighbour);
        });

        return lockedNeighbours;
    }

    public static List<Tile> getNonBombTiles(List<Tile> tiles) {
        List<Tile> nonBombTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (!tile.getItem().getClass().isAssignableFrom(MineItem.class)) nonBombTiles.add(tile);
        });

        return nonBombTiles;
    }

    public static List<Tile> toTiles(List<List<Tile>> grid) {
        List<Tile> tiles = new ArrayList<>();
        grid.forEach(tiles::addAll);
        return tiles;
    }

    public BooleanProperty getOpenProperty() {
        return this.openProperty;
    }

    public BooleanProperty getLockedProperty() {
        return this.lockedProperty;
    }

    public ObjectProperty<Item> getItemProperty() {
        return this.itemProperty;
    }

    public Board getBoard() {
        return this.boardProperty.get();
    }

    public boolean getOpen() {
        return this.openProperty.get();
    }

    public boolean getLocked() {
        return this.lockedProperty.get();
    }

    public Item getItem() {
        return this.itemProperty.get();
    }

    public void setLocked(boolean locked) {
        this.lockedProperty.set(locked);
    }

    public void setItem(Item item) {
        this.itemProperty.set(item);
    }

    public void setBoard(Board board) {
        this.boardProperty.set(board);
    }

    public void open() {
        if (!this.openProperty.get() && !this.lockedProperty.get()) {
            this.openProperty.set(true);

            boolean neighbouringDetectableTiles = !Tile.getDetectableNeighbours(this.grid, this).isEmpty();
            boolean detectable = this.itemProperty.get().getDetectable();

            if (!neighbouringDetectableTiles && !detectable) {
                Tile.getNeighbouringTiles(this.grid, this).forEach(Tile::open);
            }
        }
    }

    public void openNeighbours() {
        // Get neighbouring detectable tiles
        List<Tile> detectableNeighbours = Tile.getDetectableNeighbours(this.grid, this);

        int detectableNeighbourCount = detectableNeighbours.size();
        int openDetectableNeighbourCount = Tile.getOpenTiles(detectableNeighbours).size();
        int lockedNeighbourCount = Tile.getLockedNeighbours(this.grid, this).size();

        // If enough nearby detectable tiles have been locked or opened, open nearby tiles
        if (lockedNeighbourCount + openDetectableNeighbourCount >= detectableNeighbourCount) {
            // Get all neighbours
            List<Tile> neighbouringTiles = Tile.getNeighbouringTiles(this.grid, this);

            // And open them
            neighbouringTiles.forEach(Tile::open);
        } else {
            //System.out.println("Can't open neighbours: Not enough tiles have been locked");
        }
    }
}
