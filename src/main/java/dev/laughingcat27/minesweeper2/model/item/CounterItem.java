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

    public CounterItem(List<List<Tile>> grid, Tile tile) {
        super(grid, tile);
        this.countProperty = new SimpleIntegerProperty();

        // Bind stuff
        List<Tile> tiles = Tile.toTiles(this.grid);
        tiles.forEach(tile1 -> tile1.getItemProperty().addListener(_ -> this.action()));
        System.out.println(this.countProperty);
        this.countProperty.addListener((_, _, newValue) -> {
            Image image = new Image(String.valueOf(CounterItem.class.getResource(newValue.toString() + ".png")));
            super.imageProperty.set(image);
        });
    }

    @Override
    public void action() {
        List<Tile> tiles = Tile.getNeighbouringDetectableTiles(super.grid, super.tile);
        this.countProperty.set(tiles.size());

        System.out.println("Grid: " + super.grid);
        System.out.println("Tile: " + super.tile);

        System.out.println("Neighbouring tiles: " + Tile.getNeighbouringTiles(super.grid, super.tile));
        System.out.println("Neighbouring detectable tiles: " + tiles);
        System.out.println("Amount of neighbouring detectable tiles: " + tiles.size());
    }
}
