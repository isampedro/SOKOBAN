package Sokoban;

import Heuristics.*;
import UninformedSearch.*;
import InformedSearch.*;

import java.time.Duration;
import java.time.Instant;

public class GameSolver {
    public static void main(String[] args) {
        Boards gameBoard = new Boards();
        final Sokoban game = new Sokoban(gameBoard.getBoard3());
        final Pair boardDimensions = new Pair(10, 15);
        final Integer boxes = 2;
        System.out.println("Board:");
        game.show();
        Node solution;
        Instant starts = Instant.now();
        Heuristic manhattanDistance = new ManhattanDistance(), manhattanDistancePBO = new ManhattanDistancePBO(), corneredBox = new CorneredBox(), trivialHeuristic = new TrivialHeuristic();
//        System.out.println("Searching with BFS:");
//        solution = BFS.solve(game, boardDimensions, boxes);
//        System.out.println("Searching with IDDFS:");
//        solution = IDDFS.solve(game, boardDimensions, boxes);
//        System.out.println("Searching with DFS:");
//        solution = DFS.solve(game, boardDimensions, boxes);
//        System.out.println("Searching with A* and Manhattan Distance Box-Objective:");
//        solution = AStar.solve(game, boardDimensions, boxes, manhattanDistance);
        System.out.println("Searching with A* and Manhattan Distance Player-Box:");
        solution = AStar.solve(game, boardDimensions, boxes, manhattanDistancePBO);
//        System.out.println("Searching with A* and CorneredBox (dead end):");
//        solution = AStar.solve(game, boardDimensions, boxes, corneredBox);
//        System.out.println("Searching with A* and Trivial heuristic:");
//        solution = AStar.solve(game, boardDimensions, boxes, trivialHeuristic);
//        System.out.println("Searching with GGS and Manhattan Distance Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, manhattanDistance);
//        System.out.println("Searching with GGS and Manhattan Distance Player-Box:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, manhattanDistancePBO);
//        System.out.println("Searching with GGS and Cornered Box (dead end):");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, corneredBox);
//        System.out.println("Searching with GGS and Trivial heuristic:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, trivialHeuristic);
//        System.out.println("Searching with IDAStar and Manhattan Distance Box-Objective:");
//        solution = IDAStar.solve(game, manhattanDistance, boardDimensions, boxes);
//        System.out.println("Searching with IDAStar and Manhattan Distance Player-Box-Objective:");
//        solution = IDAStar.solve(game, manhattanDistancePBO, boardDimensions, boxes);
//        System.out.println("Searching with IDAStar and Cornered Box (dead end):");
//        solution = IDAStar.solve(game, corneredBox, boardDimensions, boxes);
//        System.out.println("Searching with IDAStar and Trivial heuristic:");
//        solution = IDAStar.solve(game, trivialHeuristic, boardDimensions, boxes);


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
