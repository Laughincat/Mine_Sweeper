package dev.laughingcat27.minesweeper2.fxgui.root;

import dev.laughingcat27.minesweeper2.fxgui.board.BoardNode;
import dev.laughingcat27.minesweeper2.fxgui.tile.TileNodeFactory;
import dev.laughingcat27.minesweeper2.model.board.Board;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
    @FXML
    private Button startGameButton;

    public RootPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, RootPane.class);
    }

    @FXML
    private void onStartGameButtonAction() {
        BoardNode boardNode = new BoardNode(new Board(5, 3));
        this.setCenter(boardNode);
    }
}
