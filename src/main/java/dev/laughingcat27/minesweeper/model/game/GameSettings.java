package dev.laughingcat27.minesweeper.model.game;

public class GameSettings {
    private int columns;
    private int rows;
    private int bombs;

    public GameSettings(int columns, int rows, int bombs) {
        this.columns = columns;
        this.rows = rows;
        this.bombs = bombs;
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
}
