package dev.laughingcat27.minesweeper.model.board;

public class SimpleBoard extends Board {

    public SimpleBoard(int columns, int rows, int bombs) {
        super(columns, rows, bombs);
    }


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
