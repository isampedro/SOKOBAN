package Sokoban;

import Heuristics.*;
import InformedSearch.*;
import UninformedSearch.*;

import java.time.Duration;
import java.time.Instant;

public class GameSolver {
    public static void main(String[] args) {
        Boards gameBoard = new Boards();
        Sokoban game = new Sokoban(gameBoard.getBoard1());
        Pair boardDimensions = new Pair(10, 15);
        int boxes = 2;
        System.out.println("Board:");
        game.show();
        runAlgorithmsOnBoard(game, boardDimensions, boxes);
        game = new Sokoban(gameBoard.getBoard2());
        System.out.println("Board:");
        game.show();
        boardDimensions = new Pair(13,13);
        boxes = 2;
        runAlgorithmsOnBoard(game, boardDimensions, boxes);
        game = new Sokoban(gameBoard.getBoard3());
        System.out.println("Board:");
        game.show();
        boardDimensions = new Pair(8,9);
        boxes = 4;
        runAlgorithmsOnBoard(game, boardDimensions, boxes);
    }

    private static void runAlgorithmsOnBoard( Sokoban game, Pair boardDimensions, int boxes ) {
        long hours, minutes, seconds, millis;Instant starts, ends;Node solution = null;
        Heuristic manhattanDistance = new ManhattanDistance(), manhattanDistancePBO = new ManhattanDistancePBO(), corneredBox = new CombinedHeuristic(new CorneredBoxDeadlock(),
                new ManhattanDistance()), tunnelDirection = new CombinedHeuristic(new TunnelDirection(), new ManhattanDistance()), minMarchingLowerBound = new MinMatchingLowerBound();

        starts = Instant.now();
        System.out.println("Searching with BFS:");
//        solution = BFS.solve(game, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with IDDFS:");
//        solution = IDDFS.solve(game, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with DFS:");
//        solution = DFS.solve(game, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with A* and Manhattan Distance Box-Objective:");
//        solution = AStar.solve(game, boardDimensions, boxes, manhattanDistance);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with A* and Manhattan Distance Player-Box-Objective:");
//        solution = AStar.solve(game, boardDimensions, boxes, manhattanDistancePBO);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with A* and Cornered Box Deadlock with Manhattan Distance Box-Objective:");
//        solution = AStar.solve(game, boardDimensions, boxes, corneredBox);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with A* and Tunnel Direction with Manhattan Distance Box-Objective Heuristic:");
//        solution = AStar.solve(game, boardDimensions, boxes, tunnelDirection);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with A* and Min Matching Lower Bound:");
        solution = AStar.solve(game, boardDimensions, boxes, minMarchingLowerBound);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with GGS and Manhattan Distance Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, manhattanDistance);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with GGS and Manhattan Distance Player-Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, manhattanDistancePBO);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with GGS and Cornered Box Deadlock with Manhattan Distance Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, corneredBox);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with GGS and Tunnel Direction Heuristic with Manhattan Distance Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, tunnelDirection);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with GGS and Tunnel Direction Heuristic with Min Matching Lower Bound:");
        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, minMarchingLowerBound);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with IDA* and Manhattan Distance Box-Objective:");
//        solution = IDAStar.solve(game, manhattanDistance, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with IDA* and Manhattan Distance Player-Box-Objective:");
//        solution = IDAStar.solve(game, manhattanDistancePBO, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

//        solution = null;
//        starts = Instant.now();
//        System.out.println("Searching with IDA* and Cornered Box Deadlock with Manhattan Distance Box-Objective:");
//        solution = IDAStar.solve(game, corneredBox, boardDimensions, boxes);
//        ends = Instant.now();
//        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
//        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
//        showSolution(solution, seconds, minutes, hours, millis);

//        solution = null;
//        starts = Instant.now();
//        System.out.println("Searching with IDA* and Tunnel Direction Heuristic with Manhattan Distance Box-Objective:");
//        solution = IDAStar.solve(game, tunnelDirection, boardDimensions, boxes);
//        ends = Instant.now();
//        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
//        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
//        showSolution(solution, seconds, minutes, hours, millis);

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with IDA* and Min Marching Lower Bound:");
        solution = IDAStar.solve(game, minMarchingLowerBound, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);

    }

    private static void showSolution(Node solution, long seconds, long minutes, long hours, long millis ) {
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("The algorithm succeeded to find a solution");
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getCost());
            System.out.println("Solution depth: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + millis + "ms");
    }
}
