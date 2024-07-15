package dev.laughingcat27.minesweeper.fxgui.tile;

import dev.laughingcat27.minesweeper.model.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileNodeFactory {

    public static TileNode createTileNode(Tile tile, int x, int y) {
        return new TileNode(tile, x, y);
    }

    public static List<TileNode> createTileNodes(List<List<Tile>> grid) {
        List<TileNode> tileNodes = new ArrayList<>();

        Logger logger = Logger.getLogger(TileNodeFactory.class.getSimpleName());
        logger.setLevel(Level.WARNING);
        //logger.warning("Hey, the TileNodeFactory is fucking laggy. Don't believe me? see this:");

        //System.out.println("Starting to create tilenodes");

        for (List<Tile> column : grid) {
            int x = grid.indexOf(column);
            for (Tile tile : column) {
                int y = column.indexOf(tile);
                TileNode tileNode = createTileNode(tile, x, y);
                tileNodes.add(tileNode);
            }
        }

        System.out.println("Done creating tilenodes");

        return tileNodes;
    }
}
