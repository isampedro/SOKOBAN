import java.util.LinkedList;
import java.util.List;

public class DFS {

    public static Node solve(Node current, List<Node> frontier, List<Node> visited) {
        int TILES_X = 10, TILES_Y = 15;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD * BOXES;
        Node currentChild;

        if( new Sokoban(current.getSnapshot()).isOver() ) {
            return current;
        }

        if( current.getDepth() < MAX_MOVEMENTS ) {
            List<Snapshot> moves = new Sokoban(current.getSnapshot()).getPossibleMoves();
            for (Snapshot move : moves) {
                currentChild = new Node(move, current, current.getDepth()+1);
                visited.add(currentChild);
                currentChild = solve(currentChild, frontier, visited);
                if( currentChild != null && new Sokoban(currentChild.getSnapshot()).isOver() ) {
                    return currentChild;
                }
            }
        }
        return null;
    }
}
