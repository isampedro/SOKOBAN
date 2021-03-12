import java.util.*;

public class DFS2 {
    public static Node solve(Sokoban game) {
        int TILES_X = 15,TILES_Y = 10;
        int BOARD = 60;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;

        Set<Node> visitedNodes = new HashSet<>();
        Stack<Node> frontierNodes = new Stack<>();
        Node startNode = new Node(game.snapshot(), null, 0, new LinkedList<>(), new LinkedList<>());
        startNode.setMovements(new LinkedList<>());
        frontierNodes.push(startNode);
        Node aux = null;
        List<Directions> movements;
        Stack<Node> finishedNodes = new Stack<>();

        while( !frontierNodes.isEmpty() ) {
            aux = frontierNodes.pop();
            visitedNodes.add(aux);
            if( new Sokoban(aux.getSnapshot()).isOver() ) {
                return aux;
            }
            if( aux.getMovements().size() <= MAX_MOVEMENTS) {
                List<Snapshot> moves = new Sokoban(aux.getSnapshot()).getPossibleMoves();
                List<Node> childNodes = new LinkedList<>();
                for (Snapshot move : moves) {
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
            if( nodeFromList.equals( node ) ) {
                return true;
            }
        }
        return false;
    }
}
