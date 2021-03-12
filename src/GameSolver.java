import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;

public class GameSolver {
    public static void main(String[] args) {
        final Sokoban game = new Sokoban(
                Arrays.asList(new Pair(0, 6),new Pair(0, 7),new Pair(0, 8),new Pair(1, 6),new Pair(1,8), new Pair(2, 2),new Pair(2, 3),
                new Pair(2, 4),new Pair(2, 5),new Pair(2, 6),new Pair(2, 8), new Pair(2, 9), new Pair(2, 10), new Pair(2, 11),
                new Pair(2, 12),new Pair(3, 1),new Pair(3, 2),new Pair(3, 12),new Pair(2, 13),new Pair(4, 0),new Pair(4, 1),
                new Pair(4, 4),new Pair(4, 6),new Pair(4, 8),new Pair(4, 10),new Pair(4, 13),new Pair(4, 14),new Pair(5, 0),
                new Pair(5, 3),new Pair(5, 4),new Pair(5, 10),new Pair(5, 11),new Pair(5, 14),new Pair(6, 0),new Pair(6, 2),
                new Pair(6, 3),new Pair(6, 6),new Pair(6, 8),new Pair(6, 11),new Pair(6, 12),new Pair(6, 14),new Pair(7, 0),
                new Pair(7, 14),new Pair(8, 0),new Pair(8, 1),new Pair(8, 2),new Pair(8, 3),new Pair(8, 6),new Pair(8, 7),
                new Pair(8, 8),new Pair(8, 11),new Pair(8, 12),new Pair(8, 13),new Pair(8, 14),new Pair(9, 3),new Pair(9, 4),
                new Pair(9, 5),new Pair(9, 6),new Pair(9, 8),new Pair(9, 9),new Pair(9, 10),new Pair(9, 11)),
                Arrays.asList(new Pair(7, 6),new Pair(7, 8)),
                Arrays.asList(new Pair(1, 7), new Pair(2, 7)), new Pair(7, 7), 10, 15);
        Node start = new Node(game.snapshot(), null, 0);
        start.setMovements(new LinkedList<>());
        Node solution;
        Instant starts = Instant.now();


       solution = DFS.solve(start, new LinkedList<>());
//        solution = BFS.solve(game);
//        solution = IDDFS.solve(game);
//        solution = DFS2.solve(game);
        Instant ends = Instant.now();
        System.out.println(Duration.between(starts, ends));
        if( solution != null ) {
            new Sokoban(solution.getSnapshot()).show();
            System.out.println(solution.getMovements());
            System.out.println(solution.getMovements().size());
        }
    }
}
