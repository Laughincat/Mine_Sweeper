module dev.laughingcat27.minesweeper2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.incubator.vector;

    exports dev.laughingcat27.minesweeper2.fxgui.application;
    opens dev.laughingcat27.minesweeper2.fxgui.application to javafx.fxml;
    exports dev.laughingcat27.minesweeper2.fxgui.root;
    opens dev.laughingcat27.minesweeper2.fxgui.root to javafx.fxml;
    exports dev.laughingcat27.minesweeper2.fxgui.tile;
    opens dev.laughingcat27.minesweeper2.fxgui.tile to javafx.fxml;
    exports dev.laughingcat27.minesweeper2.fxgui.item;
    opens dev.laughingcat27.minesweeper2.fxgui.item to javafx.fxml;

    exports dev.laughingcat27.minesweeper2.model.tile;
    opens dev.laughingcat27.minesweeper2.model.tile to javafx.fxml;
    exports dev.laughingcat27.minesweeper2.model.item;
    opens dev.laughingcat27.minesweeper2.model.item to javafx.fxml;
}