package dev.laughingcat27.minesweeper2.model.item;

import dev.laughingcat27.minesweeper2.model.tile.Tile;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.List;

public abstract class Item {
    protected List<List<Tile>> grid;
    protected Tile tile;
    protected ObjectProperty<Image> imageProperty;
    private BooleanProperty consumedProperty;
    private BooleanProperty detectableProperty;

    public Item(List<List<Tile>> grid, Tile tile) {
        this.grid = grid;
        this.tile = tile;
        this.imageProperty = new SimpleObjectProperty<>();
        this.consumedProperty = new SimpleBooleanProperty(false);
        this.detectableProperty = new SimpleBooleanProperty(false);

        this.consumedProperty.addListener((_, _, newValue) -> {
            if (newValue) action();
        });

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

    public ObjectProperty<Image> getImageProperty() {
        return this.imageProperty;
    }

    public BooleanProperty getConsumedProperty() {
        return this.consumedProperty;
    }

    public boolean getDetectable() {
        return this.detectableProperty.get();
    }

    protected void setDetectable(boolean detectable) {
        this.detectableProperty.set(detectable);
    }

    public abstract void action();

    public final void use() {
        System.out.println(this.getClass() + " Item used!");
        this.consumedProperty.set(true);
    }
}
