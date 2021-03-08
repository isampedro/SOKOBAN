import java.util.LinkedList;

public class GameSolver {
    public static void main(String[] args) {
        final Sokoban game = new Sokoban();
        DFS.solve(new Node(game, null, 0), 0, new LinkedList<>());
    }
}
