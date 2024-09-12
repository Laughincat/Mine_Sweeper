package dev.laughingcat27.minesweeper.model.item.itemfactory;

import dev.laughingcat27.minesweeper.model.item.CounterItem;
import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.math.MathLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ItemFactory {
    private static List<ItemFactory> mineItemFactories;

    static {
        mineItemFactories = new ArrayList<>(Arrays.asList(
                new SimpleMineItemFactory(),
                //new NukeMineItemFactory(),
                new OpenerMineItemFactory()
        ));
    }

    public static Item createItem(List<List<Tile>> grid, double bombChance) {
        double random = Math.random();

        boolean makeBomb = random <= bombChance;

        return makeBomb ?
                mineItemFactories.get(MathLib.random(0, mineItemFactories.size() - 1)).createItem(grid)
                :
                new CounterItem(grid);
    }

    public abstract Item createItem(List<List<Tile>> grid);
}
