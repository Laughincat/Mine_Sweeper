package dev.laughingcat27.minesweeper2.fxgui.item;

import dev.laughingcat27.minesweeper2.model.item.Item;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.scene.image.ImageView;

public class ItemNode extends ImageView {
    private Item item;

    public ItemNode(Item item) {
        new ExtendedFxmlLoader().loadCustomObject(this, ItemNode.class);

        this.item = item;
    }

    public ItemNode() {
        this(null);
    }

    public void setItem(Item item) {
        this.item = item;
        this.setImage(item.getImage());
    }
}
