package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.math.MathLib;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public class OpenerMineItem extends MineItem {
    private static Image image;

    static {
        OpenerMineItem.image = new Image(String.valueOf(CounterItem.class.getResource("Opener.png")));
    }

    public OpenerMineItem(List<List<Tile>> grid) {
        super(grid);

        super.setImage(OpenerMineItem.image);
    }

    @Override
    protected void onUsed() {
        // Get grid and tile
        Tile tile = super.getTile();
        List<List<Tile>> grid = tile.getBoard().getGrid();


        // Get neighbours
        List<Tile> closedNeighbours = Tile.getClosedNeighbours(grid, tile);

        System.out.println(closedNeighbours);

        int min = Math.min(1, closedNeighbours.size());
        int max = closedNeighbours.size();

        int tilesToOpen = MathLib.random(min, max);
        int openedTiles = 0;

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Tiles to open: " + tilesToOpen);

        while (!(openedTiles == tilesToOpen)) {
            // Refresh the list to stay up-to-date after a tile has been opened
            closedNeighbours = Tile.getClosedNeighbours(grid, tile);

            // Get random tile
            int index = MathLib.random(0, closedNeighbours.size() - 1);
            Tile selectedTile = closedNeighbours.get(index);

            // Open tile
            selectedTile.setLocked(false);
            selectedTile.open();

            openedTiles++;
        }
    }
}
