package dev.laughingcat27.minesweeper2.fxgui.tile;

import dev.laughingcat27.minesweeper2.model.tile.Tile;
import dev.laughingcat27.minesweeper2.model.tile.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class TileNodeFactory {

    public static TileNode createTileNode(Tile tile, int x, int y) {
        return new TileNode(tile, x, y);
    }

    public static List<TileNode> createTileNodes(List<List<Tile>> grid) {
        List<TileNode> tileNodes = new ArrayList<>();

        for (List<Tile> row : grid) {
            int y = grid.indexOf(row);
            for (Tile tile : row) {
                int x = row.indexOf(tile);
                TileNode tileNode = createTileNode(tile, x, y);
                tileNodes.add(tileNode);
            }
        }
        return tileNodes;
    }
}
