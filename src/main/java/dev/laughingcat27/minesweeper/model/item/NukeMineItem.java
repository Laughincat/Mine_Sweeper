package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public class NukeMineItem extends MineItem {
    private static Image image;

    static {
        NukeMineItem.image = new Image(String.valueOf(CounterItem.class.getResource("Nuke.png")));
    }

    public NukeMineItem(List<List<Tile>> grid) {
        super(grid);

        super.setImage(NukeMineItem.image);
    }

    @Override
    protected int initDetectionRange() {
        return 2;
    }

    @Override
    public void onUsed() {
        // Get game
        Game game = this.getTile().getBoard().getGame();

        // Get health property
        IntegerProperty healthProperty = game.getGameStats().getHealthProperty();

        // Set health
        healthProperty.set(0);
    }
}
