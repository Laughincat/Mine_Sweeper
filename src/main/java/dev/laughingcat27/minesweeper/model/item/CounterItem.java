package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public class CounterItem extends UsableItem {
    private IntegerProperty countProperty;

    public CounterItem(List<List<Tile>> grid) {
        super(grid);
        this.countProperty = new SimpleIntegerProperty(9);

        // Bind stuff
        List<Tile> tiles = Tile.toTiles(this.grid);

        //tiles.forEach(tile1 -> tile1.getItemProperty().addListener(_ -> this.updateCounter()));
        this.countProperty.addListener((_, _, newValue) -> {
            Image image = new Image(String.valueOf(CounterItem.class.getResource(newValue.toString() + ".png")));
            super.imageProperty.set(image);
        });
    }

    private void updateCounter() {
        List<Tile> tiles = Tile.getDetectableNeighbours(super.grid, super.tileProperty.get());
        this.countProperty.set(tiles.size());
    }

    @Override
    public void onUsed() {
        this.updateCounter();
    }
}
