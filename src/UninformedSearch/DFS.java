package UninformedSearch;
import java.util.*;
import Sokoban.*;

public class DFS {
    static Integer frontierNodes = 0, expandedNodes = 0;


    public static Node solve(Sokoban game, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boxes * boardDimensions.getY() * boardDimensions.getX();

        Node startNode = new Node(game.snapshot(), null, 0);
        Node solution = null;
        Set<Snapshot> visited = new HashSet<>();

        solution = search(startNode, visited, MAX_MOVEMENTS);

        return solution == null ? null:new Node(solution, expandedNodes, frontierNodes);
    }

    private static Node search(Node currentNode, Set<Snapshot> visited, int limit) {
        if (frontierNodes > 0) {
            frontierNodes--;
        }
        if (new Sokoban(currentNode.getSnapshot()).isOver()) {
            return currentNode;
        }
        if (currentNode.getDepth() > limit) {
            return null;
        }
        // chequeo que no haya visitado el nodo aún
        if (visited.contains(currentNode.getSnapshot())) {
            return null;
        }
        visited.add(currentNode.getSnapshot());
        List<Snapshot> moves = new Sokoban(currentNode.getSnapshot()).getPossibleMoves();
        expandedNodes++;
        frontierNodes += moves.size();
        for (Snapshot move : moves) {
            Node childNode = new Node(move, currentNode, currentNode.getDepth() + 1);
            childNode = search(childNode, visited, limit);
            if (childNode != null) {
                return childNode;
            }
        }
        return null;
    }
}