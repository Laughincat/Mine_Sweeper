module dev.laughingcat27.minesweeper2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.incubator.vector;

    exports dev.laughingcat27.minesweeper.fxgui.application;
    opens dev.laughingcat27.minesweeper.fxgui.application to javafx.fxml;
    exports dev.laughingcat27.minesweeper.fxgui.root;
    opens dev.laughingcat27.minesweeper.fxgui.root to javafx.fxml;
    exports dev.laughingcat27.minesweeper.fxgui.tile;
    opens dev.laughingcat27.minesweeper.fxgui.tile to javafx.fxml;
    exports dev.laughingcat27.minesweeper.fxgui.item;
    opens dev.laughingcat27.minesweeper.fxgui.item to javafx.fxml;
    exports dev.laughingcat27.minesweeper.fxgui.game;
    opens dev.laughingcat27.minesweeper.fxgui.game to javafx.fxml;
    exports dev.laughingcat27.util.fx.controls;
    opens dev.laughingcat27.util.fx.controls to javafx.fxml;

    exports dev.laughingcat27.minesweeper.model.tile;
    opens dev.laughingcat27.minesweeper.model.tile to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.item;
    opens dev.laughingcat27.minesweeper.model.item to javafx.fxml;
}