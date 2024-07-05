package dev.laughingcat27.minesweeper.fxgui.game;

import dev.laughingcat27.minesweeper.fxgui.board.BoardPane;
import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.game.GameSettings;
import dev.laughingcat27.minesweeper.model.game.GameStats;
import dev.laughingcat27.util.fx.controls.IntegerField;
import dev.laughingcat27.util.fx.fxmlloader.ExtendedFxmlLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class GamePane extends BorderPane {
    private static Image neutralImage;
    private static Image deadImage;

    // Top bar
    @FXML
    private ImageView characterImageView;
    @FXML
    private Label healthLabel;

    // Game settings
    @FXML
    private Pane settingsPane;
    @FXML
    private IntegerField columnsField;
    @FXML
    private IntegerField rowsField;
    @FXML
    private IntegerField bombsField;
    @FXML
    private IntegerField bombDamageField;
    @FXML
    private Button startButton;

    // Board
    @FXML
    private BoardPane boardPane;

    // Game over
    @FXML
    private GameOverPane gameOverPane;

    static {
        GamePane.neutralImage = new Image(String.valueOf(GamePane.class.getResource("Neutral.png")));
        GamePane.deadImage = new Image(String.valueOf(GamePane.class.getResource("Dead.png")));
    }

    public GamePane() {
        new ExtendedFxmlLoader().loadCustomObject(this, GamePane.class);

        this.characterImageView.setImage(GamePane.neutralImage);

        // When the game has completed, return to settings screen.
        this.gameOverPane.getContinueButton().setOnAction(actionEvent -> {

            // Shitty workaround for presentation purposes
            this.characterImageView.setImage(GamePane.neutralImage);

            this.gameOverPane.setVisible(false);
            this.boardPane.setVisible(false);
            this.settingsPane.setVisible(true);
        });
    }

    @FXML
    private void onStartButtonAction() {
        int columns = this.columnsField.getInteger();
        int rows = this.rowsField.getInteger();
        int bombs = this.bombsField.getInteger();
        int bombDamage = this.bombDamageField.getInteger();

        // Create gameSettings
        GameSettings gameSettings = new GameSettings(columns, rows, bombs, bombDamage);

        // Create game
        Game game = new Game(gameSettings);

        // Set the new board in the board pane
        this.boardPane.setBoard(game.getBoard());

        // Display board
        this.boardPane.setVisible(true);
        this.settingsPane.setVisible(false);

        GameStats gameStats = game.getGameStats();

        // Bind health label to health property
        StringBinding healthBinding = gameStats.getHealthProperty().asString();
        this.healthLabel.textProperty().bind(Bindings.concat("Health: ", healthBinding));

        // When player gets a game over, show dead image
        gameStats.getGameOverProperty().addListener((_, _, gameOver) -> {
            if (gameOver) this.onGameOver();
        });
    }

    private void onGameOver() {
        // Set the character image
        this.characterImageView.setImage(GamePane.deadImage);

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
}
