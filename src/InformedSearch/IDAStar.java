package InformedSearch;
import Heuristics.Heuristic;
import Sokoban.*;
import java.util.*;

public class IDAStar {
    public static Node solve(Sokoban game, Heuristic heuristic, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boardDimensions.getX()*boardDimensions.getY()*boxes;
        Node childNode, rootNode, startNode = new Node(game.snapshot(), null, 0, heuristic);
        Stack<Node> frontier = new Stack<>(), nextFrontier;
        Map<Snapshot, Integer> visited = new HashMap<>();
        rootNode = startNode;
        int f, nextLimit, limit = startNode.getDepth() + startNode.evaluate();
        frontier.push(startNode);
        do {
            nextLimit = Integer.MAX_VALUE;
            nextFrontier = new Stack<>();
            while( !frontier.isEmpty() ) {
                startNode = frontier.pop();
                visited.put(startNode.getSnapshot(), startNode.getDepth());
                f = startNode.getDepth() + startNode.evaluate();
                if( new Sokoban(startNode.getSnapshot()).isOver() ) {
                    return startNode;
                } else if( f <= limit && startNode.getDepth() <= MAX_MOVEMENTS) {
                    List<Snapshot> moves = new Sokoban(startNode.getSnapshot()).getPossibleMoves();
                    for (Snapshot move : moves) {
                        childNode = new Node(move, startNode, startNode.getDepth() + 1, heuristic);
                        if( !contains(frontier, childNode) && !contains(childNode, visited) ) {
                            frontier.push(childNode);
                        }
                    }
                } else if( startNode.getDepth() <= MAX_MOVEMENTS && f > limit) {
                    if( f < nextLimit ) {
                        nextLimit = f;
                    }
                    nextFrontier.push(startNode);
                }
            }
            limit = nextLimit;
            frontier = nextFrontier;
        } while (!nextFrontier.isEmpty());
        return null;
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