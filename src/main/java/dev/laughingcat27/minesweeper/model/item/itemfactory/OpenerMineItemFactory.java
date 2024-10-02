package dev.laughingcat27.minesweeper.model.item.itemfactory;

import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.minesweeper.model.item.OpenerMineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class OpenerMineItemFactory extends MineItemFactory {

    @Override
    public OpenerMineItem createItem(List<List<Tile>> grid) {
        return new OpenerMineItem(grid);
    }
}
