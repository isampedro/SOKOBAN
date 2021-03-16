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
    }

    private static void runAlgorithmsOnBoard( Sokoban game, Pair boardDimensions, int boxes ) {
        long hours, minutes, seconds;
        Instant starts, ends;
        Node solution;
        Heuristic manhattanDistance = new ManhattanDistance(), manhattanDistancePBO = new ManhattanDistancePBO(),
                corneredBox = new CorneredBoxDeadlock(), trivialHeuristic = new TrivialHeuristic(), tunnelDirection = new TunnelDirection();
        starts = Instant.now();
        System.out.println("Searching with BFS:");
        solution = BFS.solve(game, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with IDDFS:");
//        solution = IDDFS.solve(game, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
        System.out.println("Searching with DFS:");
        solution = DFS.solve(game, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with A* and Manhattan Distance Box-Objective:");
//        solution = AStar.solve(game, boardDimensions, boxes, manhattanDistance);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with A* and Manhattan Distance Player-Box-Objective:");
//        solution = AStar.solve(game, boardDimensions, boxes, manhattanDistancePBO);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with A* and Cornered Box Deadlock:");
//        solution = AStar.solve(game, boardDimensions, boxes, corneredBox);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with A* and Tunnel Direction Heuristic:");
//        solution = AStar.solve(game, boardDimensions, boxes, tunnelDirection);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with A* and Trivial heuristic:");
//        solution = AStar.solve(game, boardDimensions, boxes, trivialHeuristic);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with GGS and Manhattan Distance Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, manhattanDistance);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with GGS and Manhattan Distance Player-Box-Objective:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, manhattanDistancePBO);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with GGS and Cornered Box Deadlock:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, corneredBox);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with GGS and Tunnel Direction Heuristic:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, tunnelDirection);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with GGS and Trivial heuristic:");
//        solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, trivialHeuristic);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with IDA* and Manhattan Distance Box-Objective:");
//        solution = IDAStar.solve(game, manhattanDistance, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with IDA* and Manhattan Distance Player-Box-Objective:");
//        solution = IDAStar.solve(game, manhattanDistancePBO, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with IDA* and Cornered Box Deadlock:");
//        solution = IDAStar.solve(game, corneredBox, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with IDA* and Tunnel Direction Heuristic:");
//        solution = IDAStar.solve(game, tunnelDirection, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");

        solution = null;
        starts = Instant.now();
//        System.out.println("Searching with IDA* and Trivial heuristic:");
//        solution = IDAStar.solve(game, trivialHeuristic, boardDimensions, boxes);
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;
        minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("Finished Board:");
            new Sokoban(solution.getSnapshot()).show();
            System.out.println("Solution cost: " + solution.getDepth());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + (Duration.between(starts, ends).toMillis() % 1000) + "ms");
    }
}
