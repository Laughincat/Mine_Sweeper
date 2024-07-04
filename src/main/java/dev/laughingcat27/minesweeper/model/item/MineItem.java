package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.fxgui.game.GamePane;
import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public abstract class MineItem extends UsableItem {
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
