package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class ItemFactory {

    public static Item createItem(List<List<Tile>> grid, double bombChance) {
        double random = Math.random();

        boolean makeBomb = random <= bombChance;

        return makeBomb ? new SimpleMineItem(grid) : new CounterItem(grid);
    }
}
