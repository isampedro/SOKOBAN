import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DFS {

    public static Node solve(Node current, int depth, List<Pair> positions) {
        int TILES = 7;
        int BOARD = TILES*TILES;
        int BOXES = 4;
        int MAX_MOVEMENTS = BOARD*BOXES;
        List<Node> solutions = new LinkedList<>();
        if( current.getBoard().isOver()) {
            return current;
        } else if( current.getMovements() != null && current.getMovements().size() > MAX_MOVEMENTS) {
            return null;
        } else {
            List<Sokoban> childBoards = current.getBoard().getPossibleMoves();
            Node childNode, solution;
            List<Pair> currentPositions;
            if( current.getBoard().isMovingBox() ) {
                positions = new LinkedList<>();
            }
            if (positions.contains(current.getBoard().getPlayer())) {
                return null;
            }
            for (Sokoban childBoard : childBoards) {
                childNode = new Node(childBoard, current, depth + 1);
                if( current.getMovements() == null ) {
                    current.setMovements(new LinkedList<>());
                }
                List<Directions> movements = new LinkedList<>(current.getMovements());
                movements.add(childNode.getBoard().getDirection());
                childNode.setMovements(movements);
                currentPositions = positions;
                currentPositions.add(current.getBoard().getPlayer());
                solution = solve(childNode, depth + 1, currentPositions);
                if( solution != null ) {
                    solutions.add(solution);
                }
            }
            if( solutions.isEmpty() ) {
                return null;
            }
            Node min = solutions.get(0);

            for (Node node : solutions) {
                if( min.getMovements().size() > node.getMovements().size() ) {
                    min = node;
                }
            }

            return min;
        }
    }

}
