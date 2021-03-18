package UninformedSearch;

import Sokoban.Node;
import Sokoban.Sokoban;

import Sokoban.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFS2 {
    public static Node solve(Sokoban game, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boxes* boardDimensions.getX()* boardDimensions.getY();

        Map<Snapshot, Integer> visitedNodes = new HashMap<>();
        Stack<Node> frontierNodes = new Stack<>();
        Node startNode = new Node(game.snapshot(), null, 0);
        frontierNodes.push(startNode);
        Node aux = null;
        int expandedNodes = 0;

        while( !frontierNodes.isEmpty() ) {
            aux = frontierNodes.pop();
            visitedNodes.put(aux.getSnapshot(), aux.getDepth());
            if( new Sokoban(aux.getSnapshot()).isOver() ) {
                return new Node(aux, expandedNodes, frontierNodes.size());
            }
            if( aux.getDepth() <= MAX_MOVEMENTS) {
                expandedNodes++;
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
