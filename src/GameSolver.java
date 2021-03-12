import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;

public class GameSolver {
    public static void main(String[] args) {
        final Sokoban game = new Sokoban(new Character[][]{
                {'#','#','#','#','#'},
                {'#','.',null,'.','#'},
                {'#',null,null,null,'#'},
                {'#','$',null,'$','#'},
                {'#',null,'@',null,'#'},
                {'#','#','#','#','#'},});
        Node start = new Node(game.snapshot(), null, 0);
        start.setMovements(new LinkedList<>());
        Node solution;
        Instant starts = Instant.now();


//       solution = DFS.solve(start, new LinkedList<>());
//        solution = BFS.solve(game);
//        solution = IDDFS.solve(game);
        solution = DFS2.solve(game);
        Instant ends = Instant.now();
        long seconds = Duration.between(starts, ends).getSeconds()%60;
        long minutes = (Duration.between(starts, ends).getSeconds()/60)%60;
        long hours = (Duration.between(starts, ends).getSeconds()/(60*60))%24;
        System.out.println(hours + ":" + minutes + ":" + seconds + "." + (Duration.between(starts, ends).toMillis()%1000 ));
        if( solution != null && new Sokoban(solution.getSnapshot()).isOver() ) {
            new Sokoban(solution.getSnapshot()).show();
            System.out.println(solution.getMovements());
            System.out.println(solution.getMovements().size());
        }
    }
}
