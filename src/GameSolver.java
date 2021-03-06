import java.util.LinkedList;

public class GameSolver {
    public static void main(String[] args) {
        final Sokoban game = new Sokoban();
        Node start = new Node(game, null, 0);
        start.setMovements(new LinkedList<>());
        //Node solution = DFS.solve(start, new LinkedList<>());
        //Node solution = BFS.solve(game);
    //    Node solution = DFS2.solve(game);
        Node solution = IDDFS.solve(game);
        if( solution != null ) {
            solution.getBoard().show();
            System.out.println(solution.getMovements());
            System.out.println(solution.getMovements().size());
        }
        return;
    }
}
