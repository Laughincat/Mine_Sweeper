package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class ItemFactory {

    public static Item createItem(List<List<Tile>> grid, Tile tile, double bombChance) {
        double random = Math.random();

        return random <= bombChance ? new MineItem(grid, tile) : new CounterItem(grid, tile);
    }
}
