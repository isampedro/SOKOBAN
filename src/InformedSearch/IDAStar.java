package InformedSearch;
import Heuristics.Heuristic;
import Sokoban.*;
import java.util.*;

public class IDAStar {
    public static Node solve(Sokoban game, Heuristic heuristic, Pair boardDimensions, int boxes) {
        int MAX_MOVEMENTS = boardDimensions.getX()*boardDimensions.getY()*boxes;
        Node childNode, startNode = new Node(game.snapshot(), null, 0, heuristic);
        Stack<Node> frontier = new Stack<>(), nextFrontier = new Stack<>();
        List<Snapshot> moves;
        int f, nextLimit, limit = startNode.getDepth() + startNode.evaluate();
        frontier.push(startNode);
        do {
            nextLimit = Integer.MAX_VALUE;
            nextFrontier.clear();
            while( !frontier.isEmpty() ) {
                startNode = frontier.pop();
                if( startNode.evaluate() == Integer.MAX_VALUE ) {
                    f = startNode.evaluate();
                } else {
                    f = startNode.evaluate() + startNode.getDepth();
                }

                if( new Sokoban(startNode.getSnapshot()).isOver() ) {
                    return startNode;
                }
                if( f <= limit && startNode.getDepth() <= MAX_MOVEMENTS) {
                    moves = new Sokoban(startNode.getSnapshot()).getPossibleMoves();
                    for (Snapshot move : moves) {
                        childNode = new Node(move, startNode, startNode.getDepth() + 1, heuristic);
                        if( !frontier.contains(childNode) ) {
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
            frontier.addAll(nextFrontier);
        } while (!nextFrontier.isEmpty());
        return null;
    }
}