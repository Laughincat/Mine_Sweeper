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

        // The button dissapears when the tile is open
        this.button.visibleProperty().bind(this.tile.getOpenProperty().not());

        // Show the locked image as long as tile is locked
        this.lockedImageView.visibleProperty().bind(this.tile.getLockedProperty());

        // When the tile's item changes, update the itemNode
        this.itemNode.getItemProperty().bind(tile.getItemProperty());

        // When the button is left clicked, open the tile
        this.button.setOnAction(_ -> this.tile.open());

        // When the button is right clicked, lock the tile
        this.button.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                tile.setLocked(!tile.getLocked());
            }
        });

        // When the left mouse button releases the item node, try opening the neighbours
        this.itemNode.setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                tile.openNeighbours();
            }
        });

        // Set grid position
        GridPane.setColumnIndex(this, x);
        GridPane.setRowIndex(this, y);
    }


}
