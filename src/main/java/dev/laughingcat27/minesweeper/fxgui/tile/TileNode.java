package dev.laughingcat27.minesweeper.fxgui.tile;

import dev.laughingcat27.minesweeper.fxgui.item.ItemNode;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TileNode extends StackPane {
    private Tile tile;
    @FXML
    private ItemNode itemNode;
    @FXML
    private Button button;
    @FXML
    private ImageView lockedImageView;

    public TileNode(Tile tile, int x, int y) {
        new ExtendedFxmlLoader().loadCustomObject(this, TileNode.class);

        this.tile = tile;

        // Set itemNode's item
        this.itemNode.setItem(tile.getItem());

        // Bind values
        this.button.visibleProperty().bind(this.tile.getOpenProperty().not());
        this.lockedImageView.visibleProperty().bind(this.tile.getLockedProperty());

        // Set actions
        this.button.setOnAction(_ -> this.tile.open());
        this.button.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                tile.setLocked(!tile.getLocked());
            }
        });

        // Set grid position
        GridPane.setColumnIndex(this, x);
        GridPane.setRowIndex(this, y);
    }


}
