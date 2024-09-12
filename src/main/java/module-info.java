module dev.laughingcat27.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jetbrains.annotations;
    requires java.logging;

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
    exports dev.laughingcat27.minesweeper.fxgui.board;
    opens dev.laughingcat27.minesweeper.fxgui.board to javafx.fxml;

    exports dev.laughingcat27.minesweeper.model.tile;
    opens dev.laughingcat27.minesweeper.model.tile to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.item;
    opens dev.laughingcat27.minesweeper.model.item to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.board;
    opens dev.laughingcat27.minesweeper.model.board to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.game;
    opens dev.laughingcat27.minesweeper.model.game to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.item.itemfactory;
    opens dev.laughingcat27.minesweeper.model.item.itemfactory to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.game.gamesettings;
    opens dev.laughingcat27.minesweeper.model.game.gamesettings to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.game.gamestats;
    opens dev.laughingcat27.minesweeper.model.game.gamestats to javafx.fxml;

    exports dev.laughingcat27.util.observer;
    opens dev.laughingcat27.util.observer to javafx.fxml;
    exports dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector;
    opens dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector to javafx.fxml;
    exports dev.laughingcat27.minesweeper.fxgui.game.gamesettings;
    opens dev.laughingcat27.minesweeper.fxgui.game.gamesettings to javafx.fxml;
    exports dev.laughingcat27.minesweeper.model.item.itemtypes;
    opens dev.laughingcat27.minesweeper.model.item.itemtypes to javafx.fxml;
}