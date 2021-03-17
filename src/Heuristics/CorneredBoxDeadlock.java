package Heuristics;

import Sokoban.*;

import java.util.List;

public class CorneredBoxDeadlock implements Heuristic {
    @Override
    public int evaluate(Snapshot gamePhoto) {
        Sokoban game = new Sokoban(gamePhoto);
        boolean isOnObjective;
        for (Pair box : game.getBoxesPositions()) {
            if( isCornered(game, box) ) {
                isOnObjective = false;
                for( Pair objective: game.getObjectivesPositions() ) {
                    isOnObjective |= objective.equals(box);
                }
                if( !isOnObjective ) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        return 0;
    }

    private boolean isCornered( Sokoban game, Pair box ) {
        List<Pair> walls = game.getSurroundingWalls(box);
        if( contains(box.getX()-1, box.getY(), walls) && (contains(box.getX(), box.getY()+1, walls) || contains(box.getX(), box.getY()-1, walls))) {
            return true;
        }
        if( contains(box.getX()+1, box.getY(), walls) && (contains( box.getX(), box.getY()+1, walls) || contains(box.getX(), box.getY()-1, walls))) {
            return true;
        }
        if( contains(box.getX(), box.getY()-1, walls) && (contains(box.getX()-1, box.getY(), walls) || contains(box.getX()+1, box.getY(), walls))) {
            return true;
        }
        return contains(box.getX(), box.getY() + 1, walls) && (contains(box.getX() - 1, box.getY(), walls) || contains(box.getX() + 1, box.getY(), walls));
    }

    private boolean contains(int x, int y, List<Pair> walls ) {
        for (Pair wall : walls) {
            if( wall.getX() == x && wall.getY() == y ) {
                return true;
            }
        }
        return false;
    }
}
