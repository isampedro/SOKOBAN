import java.util.*;

public class DFS2 {
    public static Node solve(Sokoban game) {
        int TILES = 7 - 2;
        int BOARD = TILES*TILES;
        int BOXES = 1;
        int MAX_MOVEMENTS = BOARD*BOXES;

        Set<Node> visitedNodes = new HashSet<>();
        Stack<Node> frontierNodes = new Stack<>();
        Node startNode = new Node(game, null, 0, new LinkedList<>(), new LinkedList<>());
        startNode.setMovements(new LinkedList<>());
        frontierNodes.push(startNode);
        Node aux = null;
        List<Directions> movements;
        Stack<Node> finishedNodes = new Stack<>();

        while( !frontierNodes.isEmpty() ) {
            aux = frontierNodes.pop();
            visitedNodes.add(aux);
            if( aux.getBoard().isOver() ) {
                aux.getBoard().show();
                return aux;
            }
            if( aux.getMovements().size() <= MAX_MOVEMENTS) {
                List<Sokoban> moves = aux.getBoard().getPossibleMoves();
                List<Node> childNodes = new LinkedList<>();
                for (Sokoban move : moves) {
                    movements = new LinkedList<>(aux.getMovements());
                    movements.add(move.getDirection());
                    childNodes.add(new Node(move, aux, aux.getDepth() + 1, null, movements));
                }
                for (Node childNode : childNodes) {
                    if( !contains(visitedNodes, childNode) && !contains(frontierNodes, childNode)) {
                        frontierNodes.push(childNode);
                    }
                }
            }
        }
        return aux;
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
