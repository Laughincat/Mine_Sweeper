package dev.laughingcat27.minesweeper.model.game;

import javafx.beans.property.*;

public class GameStats {
    private BooleanProperty victoryProperty;
    private BooleanProperty gameOverProperty;
    private IntegerProperty scoreProperty;
    private IntegerProperty healthProperty;

    public GameStats() {
        this.victoryProperty = new SimpleBooleanProperty();
        this.gameOverProperty = new SimpleBooleanProperty();
        this.scoreProperty = new SimpleIntegerProperty();
        this.healthProperty = new SimpleIntegerProperty(100);

        // When health is or is less than 0, game over!
        this.gameOverProperty.bind(this.healthProperty.lessThanOrEqualTo(0));

        // When victory print it out lol
        this.victoryProperty.addListener((_, _, victory) -> {
            if (victory) {
                this.printStats("== PLAYER BEAT THE GAME! ==");
            }
        });

        // When player loses print it out
        this.gameOverProperty.addListener((_, _, gameOver) -> {
            if (gameOver) {
                this.printStats("!! PLAYER LOST LMAO !!");
            }
        });
    }

    public BooleanProperty getVictoryProperty() {
        return this.victoryProperty;
    }

    public BooleanProperty getGameOverProperty() {
        return this.gameOverProperty;
    }

    public IntegerProperty getScoreProperty() {
        return this.scoreProperty;
    }

    public IntegerProperty getHealthProperty() {
        return this.healthProperty;
    }

    public int getScore() {
        return this.scoreProperty.get();
    }

    public int getHealth() {
        return this.healthProperty.get();
    }

    public void setVictory(boolean victory) {
        this.victoryProperty.set(victory);
    }

    public void setScore(int score) {
        this.scoreProperty.set(score);
    }

    public void setHealth(int health) {
        this.healthProperty.set(health);
    }

    private void printStats() {

    }

    private void printStats(Object object) {
        System.out.println(object);
        this.printStats();
    }
}
