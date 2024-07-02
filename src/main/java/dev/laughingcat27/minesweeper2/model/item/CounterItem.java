package dev.laughingcat27.minesweeper2.model.item;

import dev.laughingcat27.minesweeper2.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public class CounterItem extends Item {
    private static Image image;
    private IntegerProperty countProperty;

    static {
        CounterItem.image = new Image(String.valueOf(CounterItem.class.getResource("0.png")));
    }

    public CounterItem() {
        this.countProperty = new SimpleIntegerProperty(0);
    }

    @Override
    public Image getImage() {
        return CounterItem.image;
    }

    @Override
    public void action(List<List<Tile>> grid, Tile tile) {
        this.countProperty.set(Tile.getDetectableTiles(Tile.getNeighbouringTiles(grid, tile)).size());
    }

    public void update(List<List<Tile>> grid, Tile tile) {
        this.countProperty.set(Tile.getDetectableTiles(Tile.getNeighbouringTiles(grid, tile)).size());
    }
}
