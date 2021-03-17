package InformedSearch;

import Heuristics.Heuristic;
import Sokoban.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class IDAStar2 {
    private static Node endNode;
    private static final Stack<Node> path = new Stack<>();

    public static Node solve(Sokoban game, Heuristic heuristic, Pair boardDimensions, int boxes) {
        Node startNode = new Node(game.snapshot(), null, 0, heuristic);
        int limit, cheapestPath, MAX_MOVEMENTS = boxes* boardDimensions.getX()*boardDimensions.getY();
        path.push(startNode);
        limit = startNode.evaluate();

        while(true) {
            cheapestPath = search(0, limit);
            if( endNode != null ) {
                return endNode;
            }
            if( cheapestPath == Integer.MAX_VALUE ) {
                return null;
            }
            limit = cheapestPath;
        }
    }

    private static int search( int currentCost, int limit ) {
        Node childNode, startNode = path.peek();
        int min, cheapestPath, f;
        if( startNode.evaluate() == Integer.MAX_VALUE ) {
            f = startNode.evaluate();
        } else {
            f = startNode.evaluate() + currentCost;
        }
        if( f > limit ) {
            return f;
        }
        Sokoban game = new Sokoban(startNode.getSnapshot());
        if( game.isOver() ) {
            endNode = startNode;
            return f;
        }
        min = Integer.MAX_VALUE;
        List<Snapshot> moves = game.getPossibleMoves();
        for (Snapshot move : moves) {
            childNode = new Node(move, startNode, startNode.getDepth()+1, startNode.getHeuristic());
            path.push(childNode);
            cheapestPath = search(currentCost + 1, limit );
            if( endNode != null ) {
                return cheapestPath;
            }
            if( cheapestPath < min ) {
                min = cheapestPath;
            }
            path.pop();
        }
        return min;
    }

    private static boolean contains( Iterable<Node> nodeList, Node node ) {
        for (Node nodeFromList : nodeList) {
            if( new Sokoban(nodeFromList.getSnapshot()).equals(new Sokoban(node.getSnapshot())) ) {
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
