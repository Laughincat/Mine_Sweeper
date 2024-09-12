package dev.laughingcat27.minesweeper.model.item.itemtypes;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.util.List;

public abstract class Item {
    protected ObjectProperty<Tile> tileProperty;
    protected ObjectProperty<Image> imageProperty;
    private BooleanProperty detectableProperty;
    private IntegerProperty detectionRangeProperty;

    public Item(List<List<Tile>> grid) {
        this.tileProperty = new SimpleObjectProperty<>();
        this.imageProperty = new SimpleObjectProperty<>();
        this.detectableProperty = new SimpleBooleanProperty();
        this.detectionRangeProperty = new SimpleIntegerProperty();

        // Bind events
        /*
        This binding has been put in the Board class,
        action() requires parameters found in board but maybe i should put those in this class's constructor.
        this.consumedProperty.addListener(((_, _, newValue) -> {
            if (newValue) {
                this.action();
            }
        }));
         */
    }

    protected ObjectProperty<Tile> getTileProperty() {
        return this.tileProperty;
    }

    public ObjectProperty<Image> getImageProperty() {
        return this.imageProperty;
    }

    public boolean getDetectable() {
        return this.detectableProperty.get();
    }

    public int getDetectionRange() {
        return this.detectionRangeProperty.get();
    }

    public Tile getTile() {
        return this.tileProperty.get();
    }

    public void setTile(Tile tile) {
        this.tileProperty.set(tile);
    }

    protected void setImage(Image image) {
        this.imageProperty.set(image);
    }

    protected void setDetectable(boolean detectable) {
        this.detectableProperty.set(detectable);
    }
}
