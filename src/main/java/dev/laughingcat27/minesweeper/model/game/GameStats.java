package dev.laughingcat27.minesweeper.model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameStats {
    private IntegerProperty scoreProperty;
    private IntegerProperty healthProperty;

    public GameStats() {
        this.scoreProperty = new SimpleIntegerProperty();
        this.healthProperty = new SimpleIntegerProperty(100);
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

    public void setScore(int score) {
        this.scoreProperty.set(score);
    }

    public void setHealth(int health) {
        this.healthProperty.set(health);
    }
}
