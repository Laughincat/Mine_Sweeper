package dev.laughingcat27.minesweeper.model.game;

public class GameSettings {
    private int columns;
    private int rows;
    private int bombs;
    private int bombDamage;

    public GameSettings(int columns, int rows, int bombs, int bombDamage) {
        this.columns = columns;
        this.rows = rows;
        this.bombs = bombs;
        this.bombDamage = bombDamage;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getRows() {
        return this.rows;
    }

    public int getBombs() {
        return this.bombs;
    }

    public int getBombDamage() {
        return this.bombDamage;
    }
}
