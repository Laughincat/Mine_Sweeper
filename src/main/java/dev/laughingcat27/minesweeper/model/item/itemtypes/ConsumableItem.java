package dev.laughingcat27.minesweeper.model.item.itemtypes;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

// A UsableItem that only calls onUsed() once.
public abstract class ConsumableItem extends UsableItem {
    private BooleanProperty consumedProperty;

    public ConsumableItem(List<List<Tile>> grid) {
        super(grid);

        this.consumedProperty = new SimpleBooleanProperty();
    }

    public boolean getConsumed() {
        return this.consumedProperty.get();
    }

    private void setConsumed(boolean consumed) {

    }

    @Override
    void use() {
        if (!this.getConsumed()) {
            super.use();
        } else {
            System.out.println("Sorry, item is already consumed!");
        }
    }
}
