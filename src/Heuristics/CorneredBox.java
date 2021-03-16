package Heuristics;

import Sokoban.*;

import java.util.List;

public class CorneredBox implements Heuristic {
    @Override
    public int evaluate(Snapshot gamePhoto) {
        Sokoban game = new Sokoban(gamePhoto);
        for (Pair box : game.getBoxesPositions()) {
            if( isCornered(game, box) ) {
                return Integer.MAX_VALUE;
            }
        }

        return 0;
    }

    private boolean isCornered( Sokoban game, Pair box ) {
        List<Pair> walls = game.getSurroundingWalls(box);
        if( walls.contains(new Pair(box.getX()-1, box.getY())) && (walls.contains(new Pair(box.getX(), box.getY()+1)) || walls.contains(new Pair(box.getX(), box.getY()-1)))) {
            return true;
        }
        if( walls.contains(new Pair(box.getX()+1, box.getY())) && (walls.contains(new Pair(box.getX(), box.getY()+1)) || walls.contains(new Pair(box.getX(), box.getY()-1)))) {
            return true;
        }
        if( walls.contains(new Pair(box.getX(), box.getY()-1)) && (walls.contains(new Pair(box.getX()-1, box.getY())) || walls.contains(new Pair(box.getX()+1, box.getY())))) {
            return true;
        }
        return walls.contains(new Pair(box.getX(), box.getY() + 1)) && (walls.contains(new Pair(box.getX() - 1, box.getY())) || walls.contains(new Pair(box.getX() + 1, box.getY())));
    }
}
