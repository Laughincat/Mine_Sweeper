package dev.laughingcat27.minesweeper2.model.tile;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tile {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private BooleanProperty lockedProperty;

    public Tile(int x, int y) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.lockedProperty = new SimpleBooleanProperty(false);
    }

    public IntegerProperty getXProperty() {
        return this.xProperty;
    }

    public IntegerProperty getYProperty() {
        return this.yProperty;
    }

    public BooleanProperty getLockedProperty() {
        return this.lockedProperty;
    }
}
