import java.util.*;

public class IDDFS {
    private static boolean DLS( int limit ) {


        return DLS(limit);
    }


    public static Node solve(Sokoban game) {
        int TILES_X = 10, TILES_Y = 15;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;
        int LIMIT = 80;
        int limit;

        List<Directions> movements;
        Node startNode = new Node(game.snapshot(), null, 0, null, new LinkedList<>());
        Node nodeAux;
        List<Snapshot> moves;
        Set<Node> visited = new HashSet<>();
        Stack<Node> frontier = new Stack<>();


        for( limit = 0; limit <= MAX_MOVEMENTS; limit += LIMIT) {
            frontier.push(startNode);

            while(!frontier.isEmpty()) {
                startNode = frontier.pop();
                visited.add(startNode);
                if( new Sokoban(startNode.getSnapshot()).isOver() ) {
                    return startNode;
                }
                if( startNode.getDepth() <= limit ) {
                    moves = new Sokoban(startNode.getSnapshot()).getPossibleMoves();
                    for( Snapshot move: moves ){
                        movements = new LinkedList<>(startNode.getMovements());
                        movements.add(move.getDirection());
                        nodeAux = new Node(move, startNode, startNode.getDepth() + 1, null, movements);
                        if( !contains(nodeAux, visited) && !contains(nodeAux, frontier)) {
                            frontier.push(nodeAux);
                        }
                    }
                }
            }

            visited.clear();
        }
        return null;
    }

    private static boolean contains(Node node, Iterable<Node> nodeIterable) {
        for (Node nodeFromList : nodeIterable) {
            if( nodeFromList.equals( node ) ) {
                return true;
            }
        }
        return false;
    }
}
