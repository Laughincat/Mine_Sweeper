package dev.laughingcat27.minesweeper.model.game.gamesettings;

import dev.laughingcat27.minesweeper.model.game.Game;
import dev.laughingcat27.minesweeper.model.game.SimpleGame;

public class GameSettings {
    private String name;
    private int columns;
    private int rows;
    private int bombs;
    private int bombDamage;

    public GameSettings(String name, int columns, int rows, int bombs, int bombDamage) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
        this.bombs = Math.min(bombs, columns * rows - 1);
        this.bombDamage = bombDamage;
    }

    public String getName() {
        return this.name;
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
