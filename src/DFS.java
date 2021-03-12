import java.util.LinkedList;
import java.util.List;

public class DFS {

    public static Node solve(Node current, List<Pair> positions) {
        int TILES_X = 10, TILES_Y = 15;
        int BOARD = 60;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD * BOXES;


        if (current.getMovements() != null && current.getMovements().size() > MAX_MOVEMENTS) {
            return null;
        } else if (new Sokoban(current.getSnapshot()).isOver()) {
            return current;
        } else {
            List<Snapshot> childBoards = new Sokoban(current.getSnapshot()).getPossibleMoves();
            Node childNode, solution;
            List<Pair> currentPositions;
            List<Directions> movements;
            if (new Sokoban(current.getSnapshot()).isMovingBox()) {
                positions = new LinkedList<>();
            }
            if (positions.contains(new Sokoban(current.getSnapshot()).getPlayer())) {
                return null;
            }
            for (Snapshot childBoard : childBoards) {
                childNode = new Node(childBoard, current, current.getDepth() + 1);
                movements = new LinkedList<>(current.getMovements());
                movements.add(new Sokoban(childNode.getSnapshot()).getDirection());
                childNode.setMovements(movements);
                currentPositions = new LinkedList<>(positions);
                currentPositions.add(new Sokoban(current.getSnapshot()).getPlayer());
                solution = solve(childNode, currentPositions);
                if (solution != null) {
                    return solution;
                }
            }
            return null;
        }
    }
}
