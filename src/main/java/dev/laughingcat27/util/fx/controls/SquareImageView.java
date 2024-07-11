package dev.laughingcat27.util.fx.controls;

import javafx.scene.image.ImageView;

public class SquareImageView extends ImageView {

    public SquareImageView() {
        int length = 24;

        this.setFitHeight(length);
        this.setFitWidth(length);
        this.setPreserveRatio(true);
    }
}
