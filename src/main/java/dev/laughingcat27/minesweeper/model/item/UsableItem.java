package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public abstract class UsableItem extends Item {

    public UsableItem(List<List<Tile>> grid, Tile tile) {
        super(grid, tile);
        tile.getOpenProperty().addListener((_, _, newValue) -> {
            if (newValue) this.use();
        });
    }

    public abstract void use();
}
