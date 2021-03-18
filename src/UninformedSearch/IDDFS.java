package UninformedSearch;
import java.util.*;
import Sokoban.*;

public class IDDFS {
    static Integer frontierNodes = 0, expandedNodes = 0;


    public static Node solve(Sokoban game, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boxes * boardDimensions.getY() * boardDimensions.getX();
        int limit = 30;

        Node startNode = new Node(game.snapshot(), null, 0);
        Node auxNode, solution = null;
        boolean max_movements = false;
        List<Snapshot> moves;
        List<Set<Snapshot>> visited = new ArrayList<>();
        Queue<Node> currentNodes = new LinkedList<>();
        Queue<Node> nextFrontierNodes = new LinkedList<>();
        //inicializo la lista de sets
        for (int i = 0; i < MAX_MOVEMENTS; i++) {
            visited.add(new HashSet<Snapshot>());
        }
        currentNodes.add(startNode);
        while (solution == null && !currentNodes.isEmpty() && !max_movements) {
            auxNode = currentNodes.poll();
            if(auxNode.getDepth() > MAX_MOVEMENTS){
                max_movements = true;
            }
            solution = search(auxNode, nextFrontierNodes, visited, limit);
            if (currentNodes.isEmpty()) {
                currentNodes.addAll(nextFrontierNodes);
                nextFrontierNodes.clear();
                limit += 10;
            }
        }
        return solution == null ? null:new Node(solution,expandedNodes, frontierNodes);
    }

    private static Node search(Node currentNode, Queue<Node> nextFrontierNodes, List<Set<Snapshot>> visited, int limit) {
        if (frontierNodes > 0) {
            frontierNodes--;
        }
        if (new Sokoban(currentNode.getSnapshot()).isOver()) {
            return currentNode;
        }
        if (currentNode.getDepth() > limit) {
            nextFrontierNodes.add(currentNode);
            return null;
        }
        // chequeo que no haya visitado el nodo aún
        for (int i = 0; i < limit; i++) {
            if (visited.get(i).contains(currentNode.getSnapshot())) {
                return null;
            }
        }
        visited.get(currentNode.getDepth()).add(currentNode.getSnapshot());
        List<Snapshot> moves = new Sokoban(currentNode.getSnapshot()).getPossibleMoves();
        expandedNodes++;
        frontierNodes += moves.size();
        for (Snapshot move : moves) {
            Node childNode = new Node(move, currentNode, currentNode.getDepth() + 1);
            childNode = search(childNode, nextFrontierNodes, visited, limit);
            if (childNode != null) {
                return childNode;
            }
        }
        return null;
    }
}