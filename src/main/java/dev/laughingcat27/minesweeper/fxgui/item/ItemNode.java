package dev.laughingcat27.minesweeper.fxgui.item;

import dev.laughingcat27.minesweeper.model.item.Item;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

public class ItemNode extends ImageView {
    private ObjectProperty<Item> itemProperty;

    public ItemNode(Item item) {
        new ExtendedFxmlLoader().loadCustomObject(this, ItemNode.class);

        this.itemProperty = new SimpleObjectProperty<>(item);

        // Set actions
        this.itemProperty.addListener((_, _, newValue) -> super.imageProperty().bind(newValue.getImageProperty()));
    }

    public ItemNode() {
        this(null);
    }

    public ObjectProperty<Item> getItemProperty() {
        return this.itemProperty;
    }

    public void setItem(Item item) {
        this.itemProperty.set(item);
    }
}
