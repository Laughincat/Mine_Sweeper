package dev.laughingcat27.minesweeper2.model.item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CounterItem extends Item {
    private IntegerProperty countProperty;

    public CounterItem() {
        this.countProperty = new SimpleIntegerProperty(0);
    }

    @Override
    public void consume() {
        System.out.println("Counter used!");

    }
}
