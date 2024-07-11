package dev.laughingcat27.minesweeper.model.item.itemfactory;

import dev.laughingcat27.minesweeper.model.item.SimpleMineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class SimpleMineItemFactory extends ItemFactory {

    public SimpleMineItem createItem(List<List<Tile>> grid) {
        return new SimpleMineItem(grid);
    }
}
