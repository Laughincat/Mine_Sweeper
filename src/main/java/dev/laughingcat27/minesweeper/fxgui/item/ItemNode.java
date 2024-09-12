package dev.laughingcat27.minesweeper.fxgui.item;

import dev.laughingcat27.minesweeper.model.item.itemtypes.Item;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ItemNode extends StackPane {
    @FXML
    private ImageView itemImageView;

    private ObjectProperty<Item> itemProperty;

    public ItemNode(Item item) {
        new ExtendedFxmlLoader().loadCustomObject(this, ItemNode.class);

        this.itemProperty = new SimpleObjectProperty<>(item);

        // Set actions
        this.itemProperty.addListener((_, _, newItem) -> {
            ObjectProperty<Image> itemImageProperty = this.getItemImageView().imageProperty();

            itemImageProperty.unbind();
            itemImageProperty.bind(newItem.getImageProperty());
        });
    }

    public ItemNode() {
        this(null);
    }

    public ImageView getItemImageView() {
        return this.itemImageView;
    }

    public ObjectProperty<Item> getItemProperty() {
        return this.itemProperty;
    }

    public void setItem(Item item) {
        this.itemProperty.set(item);
    }
}
