package UninformedSearch;
import java.util.*;
import Sokoban.*;

public class BFS {
    public static Node solve(Sokoban game, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boxes*boardDimensions.getY()* boardDimensions.getX();

        Map<Snapshot, Integer> visited = new HashMap<>();
        Queue<Node> frontier = new LinkedList<>();
        Node startNode = new Node(game.snapshot(), null, 0);
        frontier.offer(startNode);
        Node aux;
        int expandedNodes = 0;

        while( !frontier.isEmpty() ) {
            aux = frontier.poll();
            visited.put(aux.getSnapshot(), aux.getDepth());
            if( new Sokoban(aux.getSnapshot()).isOver() ) {
                return new Node(aux, expandedNodes, frontier.size());
            }
            if( aux.getDepth() <= MAX_MOVEMENTS) {
                expandedNodes++;
                List<Snapshot> moves = new Sokoban(aux.getSnapshot()).getPossibleMoves();
                for (Snapshot move : moves) {
                    startNode = new Node(move, aux, aux.getDepth() + 1);
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