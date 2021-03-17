package Heuristics;

import Sokoban.*;

public class TunnelDirection implements Heuristic{
    @Override
    public int evaluate(Snapshot gamePhoto) {
        Sokoban game = new Sokoban(gamePhoto);
        boolean isOnObjective;
        for (Pair box : game.getBoxesPositions()) {
            Directions tunnelDirection = getTunnelDirection(game, box);
            if (tunnelDirection != null ) {
                isOnObjective = false;
                for (Pair objective : game.getObjectivesPositions()) {
                    isOnObjective |= objective.equals(box);
                }
                if (!isOnObjective && game.getDirection() != tunnelDirection && game.isMovingBox() ) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return 0;
    }

    private Directions getTunnelDirection(Sokoban game, Pair box ) {
        if( game.getSurroundingPaths(box).size() == 1 && game.hasParallelWalls(box) && game.isPlayerAround(box) ) {

            return game.getPlayer().direction(box);
        }
        return null;
    }
}
