package dev.laughingcat27.minesweeper2.fxgui.tile;

import dev.laughingcat27.minesweeper2.model.tile.Tile;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.scene.layout.StackPane;

public class TileNode extends StackPane {
    private Tile tile;

    public TileNode(Tile tile) {
        new ExtendedFxmlLoader().loadCustomObject(this, TileNode.class);

        this.tile = tile;


    }
}
