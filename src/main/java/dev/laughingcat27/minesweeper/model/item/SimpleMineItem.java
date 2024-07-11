package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public class SimpleMineItem extends MineItem {
    private static Image image;

    static {
        SimpleMineItem.image = new Image(String.valueOf(CounterItem.class.getResource("Mine.png")));
    }

    public SimpleMineItem(List<List<Tile>> grid) {
        super(grid);

        super.setImage(SimpleMineItem.image);
    }

    @Override
    protected void onUsed() {
        Game game = super.getTile().getBoard().getGame();

        // Get health property
        IntegerProperty healthProperty = game.getGameStats().getHealthProperty();

        // Calculate new health
        int bombDamage = game.getGameSettings().getBombDamage();
        int newHealth = healthProperty.get() - bombDamage;

        // Apply new health
        healthProperty.set(newHealth);
    }
}
