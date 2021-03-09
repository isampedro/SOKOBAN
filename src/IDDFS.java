import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IDDFS {
    private static final List<Node> endNodes = new LinkedList<>();
    private static final List<Node> visited = new LinkedList<>();
    private static boolean DLS( Node node, int limit ) {

        if( node.getBoard().isOver() ) {
            endNodes.add(node);
            return true;
        }

        if( node.getDepth() == limit ) {
            return false;
        }

        List<Sokoban> moves = node.getBoard().getPossibleMoves();
        List<Node> childNodes = new LinkedList<>();
        Node nodeAux;
        for( Sokoban move: moves ){
            nodeAux = new Node(move, node, node.getDepth() + 1, new LinkedList<>(node.getPositions()), new LinkedList<>(node.getMovements()));
            nodeAux.getMovements().add(move.getDirection());
            nodeAux.getPositions().add(move.getPlayer());
            childNodes.add(nodeAux);
        }

        boolean finished = false;
        for (Node childNode : childNodes) {
            if( !contains(visited, childNode) ) {
                visited.add(childNode);
                if( DLS(childNode, limit) ) {
                    finished = true;
                } else {
                    visited.remove(childNode);
                }
            }
        }

        return finished;
    }


    public static Node solve(Sokoban game) {
        int TILES = 7 - 2;
        int BOARD = TILES*TILES;
        int BOXES = 2;
        int MAX_MOVEMENTS = 50;
        int LIMIT = 20;
        int limit = LIMIT;
        List<Pair> positions;
        List<Directions> movements;
        Node startNode = new Node(game, null, 0, new LinkedList<>(), new LinkedList<>());

        positions = new LinkedList<>();
        positions.add(game.getPlayer());
        startNode.setPositions(positions);
        startNode.setMovements(new LinkedList<>());

        Queue<Node> frontier = new LinkedList<>();


        frontier.offer(startNode);
        Node aux = null;

        for( limit = 0; limit < MAX_MOVEMENTS; limit+= LIMIT) {
            boolean isOver = DLS(startNode, limit);
            if( isOver ) {
                Node min = endNodes.get(0);
                for (Node endNode : endNodes) {
                    if( min.getMovements().size() > endNode.getMovements().size() ) {
                        min = endNode;
                    }
                }
                return min;
            }
        }
        return null;
    }

    private static boolean contains( Iterable<Node> nodeList, Node node ) {
        for (Node nodeFromList : nodeList) {
            if( nodeFromList.equalsNotDepth( node ) ) {
                return true;
            }
        }
        return false;
    }
}
