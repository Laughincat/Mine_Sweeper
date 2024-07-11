package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.fxgui.board.BoardPane;
import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.game.GameSettings;
import dev.laughingcat27.minesweeper.model.game.SimpleGame;
import dev.laughingcat27.minesweeper.model.item.MineItem;
import dev.laughingcat27.minesweeper.model.tile.Tile;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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

        this.gameSettingsPane.getGameSettingsProperty().addListener((_, _, gameSettings) -> this.setGame(gameSettings));
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
        // Play death audio
        try {
            File file = new File(new URI(Objects.requireNonNull(GamePane.class.getResource("Death.wav")).toString()));

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Display game over screen
        this.gameOverPane.setVisible(true);
    }

    private void onVictory() {
        // Play victory audio
        try {
            File file = new File(new URI(Objects.requireNonNull(GamePane.class.getResource("Victory.wav")).toString()));

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.start();

            // End clip when new game starts
            this.gameProperty.addListener(_ -> clip.close());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Flag all left-over bombs
        Game game = this.getGame();
        List<List<Tile>> grid = game.getBoard().getGrid();
        List<Tile> tiles = Tile.toTiles(grid);

        tiles.forEach(tile -> {
            if (tile.getItem() instanceof MineItem) tile.setLocked(true);
        });
    }
}
