package Heuristics;
import Sokoban.*;

public class ManhattanDistance implements Heuristic{
    @Override
    public int evaluate(Snapshot gamePhoto) {
        Sokoban game = new Sokoban(gamePhoto);
        int total = 0, min, aux;
        for (Pair box : game.getBoxesPositions()) {
            min = Integer.MAX_VALUE;
            for (Pair objective : game.getObjectivesPositions()) {
                aux = manhattanDistance(box, objective);
                if (aux < min) {
                    min = aux;
                }
            }
            total += min;
        }

        return total;
    }

    private int manhattanDistance(Pair box, Pair objective) {
        return Math.abs(box.getX() - objective.getX()) + Math.abs(box.getY() - objective.getY());
    }
}
