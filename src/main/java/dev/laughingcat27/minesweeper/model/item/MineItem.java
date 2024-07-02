package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.scene.image.Image;

import java.util.List;

public class MineItem extends UsableItem {
    private static Image image;

    static {
        MineItem.image = new Image(String.valueOf(CounterItem.class.getResource("Mine.png")));
    }

    public MineItem(List<List<Tile>> grid, Tile tile) {
        super(grid, tile);
        super.setDetectable(true);
        super.imageProperty.set(MineItem.image);
    }

    @Override
    public void use() {
        System.out.println("Allahu akbar");
    }
}
