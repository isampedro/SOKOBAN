import java.util.*;

public class BFS {
    public static Node solve(Sokoban game, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boxes*boardDimensions.getY()* boardDimensions.getX();

        List<Directions> movements;
        Map<Snapshot, Integer> visited = new HashMap<>();
        Queue<Node> frontier = new LinkedList<>();
        Node startNode = new Node(game.snapshot(), null, 0, new LinkedList<>(), new LinkedList<>());
        startNode.setMovements(new LinkedList<>());
        frontier.offer(startNode);
        Node aux;

        while( !frontier.isEmpty() ) {
            aux = frontier.poll();
            visited.put(aux.getSnapshot(), aux.getDepth());
            if( new Sokoban(aux.getSnapshot()).isOver() ) {
                return aux;
            }
            if( aux.getMovements().size() <= MAX_MOVEMENTS) {
                List<Snapshot> moves = new Sokoban(aux.getSnapshot()).getPossibleMoves();
                for (Snapshot move : moves) {
                    movements = new LinkedList<>(aux.getMovements());
                    movements.add(move.getDirection());
                    startNode = new Node(move, aux, aux.getDepth() + 1, null, movements );
                    if( !contains(startNode, visited) && !contains(frontier, startNode) ) {
                        frontier.offer(startNode);
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