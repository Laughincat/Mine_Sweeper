package dev.laughingcat27.minesweeper2.model.item;

public class ItemFactory {

    public static Item createItem() {
        double bombChance = 0.25;

        double random = Math.random();

        return random <= bombChance ? new BombItem() : new CounterItem();
    }
}
