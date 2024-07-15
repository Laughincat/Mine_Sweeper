package dev.laughingcat27.minesweeper.model.board;

import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.List;

public class SimpleBoard extends Board {

    public SimpleBoard(int columns, int rows, int bombs) {
        super(columns, rows, bombs);

        List<Tile> tiles = Tile.toTiles(super.getGrid());

        // Whenever a tile gets opened, check for victory
        tiles.forEach(tile -> tile.getOpenProperty().addListener(_ -> this.checkForVictory()));

        // When a tile's item changes, check for victory
        tiles.forEach(tile -> tile.getItemProperty().addListener(_ -> this.checkForVictory()));
    }

    private boolean areAllNonBombsOpen() {
        List<Tile> nonBombTiles = Tile.getNonBombTiles(Tile.toTiles(super.getGrid()));

        //System.out.println(nonBombTiles.size());

        int nonBombCount = nonBombTiles.size();
        int openNonBombCount = Tile.getOpenTiles(nonBombTiles).size();

        // Are all non-bombs open?
        return openNonBombCount == nonBombCount;
    }

    @Override
    protected void checkForVictory() {
        if (this.areAllNonBombsOpen()) this.getGame().getGameStats().setVictory(true);
    }

    // Wtf is this code doing here?

    // Oh wait this used to be the first board class but then it got renamed and another abstract Board class was made

    /*
    public Vector<Integer> getTilePosition(Tile tile) {
        for (List<Tile> row : this.grid) {
            for (Tile tile1 : row) {
                if (tile1.equals(tile)) {
                    Vector<Integer> position = new Vector<>();
                    position.add(row.indexOf(tile1), this.grid.indexOf(row));
                    return position;
                }
            }
        }
        return null;
    }

    public List<Tile> getNeighbouringTiles(Tile tile) {
        Vector<Integer> position = getTilePosition(tile);
        int x = position.getFirst();
        int y = position.getLast();

        List<Tile> tiles = new ArrayList<>();

        tiles.add(grid.get(x-1).get(y-1));
        tiles.add(grid.get(x).get(y-1));
        tiles.add(grid.get(x+1).get(y-1));

        tiles.add(grid.get(x-1).get(y));
        tiles.add(grid.get(x+1).get(y));

        tiles.add(grid.get(x-1).get(y+1));
        tiles.add(grid.get(x).get(y+1));
        tiles.add(grid.get(x+1).get(y+1));

        return tiles;
    }

    public List<Tile> getDetectableTiles(List<Tile> tiles) {
        List<Tile> tiles1 = new ArrayList<>();

        tiles.forEach(tile -> {
            if (tile.getItem().getDetectable()) {
                tiles1.add(tile);
            }
        });
        return tiles1;
    }
     */
}
