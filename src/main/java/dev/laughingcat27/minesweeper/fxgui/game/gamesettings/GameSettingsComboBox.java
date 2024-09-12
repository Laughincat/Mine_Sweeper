package dev.laughingcat27.minesweeper.fxgui.game.gamesettings;

import dev.laughingcat27.minesweeper.fxgui.game.GameSettingsStringConverter;
import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

class GameSettingsListCell extends ListCell<GameSettings> {

    @Override
    protected void updateItem(GameSettings gameSettings, boolean empty) {
        super.updateItem(gameSettings, empty);
        if (gameSettings == null || empty) {
            super.setGraphic(null);
        } else {
            super.setText(gameSettings.getName());
        }
    }
}


class GameSettingsCellFactory implements Callback<ListView<GameSettings>, ListCell<GameSettings>> {

    @Override
    public ListCell<GameSettings> call(ListView<GameSettings> gameSettingsListView) {
        return new GameSettingsListCell();
    }
}

public class GameSettingsComboBox extends ComboBox<GameSettings> {

    public GameSettingsComboBox() {
        this.setCellFactory(new GameSettingsCellFactory());
        this.setConverter(new GameSettingsStringConverter());
    }
}
