import java.util.*;

public class IDDFS {
    public static Node solve(Sokoban game, Pair boardDimensions, int boxes ) {
        int MAX_MOVEMENTS = boxes*boardDimensions.getY()*boardDimensions.getX();
        int LIMIT = MAX_MOVEMENTS/10;
        int limit;

        List<Directions> movements;
        Node rootNode, startNode = new Node(game.snapshot(), null, 0, null, new LinkedList<>());
        Node childNode;
        List<Snapshot> moves;
        Map<Snapshot, Integer> visited = new HashMap<>();
        Stack<Node> frontier = new Stack<>();
        rootNode = startNode;
        Sokoban start;


        for( limit = 0; limit <= MAX_MOVEMENTS; limit += LIMIT) {
            frontier.push(rootNode);
            while(!frontier.isEmpty()) {
                startNode = frontier.pop();
                visited.put(startNode.getSnapshot(), startNode.getDepth());
                start = new Sokoban(startNode.getSnapshot());
                if( start.isOver() ) {
                    return startNode;
                }
                if( startNode.getDepth() <= limit ) {
                    moves = start.getPossibleMoves();
                    for( Snapshot move: moves ){
                        movements = new LinkedList<>(startNode.getMovements());
                        movements.add(move.getDirection());
                        childNode = new Node(move, startNode, startNode.getDepth() + 1, null, movements);
                        if( !contains(childNode, visited) && !contains(frontier, childNode)) {
                            frontier.push(childNode);
                        }
                    }
                }
            }
            visited.clear();
        }
        return null;
    }

    private static boolean contains(Node node, Map<Snapshot, Integer> nodesIterable) {
        Integer depth;
        if( nodesIterable.containsKey(node.getSnapshot())) {
            depth = nodesIterable.get(node.getSnapshot());
            return node.getDepth() > depth;
        }
        return false;
    }

    private static boolean contains( Iterable<Node> nodeList, Node node ) {
        for (Node nodeFromList : nodeList) {
            if( new Sokoban(nodeFromList.getSnapshot()).equals(new Sokoban(node.getSnapshot())) ) {
                return true;
            }
        }
        return false;
    }
}
