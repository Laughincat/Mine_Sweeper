package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public class MineItem extends UsableItem {
    private static Image image;

    static {
        MineItem.image = new Image(String.valueOf(CounterItem.class.getResource("Mine.png")));
    }

    public MineItem(List<List<Tile>> grid) {
        super(grid);
        super.setDetectable(true);
        super.imageProperty.set(MineItem.image);
    }

    @Override
    public void onUsed() {
        System.out.println("Allahu akbar");
        // Get health property
        IntegerProperty healthProperty = super.getTile().getBoard().getGame().getGameStats().getHealthProperty();

        // Calculate new health
        int newHealth = healthProperty.get() - 100;

        // Apply new health
        healthProperty.set(newHealth);
    }
}
