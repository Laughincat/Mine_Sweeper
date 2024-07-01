module dev.laughingcat27.minesweeper2 {
    requires javafx.controls;
    requires javafx.fxml;

    exports dev.laughingcat27.minesweeper2.fxgui.application;
    opens dev.laughingcat27.minesweeper2.fxgui.application to javafx.fxml;
}