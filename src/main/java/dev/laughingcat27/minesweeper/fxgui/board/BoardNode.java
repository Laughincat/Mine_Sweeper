package dev.laughingcat27.minesweeper.fxgui.board;

import dev.laughingcat27.minesweeper.fxgui.tile.TileNodeFactory;
import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class BoardNode extends ScrollPane {
    @FXML
    private GridPane gridPane;

    public BoardNode(Board board) {
        new ExtendedFxmlLoader().loadCustomObject(this, BoardNode.class);

        this.gridPane.getChildren().setAll(TileNodeFactory.createTileNodes(board.getGrid()));
    }
}
