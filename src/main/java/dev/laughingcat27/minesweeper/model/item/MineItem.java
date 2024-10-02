package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.item.itemtypes.ConsumableItem;
import dev.laughingcat27.minesweeper.model.item.itemtypes.UsableItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public abstract class MineItem extends ConsumableItem {

    public MineItem(List<List<Tile>> grid) {
        super(grid);
        super.setDetectable(true);
    }

    @Override
    protected int initDetectionRange() {
        return 1;
    }
}
