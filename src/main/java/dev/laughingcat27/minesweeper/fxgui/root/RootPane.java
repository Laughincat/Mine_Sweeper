package dev.laughingcat27.minesweeper.fxgui.root;

import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {

    public RootPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, RootPane.class);
    }
}
