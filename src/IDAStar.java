import java.util.List;
import java.util.Stack;

public class IDAStar {

    // Solution taken from https://en.wikipedia.org/wiki/Iterative_deepening_A*

    public static Node solve(Sokoban game, Heuristic heuristic) {
        int TILES_X = 15,TILES_Y = 10;
        int BOARD = TILES_X*TILES_Y;
        int BOXES = 2;
        int MAX_MOVEMENTS = BOARD*BOXES;
        Node endNode = null;
        Stack<Node> path = new Stack<>();

        Node startNode = new Node(game.snapshot(), null, 0, heuristic);

        int bound = startNode.evaluate();
        path.push(startNode);
        while(true){
            int t = search(path, 0, bound, heuristic);
            if(t == -1){
                endNode = path.peek();
                return endNode;
            }
            if(t == Integer.MAX_VALUE){
                return null;
            }
            bound = t;

        }


    }

    public static int search(Stack<Node> path, int g, int bound, Heuristic heuristic){
        Node node = path.peek();
        int f = g + node.evaluate();
        if( f > bound ){
            return f;
        }
        if( new Sokoban(node.getSnapshot()).isOver() ) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        List<Snapshot> moves = new Sokoban(node.getSnapshot()).getPossibleMoves();
        for (Snapshot move : moves) {
            Node aux = new Node(move, node, node.getDepth() + 1, heuristic);
            if( !contains(path, aux) ) {
                path.push(aux);
                int t = search(path, aux.getDepth(), bound, heuristic);
                if( t == -1){
//                    endNode = aux;
                    return -1;
                }
                if( t < min){
                    min = t;
                }
                path.pop();
            }
        }
        return min;
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
