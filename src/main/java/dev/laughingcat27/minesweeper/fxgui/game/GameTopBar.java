package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.game.GameStats;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTopBar extends StackPane {
    private static Image neutralImage;
    private static Image deadImage;

    @FXML
    private Label healthLabel;
    @FXML
    private Label flagsLabel;
    @FXML
    private Button characterButton;
    @FXML
    private ImageView characterImageView;
    @FXML
    private Button quitButton;

    private Game game;

    static {
        GameTopBar.neutralImage = new Image(String.valueOf(GameTopBar.class.getResource("Neutral.png")));
        GameTopBar.deadImage = new Image(String.valueOf(GameTopBar.class.getResource("Dead.png")));
    }

    public GameTopBar() {
        new ExtendedFxmlLoader().loadCustomObject(this, GameTopBar.class);
    }

    public Button getCharacterButton() {
        return this.characterButton;
    }

    public Button getQuitButton() {
        return this.quitButton;
    }

    public void setGame(Game game) {
        this.game = game;
        GameStats gameStats = game.getGameStats();

        StringBinding healthBinding = gameStats.getHealthProperty().asString();
        List<Tile> tiles = Tile.toTiles(game.getBoard().getGrid());
        int bombs = game.getGameSettings().getBombs();

        this.healthLabel.textProperty().bind(Bindings.concat("Health: ", healthBinding));
        tiles.forEach(tile -> {
            tile.getLockedProperty().addListener(_ -> this.updateFlags());
            tile.getOpenProperty().addListener(_ -> this.updateFlags());
        });
        this.flagsLabel.setText(String.valueOf(bombs));
        this.characterImageView.setImage(GameTopBar.neutralImage);

        gameStats.getGameOverProperty().addListener((_, _, gameOver) -> {
            this.characterImageView.setImage(gameOver ? GameTopBar.deadImage : GameTopBar.neutralImage);
        });
    }

    private void updateFlags() {
        List<Tile> tiles = Tile.toTiles(this.game.getBoard().getGrid());

        int flags = Tile.getFlagsLeft(tiles);
        this.flagsLabel.setText(String.valueOf(flags));
    }
}
