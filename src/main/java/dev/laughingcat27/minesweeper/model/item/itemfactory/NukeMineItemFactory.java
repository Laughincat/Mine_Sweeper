package dev.laughingcat27.minesweeper.model.item.itemfactory;

import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.item.NukeMineItem;
import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class NukeMineItemFactory extends MineItemFactory {

    public NukeMineItem createItem(List<List<Tile>> grid) {
        return new NukeMineItem(grid);
    }
}
