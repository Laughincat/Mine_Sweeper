package dev.laughingcat27.minesweeper.model.item.itemfactory;

import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public abstract class MineItemFactory extends ItemFactory {

    @Override
    public abstract MineItem createItem(List<List<Tile>> grid);
}
