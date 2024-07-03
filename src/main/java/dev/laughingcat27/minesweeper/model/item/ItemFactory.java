package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class ItemFactory {

    public static boolean isMine(Item item) {
        return item.getClass().isAssignableFrom(MineItem.class);
    }

    public static Item createItem(List<List<Tile>> grid, double bombChance) {
        double random = Math.random();

        return random <= bombChance ? new MineItem(grid) : new CounterItem(grid);
    }
}
