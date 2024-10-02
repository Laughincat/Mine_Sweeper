package dev.laughingcat27.minesweeper.model.item.itemfactory;

import dev.laughingcat27.minesweeper.model.item.CounterItem;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.item.NukeMineItem;
import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.math.MathLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ItemFactory {
    private static final List<MineItemFactory> mineItemFactories;

    static {
        mineItemFactories = new ArrayList<>(Arrays.asList(
                //new SimpleMineItemFactory(),
                new NukeMineItemFactory(),
                new OpenerMineItemFactory()
        ));
    }

    public static Item createItem(List<List<Tile>> grid, double bombChance) {
        double random = Math.random();

        boolean makeBomb = random <= bombChance;
        int max = mineItemFactories.size() - 1;

        Item item;

        if (makeBomb) {
            item = mineItemFactories.get(MathLib.random(0, max)).createItem(grid);
            if (item.getClass().equals(NukeMineItem.class)) mineItemFactories.remove(0);
        } else {
            item = new CounterItem(grid);
        }

        return item;

        //return makeBomb ? mineItemFactories.get(MathLib.random(0, max)).createItem(grid) : new CounterItem(grid);
    }

    public abstract Item createItem(List<List<Tile>> grid);
}
