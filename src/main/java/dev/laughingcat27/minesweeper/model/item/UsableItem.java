package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.List;

public abstract class UsableItem extends Item {
    private BooleanProperty consumedProperty;
    private ChangeListener<Boolean> tileOpenListener;

    public UsableItem(List<List<Tile>> grid) {
        super(grid);

        this.consumedProperty = new SimpleBooleanProperty(false);

        // Whenever the tile gets opened, this item should be used as a response.
        this.tileOpenListener = (_, _, newOpen) -> {
            if (newOpen) {
                this.use();
            }
        };

        // Whenever this item gets put in a new tile, and the tile is already open, make sure this item gets used!
        super.tileProperty.addListener((_, _, newTile) -> {
            if (newTile.isOpen()) {
                System.out.println("Item is added to an already open tile, using itself...");
                this.use();
            }
        });

        super.tileProperty.addListener((_, oldTile, newTile) -> {
            // Whenever this item is put in a new tile, remove the tileOpenListener from the previous tile.
            if (oldTile != null) oldTile.getOpenProperty().removeListener(this.tileOpenListener);

            newTile.getOpenProperty().addListener((_, _, newOpen) -> {
                if (newOpen) {
                    this.use();
                }
            });
        });
    }

    public boolean getConsumed() {
        return this.consumedProperty.get();
    }

    private void setConsumed(boolean consumed) {
        this.consumedProperty.set(consumed);
    }

    protected abstract void onUsed();

    private void use() {
        /*
        if (!this.getConsumed()) {
            this.setConsumed(true);
            this.onUsed();
        }
         */
        this.onUsed();
    }
}
