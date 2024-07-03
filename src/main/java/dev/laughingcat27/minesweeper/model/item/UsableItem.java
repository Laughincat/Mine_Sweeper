package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.value.ChangeListener;

import java.util.List;

public abstract class UsableItem extends Item {
    private ChangeListener<Boolean> tileOpenListener;

    public UsableItem(List<List<Tile>> grid) {
        super(grid);

        UsableItem usableItem = this;

        // Whenever the tile gets opened, this item should be used as a response.
        this.tileOpenListener = (_, _, newOpen) -> {
            if (newOpen) {
                usableItem.setConsumed(true);
                usableItem.use();
            }
        };

        // Whenever this item gets put in a new tile, and the tile is already open, make sure this item gets used!
        super.getTileProperty().addListener((_, _, newTile) -> {
            if (newTile.getOpen() && !super.getConsumed()) this.use();
        });

        super.tileProperty.addListener((_, oldTile, newTile) -> {
            if (oldTile != null) oldTile.getOpenProperty().removeListener(this.tileOpenListener);
            newTile.getOpenProperty().addListener((_, _, newOpen) -> {
                if (newOpen) {
                    super.setConsumed(true);
                    this.onUsed();
                }
            });
        });
    }

    protected abstract void onUsed();

    private void use() {
        this.onUsed();
        super.setConsumed(true);
    }
}
