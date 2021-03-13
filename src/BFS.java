import java.util.*;

public class BFS {
    public static Node solve(Sokoban game) {
        int TILES_X = 10, TILES_Y = 15;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;

        List<Directions> movements;
        Set<Node> visited = new HashSet<>();
        Queue<Node> frontier = new LinkedList<>();
        Node startNode = new Node(game.snapshot(), null, 0, new LinkedList<>(), new LinkedList<>());
        startNode.setMovements(new LinkedList<>());
        frontier.offer(startNode);
        Node aux = null;

        while( !frontier.isEmpty() ) {
            aux = frontier.poll();
            visited.add(aux);
            if( new Sokoban(aux.getSnapshot()).isOver() ) {
                return aux;
            }
            if( aux.getMovements().size() <= MAX_MOVEMENTS) {
                List<Snapshot> moves = new Sokoban(aux.getSnapshot()).getPossibleMoves();
                for (Snapshot move : moves) {
                    movements = new LinkedList<>(aux.getMovements());
                    movements.add(move.getDirection());
                    startNode = new Node(move, aux, aux.getDepth() + 1, null, movements );
                    if( !contains(visited, startNode) && !contains(frontier, startNode) ) {
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
}