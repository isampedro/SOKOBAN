import java.util.LinkedList;
import java.util.List;

public class DFS {

    public static Node solve(Node current, List<Pair> positions) {
        int TILES = 7 - 2;
        int BOARD = TILES*TILES;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;
        List<Node> solutions = new LinkedList<>();
        if( current.getMovements() != null && current.getMovements().size() > MAX_MOVEMENTS) {
            return null;
        } else if( current.getBoard().isOver()) {
            return current;
        } else {
            List<Sokoban> childBoards = current.getBoard().getPossibleMoves();
            Node childNode, solution;
            List<Pair> currentPositions;
            List<Directions> movements;
            if( current.getBoard().isMovingBox() ) {
                positions = new LinkedList<>();
            }
            if ( positions.contains(current.getBoard().getPlayer()) ) {
                return null;
            }
            for (Sokoban childBoard : childBoards) {
                childNode = new Node(childBoard, current, current.getDepth() + 1);
                movements = new LinkedList<>(current.getMovements());
                movements.add(childNode.getBoard().getDirection());
                childNode.setMovements(movements);
                currentPositions = new LinkedList<>(positions);
                currentPositions.add(current.getBoard().getPlayer());
                solution = solve(childNode, currentPositions);
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
