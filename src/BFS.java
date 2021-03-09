import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static Node solve(Sokoban game) {
        int TILES = 7 - 2;
        int BOARD = TILES*TILES;
        int BOXES = 3;
        int MAX_MOVEMENTS = BOARD*BOXES;

        List<Pair> positions;
        positions = new LinkedList<>();
        positions.add(game.getPlayer());
        List<Directions> movements;
        Queue<Node> frontier = new LinkedList<>();
        Node startNode = new Node(game, null, 0, new LinkedList<>(), new LinkedList<>());
        startNode.setPositions(positions);
        startNode.setMovements(new LinkedList<>());
        frontier.offer(startNode);
        Node aux = null;

        while( !frontier.isEmpty() ) {
            aux = frontier.poll();
            if( aux.getBoard().isOver() ) {
                return aux;
            }
            if( aux.getMovements().size() <= MAX_MOVEMENTS) {
                List<Sokoban> moves = aux.getBoard().getPossibleMoves();
                for (Sokoban move : moves) {
                    positions = new LinkedList<>(aux.getPositions());
                    movements = new LinkedList<>(aux.getMovements());
                    if( !positions.contains(move.getPlayer())) {
                        positions.add(move.getPlayer());
                        if( move.isMovingBox() ) {
                            positions = new LinkedList<>();
                        }
                        movements.add(move.getDirection());
                        startNode = new Node(move, aux, aux.getDepth() + 1, positions, movements );
                        frontier.offer(startNode);
                    }
                }
            }
        }
        return null;
    }
}
