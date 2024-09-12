package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.fxgui.board.BoardPane;
import dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector.DuoGameSettingsPane;
import dev.laughingcat27.minesweeper.fxgui.game.gamesettingsselector.GameSettingsPane;
import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.game.gamesettings.GameSettings;
import dev.laughingcat27.minesweeper.model.game.SimpleGame;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.fx.clip.AudioClipFactory;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.util.List;

public class GamePane extends BorderPane {

    @FXML
    private GameTopBar gameTopBar;
    @FXML
    private GameSettingsPane gameSettingsPane;
    @FXML
    private BoardPane boardPane;
    @FXML
    private GameOverPane gameOverPane;

    private final ObjectProperty<Game> gameProperty;

    public GamePane() {
        new ExtendedFxmlLoader().loadCustomObject(this, GamePane.class);

        this.gameProperty = new SimpleObjectProperty<>();

        this.gameSettingsPane.getGameSettingsObservable().addObserver(((_, arg) -> this.setGame((GameSettings) arg)));
        this.gameProperty.addListener(_ -> this.onGameChanged());

        // When the player chooses to restart, restart!
        this.gameTopBar.getCharacterButton().setOnAction(_ -> {
            if (this.getCenter().equals(this.boardPane)) this.restart();
        });
        this.gameOverPane.getRetryButton().setOnAction(_ -> this.restart());

        // When the player quits, return to settings screen.
        this.gameOverPane.getQuitButton().setOnAction(_ -> this.quit());
        this.gameTopBar.getQuitButton().setOnAction(_ -> this.quit());

        this.gameProperty.addListener((_, _, game) -> {
            // When player gets a game over, do stuff
            game.getGameStats().getGameOverProperty().addListener((_, _, gameOver) -> {
                if (gameOver) this.onGameOver();
            });

            // When player wins do other stuff
            game.getGameStats().getVictoryProperty().addListener((_, _, victory) -> {
                if (victory) this.onVictory();
            });
        });
    }

    public Game getGame() {
        return this.gameProperty.get();
    }

    // Should add support for setting the game to null,
    // if the player chooses to quit then the game will be set to null,
    // in onGameChanged() if the new game is null then it sends u back to settings and clears game data from the top bar.

    public void setGame(@NotNull Game game) {
        this.gameProperty.set(game);
    }

    public void setGame(@NotNull GameSettings gameSettings) {
        System.out.println("new game set with gamesettings in gamepane");
        this.setGame(new SimpleGame(gameSettings));
    }

    private void onGameChanged() {
        System.out.println("game changed in gamepane");
        Game game = this.getGame();

        // Set the new game in the other nodes
        this.gameTopBar.setGame(game);
        this.boardPane.setBoard(game.getBoard());

        // Display board
        this.gameOverPane.setVisible(false);
        this.setCenter(this.boardPane);
    }

    public void restart() {
        this.setGame(this.getGame().getGameSettings());
    }

    public void quit() {
        this.setCenter(this.gameSettingsPane);
    }

    private void onGameOver() {
        // Play victory audio
        Clip clip = AudioClipFactory.createClip(GamePane.class.getResource("Death.wav"));
        clip.start();

        // End clip when new game starts
        this.gameProperty.addListener(_ -> clip.close());

        // Display game over screen
        this.gameOverPane.setVisible(true);
    }

    private void onVictory() {
        // Play victory audio
        Clip clip = AudioClipFactory.createClip(GamePane.class.getResource("Victory.wav"));
        clip.start();

        // End clip when new game starts
        this.gameProperty.addListener(_ -> clip.close());

        // Flag all left-over bombs
        Game game = this.getGame();
        List<List<Tile>> grid = game.getBoard().getGrid();
        List<Tile> tiles = Tile.toTiles(grid);

        tiles.forEach(tile -> {
            if (tile.getItem() instanceof MineItem) tile.setLocked(true);
        });
    }
}
