package dev.laughingcat27.minesweeper2.fxgui.board;

import dev.laughingcat27.minesweeper2.fxgui.tile.TileNodeFactory;
import dev.laughingcat27.minesweeper2.model.board.Board;
import dev.laughingcat27.minesweeper2.model.board.SimpleBoard;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.scene.layout.GridPane;

public class BoardNode extends GridPane {

    public BoardNode(Board board) {
        new ExtendedFxmlLoader().loadCustomObject(this, BoardNode.class);

        this.getChildren().setAll(TileNodeFactory.createTileNodes(board.getGrid()));
    }
}
