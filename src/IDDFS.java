import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IDDFS {
    private static  Node endNode;
    private static final List<Node> visited = new LinkedList<>();
    private static boolean DLS( Node node, int limit ) {

        if( new Sokoban(node.getSnapshot()).isOver() ) {
            endNode = node;
            return true;
        }

        if( node.getDepth() == limit ) {
            return false;
        }

        List<Snapshot> moves = new Sokoban(node.getSnapshot()).getPossibleMoves();
        List<Node> childNodes = new LinkedList<>();
        Node nodeAux;
        for( Snapshot move: moves ){
            nodeAux = new Node(move, node, node.getDepth() + 1, new LinkedList<>(node.getPositions()), new LinkedList<>(node.getMovements()));
            nodeAux.getMovements().add(move.getDirection());
            nodeAux.getPositions().add(new Sokoban(move).getPlayer());
            childNodes.add(nodeAux);
        }


        for (Node childNode : childNodes) {
            if( !contains(childNode) ) {
                visited.add(childNode);
                if( DLS(childNode, limit) ) {
                    return true;
                } else {
                    visited.remove(childNode);
                }
            }
        }

        return false;
    }


    public static Node solve(Sokoban game) {
        int TILES = 7 - 2;
        int BOARD = TILES*TILES;
        int BOXES = 2;
        int MAX_MOVEMENTS = 60;
        int LIMIT = 20;
        int limit = LIMIT;
        List<Pair> positions;
        List<Directions> movements;
        Node startNode = new Node(game.snapshot(), null, 0, new LinkedList<>(), new LinkedList<>());

        positions = new LinkedList<>();
        positions.add(game.getPlayer());
        startNode.setPositions(positions);
        startNode.setMovements(new LinkedList<>());

        Queue<Node> frontier = new LinkedList<>();


        frontier.offer(startNode);
        Node aux = null;

        for( limit = 0; limit < MAX_MOVEMENTS; limit ++) {
            boolean isOver = DLS(startNode, limit);
            if( isOver ) {
                return endNode;
            }
        }
        return null;
    }

    private static boolean contains(Node node) {
        for (Node nodeFromList : IDDFS.visited) {
            if( nodeFromList.equals( node ) ) {
                return true;
            }
        }
        return false;
    }
}
