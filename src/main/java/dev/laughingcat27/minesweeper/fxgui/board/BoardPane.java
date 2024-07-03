package dev.laughingcat27.minesweeper.fxgui.board;

import dev.laughingcat27.minesweeper.fxgui.tile.TileNodeFactory;
import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class BoardPane extends ScrollPane {
    private ObjectProperty<Board> boardProperty;
    @FXML
    private GridPane gridPane;

    public BoardPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, BoardPane.class);

        this.boardProperty = new SimpleObjectProperty<>();

        // When a board gets set, load it in
        this.boardProperty.addListener((_, _, board) -> {
            this.gridPane.getChildren().setAll(TileNodeFactory.createTileNodes(board.getGrid()));
        });
    }

    public void setBoard(Board board) {
        this.boardProperty.set(board);
    }
}
