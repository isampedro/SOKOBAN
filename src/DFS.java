import java.util.*;

public class DFS {
    public static Node solve(Sokoban game) {
        int TILES_X = 15,TILES_Y = 10;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;

        Map<Snapshot, Integer> visitedNodes = new HashMap<>();
        Stack<Node> frontierNodes = new Stack<>();
        Node startNode = new Node(game.snapshot(), null, 0);
        frontierNodes.push(startNode);
        Node aux = null;

        while( !frontierNodes.isEmpty() ) {
            aux = frontierNodes.pop();
            visitedNodes.put(aux.getSnapshot(), aux.getDepth());
            if( new Sokoban(aux.getSnapshot()).isOver() ) {
                return aux;
            }
            if( aux.getDepth() <= MAX_MOVEMENTS) {
                List<Snapshot> moves = new Sokoban(aux.getSnapshot()).getPossibleMoves();
                for (Snapshot move : moves) {
                    startNode = new Node(move, aux, aux.getDepth() + 1);
                    if( !contains(startNode, visitedNodes) && !contains(frontierNodes, startNode)) {
                        frontierNodes.push(startNode);
                    }
                }
            }
        }
        return null;
    }

    private static boolean contains( Iterable<Node> nodeList, Node node ) {
        for (Node nodeFromList : nodeList) {
            if( nodeFromList.equals( node ) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean contains(Node node, Map<Snapshot, Integer> nodesIterable) {
        Integer depth;
        if( nodesIterable.containsKey(node.getSnapshot())) {
            depth = nodesIterable.get(node.getSnapshot());
            return node.getDepth() > depth;
        }
        return false;
    }
}