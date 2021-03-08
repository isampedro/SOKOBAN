import java.util.LinkedList;
import java.util.List;

public class DFS {

    public static Node solve(Node current, int depth, List<Pair> positions) {
        int TILES = 7*7;
        int BOXES = 4;
        int MAX_MOVEMENTS = TILES*BOXES;
        int frontierNodes = 0;
        System.out.println(depth);
        if(current.getBoard().isOver()) {
            current.setMovements(new LinkedList<>());
            return current;
        } else if (depth > MAX_MOVEMENTS) {
            return null;
        } else {
            if( positions.contains(current.getBoard().getPlayer())) {
                return null;
            } else {
                positions.add(current.getBoard().getPlayer());
            }
            List<Sokoban> possibleMoves = current.getBoard().getPossibleMoves();
            Node possibleChildNode;
            frontierNodes += possibleMoves.size();
            for (Sokoban possibleMove : possibleMoves) {
                possibleChildNode = new Node( possibleMove, current, depth + 1);
                possibleChildNode = solve(possibleChildNode, depth + 1, positions);
                if( current.getBoard().isMovingBox() ) {
                    positions = new LinkedList<>();
                }
                if( possibleChildNode != null ) {
                    current.setMovements(possibleChildNode.getMovements());
                    return current;
                }
            }
            return null;
        }
    }

}
