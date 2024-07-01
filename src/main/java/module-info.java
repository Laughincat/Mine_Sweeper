module dev.laughingcat27.minesweeper2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.laughingcat27.minesweeper2 to javafx.fxml;
    exports dev.laughingcat27.minesweeper2;
}