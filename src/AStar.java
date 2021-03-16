import java.util.*;

public class AStar {

    public static Node solve(Sokoban game, Heuristic heuristic) {
        int TILES_X = 10, TILES_Y = 15;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;

        PriorityQueue<Node> frontierNodes = new PriorityQueue<>((n1, n2) -> {
            int ans = n1.evaluate() + n1.getDepth() - n2.evaluate() - n2.getDepth();
            if( ans == 0 ) {
                return n1.evaluate() - n2.evaluate();
            } else {
                return ans;
            }
        });
        Map<Snapshot, Integer> visited = new HashMap<>();
        List<Directions> movements;
        Node currentNode, childNode;
        Node startNode = new Node(game.snapshot(), null, 0,new LinkedList<>(), heuristic);
        frontierNodes.add(startNode);

        while( !frontierNodes.isEmpty() ) {
            currentNode = frontierNodes.poll();
            visited.put(currentNode.getSnapshot(), currentNode.getDepth());

            if( new Sokoban(currentNode.getSnapshot()).isOver() ) {
                return currentNode;
            }

            if( currentNode.getDepth() <= MAX_MOVEMENTS ) {
                List<Snapshot> moves = new Sokoban(currentNode.getSnapshot()).getPossibleMoves();
                for (Snapshot move : moves) {
                    movements = new LinkedList<>(currentNode.getMovements());
                    movements.add(move.getDirection());
                    childNode = new Node(move, currentNode, currentNode.getDepth() + 1, movements, heuristic);
                    if( !contains(frontierNodes, childNode) && !contains(childNode, visited) ) {
                        frontierNodes.offer(childNode);
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
