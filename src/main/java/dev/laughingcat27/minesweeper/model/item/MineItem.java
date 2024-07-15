package dev.laughingcat27.minesweeper.model.item;

import dev.laughingcat27.minesweeper.fxgui.game.GamePane;
import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.util.List;

public abstract class MineItem extends UsableItem {

    public MineItem(List<List<Tile>> grid) {
        super(grid);
        super.setDetectable(true);
    }
}
