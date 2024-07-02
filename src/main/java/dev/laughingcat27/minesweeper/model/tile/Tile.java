package dev.laughingcat27.minesweeper.model.tile;

import dev.laughingcat27.minesweeper.model.item.Item;
import javafx.beans.property.*;

import java.util.*;

public class Tile {
    private List<List<Tile>> grid;
    private ObjectProperty<Item> itemProperty;
    private BooleanProperty openProperty;
    private BooleanProperty lockedProperty;

    public Tile(List<List<Tile>> grid) {
        this.grid = grid;
        this.itemProperty = new SimpleObjectProperty<>();
        this.openProperty = new SimpleBooleanProperty(false);
        this.lockedProperty = new SimpleBooleanProperty(false);
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
        List<Tile> tiles1 = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.getItem().getDetectable()) {
                tiles1.add(tile);
            }
        });
        return tiles1;
    }

    public static List<Tile> getNeighbouringDetectableTiles(List<List<Tile>> grid, Tile tile) {
        return Tile.getDetectableTiles(Tile.getNeighbouringTiles(grid, tile));
    }

    public static List<Tile> toTiles(List<List<Tile>> grid) {
        List<Tile> tiles = new ArrayList<>();
        grid.forEach(tiles::addAll);
        return tiles;
    }

    public ObjectProperty<Item> getItemProperty() {
        return this.itemProperty;
    }

    public BooleanProperty getOpenProperty() {
        return this.openProperty;
    }

    public BooleanProperty getLockedProperty() {
        return this.lockedProperty;
    }

    public Item getItem() {
        return this.itemProperty.get();
    }

    public boolean getLocked() {
        return this.lockedProperty.get();
    }

    public void setLocked(boolean locked) {
        this.lockedProperty.set(locked);
    }

    public void setItem(Item item) {
        this.itemProperty.set(item);
    }

    public void open() {
        if (!this.openProperty.get() && !this.lockedProperty.get()) {
            this.openProperty.set(true);

            boolean neighbouringDetectableTiles = !Tile.getNeighbouringDetectableTiles(this.grid, this).isEmpty();
            boolean detectable = this.itemProperty.get().getDetectable();

            if (!neighbouringDetectableTiles && !detectable) {
                Tile.getNeighbouringTiles(this.grid, this).forEach(Tile::open);
            }
        }
    }
}
