package dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector;

import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;
import dev.laughingcat27.util.fx.controls.IntegerField;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.fxml.FXML;

public class CustomGameSettingsPane extends GameSettingsPane {
    @FXML
    private IntegerField columnsField;
    @FXML
    private IntegerField rowsField;
    @FXML
    private IntegerField bombsField;
    @FXML
    private IntegerField bombDamageField;

    public CustomGameSettingsPane() {
        new ExtendedFxmlLoader().loadCustomObject(this, CustomGameSettingsPane.class);
    }

    @FXML
    private void onStartButtonAction() {
        String name = "Custom";
        int columns = this.columnsField.getInt();
        int rows = this.rowsField.getInt();
        int bombs = this.bombsField.getInt();
        int bombDamage = this.bombDamageField.getInt();

        GameSettings gameSettings = new GameSettings(name, columns, rows, bombs, bombDamage);

        super.getGameSettingsObservable().notifyObservers(gameSettings);
    }
}
