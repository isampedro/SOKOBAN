import java.util.*;

public class GlobalGreedySearch {
    public static Node solve(Sokoban game, Heuristic heuristic) {
        int TILES_X = 10, TILES_Y = 15;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;

        PriorityQueue<Node> frontierNodes = new PriorityQueue<>(Comparator.comparingInt(Node::evaluate));
        Set<Node> visitedNodes = new HashSet<>();
        List<Directions> movements;
        Node currentNode, childNode;
        Node startNode = new Node(game.snapshot(), null, 0,new LinkedList<>(), heuristic);
        frontierNodes.add(startNode);

        while( !frontierNodes.isEmpty() ) {
            currentNode = frontierNodes.poll();
            visitedNodes.add(currentNode);

            if( new Sokoban(currentNode.getSnapshot()).isOver() ) {
                return currentNode;
            }

            if( currentNode.getDepth() <= MAX_MOVEMENTS ) {
                List<Snapshot> moves = new Sokoban(currentNode.getSnapshot()).getPossibleMoves();
                for (Snapshot move : moves) {
                    movements = new LinkedList<>(currentNode.getMovements());
                    movements.add(move.getDirection());
                    childNode = new Node(move, currentNode, currentNode.getDepth() + 1, movements, heuristic);
                    if( !contains(frontierNodes, childNode) && !contains(visitedNodes, childNode) ) {
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
}
