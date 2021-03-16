import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;

public class GameSolver {
    public static void main(String[] args) {
        Boards gameBoard = new Boards();
        final Sokoban game = new Sokoban(gameBoard.getBoard3());
        System.out.println("Board:");
        game.show();
        Node solution;
        Instant starts = Instant.now();
        Heuristic manhattanDistance = new ManhattanDistance(), manhattanDistancePBO = new ManhattanDistancePBO(), corneredBox = new CorneredBox(), trivialHeuristic = new TrivialHeuristic();
//        System.out.println("Searching with BFS:");
//        solution = BFS.solve(game, new Pair(10, 15), 2);
//        System.out.println("Searching with IDDFS:");
//        solution = IDDFS.solve(game, new Pair( 10, 15), 2);
//        System.out.println("Searching with DFS:");
//        solution = DFS.solve(game);
//        System.out.println("Searching with A* and Manhattan Distance Box-Objective:");
//        solution = AStar.solve(game, new ManhattanDistance());
//        System.out.println("Searching with A* and Manhattan Distance Player-Box:");
//        solution = AStar.solve(game, new ManhattanDistancePBO());
//        System.out.println("Searching with A* and CorneredBox (dead end):");
//        solution = AStar.solve(game, new CorneredBox());
//        System.out.println("Searching with A* and Trivial heuristic:");
//        solution = AStar.solve(game, new TrivialHeuristic());
//        System.out.println("Searching with GGS and Manhattan Distance Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, new ManhattanDistance());
//        System.out.println("Searching with GGS and Manhattan Distance Player-Box:");
//        solution = GlobalGreedySearch.solve(game, new ManhattanDistancePBO());
//        System.out.println("Searching with GGS and CorneredBox (dead end):");
//        solution = GlobalGreedySearch.solve(game, new CorneredBox());
//        System.out.println("Searching with GGS and Trivial heuristic:");
//        solution = GlobalGreedySearch.solve(game, new TrivialHeuristic());
        System.out.println("Searching with IDAStar and Manhattan Distance Box-Objective:");
        solution = IDAStar.solve(game, manhattanDistance, new Pair(10, 15), 2);


        Instant ends = Instant.now();
        long seconds = Duration.between(starts, ends).getSeconds()%60;
        long minutes = (Duration.between(starts, ends).getSeconds()/60)%60;
        long hours = (Duration.between(starts, ends).getSeconds()/(60*60))%24;
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis()%1000) + "ms");
        if( solution != null && new Sokoban(solution.getSnapshot()).isOver() ) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Steps count: " + solution.getDepth());

        }
    }
}
