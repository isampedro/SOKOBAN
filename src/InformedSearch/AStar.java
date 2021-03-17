package InformedSearch;
import Heuristics.Heuristic;
import Sokoban.*;
import java.util.*;

public class AStar {

    public static Node solve(Sokoban game, Pair boardDimensions, int boxes, Heuristic heuristic) {
        int MAX_MOVEMENTS = boxes* boardDimensions.getX()* boardDimensions.getY();

        PriorityQueue<Node> frontierNodes = new PriorityQueue<>((n1, n2) -> {
            if( n1.evaluate() == Integer.MAX_VALUE ) {
                return Integer.MAX_VALUE;
            }
            if( n2.evaluate() == Integer.MAX_VALUE ) {
                return Integer.MIN_VALUE;
            }
            int ans = n1.evaluate() + n1.getDepth() - n2.evaluate() - n2.getDepth();
            if( ans == 0 ) {
                return n1.evaluate() - n2.evaluate();
            } else {
                return ans;
            }
        });
        Map<Snapshot, Integer> visited = new HashMap<>();
        Node currentNode, childNode;
        Node startNode = new Node(game.snapshot(), null, 0, heuristic);
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
                    childNode = new Node(move, currentNode, currentNode.getDepth() + 1, heuristic);
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
