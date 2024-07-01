package dev.laughingcat27.minesweeper2.model.tile;

import dev.laughingcat27.minesweeper2.model.item.Item;
import javafx.beans.property.*;

public class Tile {
    private ObjectProperty<Item> itemProperty;
    private BooleanProperty openProperty;
    private BooleanProperty lockedProperty;

    public Tile(Item item) {
        this.itemProperty = new SimpleObjectProperty<>(item);
        this.openProperty = new SimpleBooleanProperty(false);
        this.lockedProperty = new SimpleBooleanProperty(false);

        // Set events
        this.openProperty.addListener((observableValue, aBoolean, newValue) -> {
            if (newValue) {
                this.itemProperty.get().use();
            }
        });
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

    public boolean getLocked() {
        return this.lockedProperty.get();
    }

    public void setLocked(boolean locked) {
        this.lockedProperty.set(locked);
    }

    public void open() {
        if (!this.lockedProperty.get()) {
            this.openProperty.set(true);
            System.out.println("Tile opened!");
        }
    }


}
