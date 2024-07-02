package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.fxgui.board.BoardNode;
import dev.laughingcat27.minesweeper.model.board.Board;
import dev.laughingcat27.minesweeper.model.board.SimpleBoard;
import dev.laughingcat27.util.fx.controls.IntegerField;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GamePane extends BorderPane {
    private static Image neutralImage;
    @FXML
    private ImageView characterImageView;
    @FXML
    private IntegerField columnsField;
    @FXML
    private IntegerField rowsField;
    @FXML
    private IntegerField bombsField;
    @FXML
    private Button startButton;

    static {
        GamePane.neutralImage = new Image(String.valueOf(GamePane.class.getResource("Neutral.png")));
    }

    public GamePane() {
        new ExtendedFxmlLoader().loadCustomObject(this, GamePane.class);

        this.characterImageView.setImage(GamePane.neutralImage);
    }

    @FXML
    private void onStartButtonAction() {
        int columns = columnsField.getInteger();
        int rows = rowsField.getInteger();
        int bombs = bombsField.getInteger();

        Board board = new SimpleBoard(columns, rows, bombs);
        BoardNode boardNode = new BoardNode(board);

        this.setCenter(boardNode);
    }
}
