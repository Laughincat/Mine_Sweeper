package dev.laughingcat27.minesweeper.model.tile;

import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
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

        // Whenever a new item gets set, set this tile in the item to maintain parent-child relationship
        this.itemProperty.addListener((_, _, newItem) -> newItem.setTile(this));

        /* Whenever this tile gets opened and its neighbours aren't detectable, open them all
        this.openProperty.addListener((_, _, open) -> {
            if (open) {

            }
        });
        */

        // I should maybe put this logic in the item class itself, it does these checks whenever it itself gets created.
        // Items are made after the tiles anyways...
        /*
        this.itemProperty.addListener((_, _, newItem) -> {
            if (newItem instanceof UsableItem usableItem && !newItem.getConsumed()) usableItem.use();
        });
         */

    }

    public static boolean isPopulated(List<Tile> tiles) {
        for (Tile tile : tiles) {
            if (tile.getItem() == null) return false;
        }
        return true;
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
            if (tile.isOpen()) openTiles.add(tile);
        });

        return openTiles;
    }

    public static List<Tile> getLockedTiles(List<Tile> tiles) {
        List<Tile> lockedTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.isLocked()) lockedTiles.add(tile);
        });

        return lockedTiles;
    }

    public static List<Tile> getIdentifiedTiles(List<Tile> tiles) {
        List<Tile> identifiedTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.isLocked() || tile.getItem() instanceof MineItem && tile.isOpen()) identifiedTiles.add(tile);
        });

        return identifiedTiles;
    }

    public static List<Tile> getClosedNeighbours(List<List<Tile>> grid, Tile tile) {
        List<Tile> neighbours = Tile.getNeighbouringTiles(grid, tile);
        List<Tile> closedNeighbours = new ArrayList<>();

        neighbours.forEach(neighbourTile -> {
            if (!neighbourTile.isOpen()) closedNeighbours.add(neighbourTile);
        });

        return closedNeighbours;
    }

    public static List<Tile> getDetectableNeighbours(List<List<Tile>> grid, Tile tile) {
        return Tile.getDetectableTiles(Tile.getNeighbouringTiles(grid, tile));
    }

    public static List<Tile> getLockedNeighbours(List<List<Tile>> grid, Tile tile) {
        List<Tile> neighbours = Tile.getNeighbouringTiles(grid, tile);

        return Tile.getLockedTiles(neighbours);
    }

    public static List<Tile> getBombTiles(List<Tile> tiles) {
        List<Tile> bombTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.getItem() instanceof MineItem) bombTiles.add(tile);
        });

        return bombTiles;
    }

    public static List<Tile> getNonBombTiles(List<Tile> tiles) {
        List<Tile> nonBombTiles = new ArrayList<>();

        tiles.forEach(tile -> {
            if (!(tile.getItem() instanceof MineItem)) nonBombTiles.add(tile);
        });

        return nonBombTiles;
    }

    public static int getFlagsLeft(List<Tile> tiles) {
        boolean populated = Tile.isPopulated(tiles);
        int settingsBombCount = tiles.getFirst().getBoard().getGame().getGameSettings().getBombs();
        int actualBombCount = Tile.getBombTiles(tiles).size();

        int bombCount = populated ? actualBombCount : settingsBombCount;
        int identifiedCount = Tile.getIdentifiedTiles(tiles).size();

        return bombCount - identifiedCount;
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

    public boolean isOpen() {
        return this.openProperty.get();
    }

    public boolean isLocked() {
        return this.lockedProperty.get();
    }

    public Item getItem() {
        return this.itemProperty.get();
    }

    private void setOpen(boolean open) {
        this.openProperty.set(open);
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

    public void toggleLock() {
        if (this.isLocked()) this.setLocked(false);
        else {
            List<Tile> tiles = Tile.toTiles(this.grid);
            int flagsLeft = Tile.getFlagsLeft(tiles);

            if (flagsLeft > 0) this.setLocked(true);
        }
    }

    public void open() {
        if (!(this.isOpen() || this.isLocked())) {
            this.setOpen(true);

            // Open all neighbours if those and this aren't detectable.
            boolean neighbouringDetectableTiles = !Tile.getDetectableNeighbours(this.grid, this).isEmpty();
            boolean detectable = this.getItem().getDetectable();

            if (!(neighbouringDetectableTiles || detectable)) {
                //System.out.println("Opening neighbours!");
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

        boolean enoughDetectablesIdentified = lockedNeighbourCount + openDetectableNeighbourCount >= detectableNeighbourCount;

        // If enough nearby detectable tiles have been identified, open nearby tiles
        if (enoughDetectablesIdentified && !this.getItem().getDetectable()) {
            // Get all neighbours
            List<Tile> neighbouringTiles = Tile.getNeighbouringTiles(this.grid, this);

            // And open them
            neighbouringTiles.forEach(Tile::open);
        } else {
            //System.out.println("Can't open neighbours: Not enough tiles have been locked");
        }
    }
}
