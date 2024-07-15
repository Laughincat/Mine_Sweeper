package dev.laughingcat27.minesweeper.fxgui.board;

import dev.laughingcat27.minesweeper.fxgui.tile.TileNode;
import dev.laughingcat27.minesweeper.fxgui.tile.TileNodeFactory;
import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class BoardPane extends StackPane {
    @FXML
    private GridPane gridPane;

    private ObjectProperty<Board> boardProperty;

    public BoardPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, BoardPane.class);

        this.boardProperty = new SimpleObjectProperty<>();

        // When a board gets set, load the board in
        this.boardProperty.addListener((_, _, board) -> {
            BoardPane boardPane = this;

            Thread tileNodeThread = new Thread(() -> {
                Platform.runLater(() -> {
                    List<TileNode> tileNodes = TileNodeFactory.createTileNodes(board.getGrid());
                    boardPane.gridPane.getChildren().setAll(tileNodes);
                    System.out.println(1);
                });
            });

            tileNodeThread.start();
            System.out.println(2);


            this.boardProperty.addListener(observable -> System.out.println("board changed in boardpane"));
        });
    }

    public void setBoard(Board board) {
        this.boardProperty.set(board);
    }
}
