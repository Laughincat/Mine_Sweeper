package dev.laughingcat27.minesweeper2.model.item;

import dev.laughingcat27.minesweeper2.model.tile.Tile;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

import java.util.List;

public abstract class Item {
    private BooleanProperty consumedProperty;
    private BooleanProperty detectableProperty;

    public Item() {
        this.consumedProperty = new SimpleBooleanProperty(false);
        this.detectableProperty = new SimpleBooleanProperty(false);

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

    public abstract Image getImage();

    public BooleanProperty getConsumedProperty() {
        return this.consumedProperty;
    }

    public boolean getDetectable() {
        return this.detectableProperty.get();
    }

    protected void setDetectable(boolean detectable) {
        this.detectableProperty.set(detectable);
    }

    public abstract void action(List<List<Tile>> grid, Tile tile);

    public final void consume() {
        this.consumedProperty.set(true);
    }
}
