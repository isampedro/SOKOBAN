package Heuristics;

import Sokoban.*;

import java.util.List;

public class ManhattanDistancePBO implements Heuristic{
    @Override
    public int evaluate(Snapshot gamePhoto) {
        Sokoban game = new Sokoban(gamePhoto);
        Pair player = game.getPlayer();
        int aux;
        List<Pair> gameObjectives = game.getObjectivesPositions();
        int minPlayerBox = Integer.MAX_VALUE;
        int minBoxGoal = Integer.MAX_VALUE;
        Pair nearestBox = null;

        for (Pair box : game.getBoxesPositions()) {
            if (!gameObjectives.contains(box)) {
                aux = manhattanDistance(player, box);
                if (aux < minPlayerBox) {
                    minPlayerBox = aux;
                    nearestBox = box;
                }
            }
        }

        if (nearestBox == null) {
            return 0;
        }

        //Ahora enceuntro el objetivo más cercano (no ocupado) para nearestbox

        for (Pair objective : game.getObjectivesPositions()) {
            aux = manhattanDistance(nearestBox, objective);
            if (aux < minBoxGoal) {
                minBoxGoal = aux;
            }
        }

        return minPlayerBox + minBoxGoal;

    }


    private int manhattanDistance(Pair box, Pair objective) {
        return Math.abs(box.getX() - objective.getX()) + Math.abs(box.getY() - objective.getY());
    }
}
