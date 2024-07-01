package dev.laughingcat27.minesweeper2.model.item;

public class BombItem extends Item {

    @Override
    public void consume() {
        System.out.println("Boom lol");
    }
}
