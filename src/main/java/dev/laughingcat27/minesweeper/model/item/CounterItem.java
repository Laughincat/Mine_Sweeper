package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.item.itemtypes.UsableItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CounterItem extends UsableItem {
    private IntegerProperty countProperty;

    public CounterItem(List<List<Tile>> grid) {
        super(grid);
        this.countProperty = new SimpleIntegerProperty(9);

        //tiles.forEach(tile1 -> tile1.getItemProperty().addListener(_ -> this.updateCounter()));
        this.countProperty.addListener((_, _, count) -> {
            Image image = new Image(String.valueOf(CounterItem.class.getResource(count.toString() + ".png")));
            super.imageProperty.set(image);
        });
    }

    private void updateCounter() {
        Tile tile = super.getTile();
        List<Tile> tiles = Tile.getDetectableNeighbours(tile.getBoard().getGrid(), tile);
        this.countProperty.set(tiles.size());
    }

    private void updateCounterB() {
        Tile counterTile = super.getTile();
        List<Tile> counterTileCandidates = new ArrayList<>();

        List<List<Tile>> grid = counterTile.getBoard().getGrid();
        List<Tile> tiles = Tile.toTiles(grid);
        tiles.remove(counterTile);

        tiles.forEach(detectableTile -> {

            List<Tile> currentTiles = new ArrayList<>(Collections.singletonList(detectableTile));
            for (int i = 0; i < detectableTile.getItem().getDetectionRange(); i++) {

                List<Tile> foundTiles = new ArrayList<>();

                currentTiles.forEach(currentTile -> {
                    // Get neighbours from current tile
                    List<Tile> neighbouringTiles = Tile.getNeighbouringTiles(grid, currentTile);

                    // Add neighbours as found tiles unless they're already in the list
                    neighbouringTiles.forEach(neighbouringTile -> {
                        if (!foundTiles.contains(neighbouringTile)) foundTiles.add(neighbouringTile);
                    });
                });

                currentTiles = foundTiles;
                counterTileCandidates.addAll(foundTiles);

                // Add surrounding tiles to checkable list unless they're already in it or they're the detectable tile
                currentTiles.forEach(surroundingTile -> {
                    if (!counterTileCandidates.contains(surroundingTile) || !surroundingTile.equals(detectableTile)) counterTileCandidates.add(surroundingTile);
                });
            }
        });
    }

    @Override
    public void onUsed() {
        this.updateCounter();
    }
}
