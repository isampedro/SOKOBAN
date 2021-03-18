package Sokoban;

import Heuristics.*;
import InformedSearch.*;
import UninformedSearch.*;

import java.time.Duration;
import java.time.Instant;

public class GameSolver {
    public static void main(String[] args) {
        Configurations config = new Configurations();
        System.out.println("Board:");
        Sokoban game = new Sokoban(config.getBoard());
        runAlgorithmOnBoard(game, new Pair(config.getDimX(), config.getDimY()), config.getBoxes(), config.getHeuristic(), config.getSearchAlg());
    }

    private static void runAlgorithmOnBoard( Sokoban game, Pair boardDimensions, int boxes, Heuristic heuristic, String searchAlg ) {
        long hours, minutes, seconds, millis;Instant starts, ends;
        Node solution = null;
        starts = Instant.now();
        switch (searchAlg) {
            case "BFS":
                solution = BFS.solve(game, boardDimensions, boxes);
                break;
            case "DFS":
                solution = DFS2.solve(game, boardDimensions, boxes);
                break;
            case "IDDFS":
                solution = IDDFS2.solve(game, boardDimensions, boxes);
                break;
            case "AStar":
                solution = AStar.solve(game, boardDimensions, boxes, heuristic);
                break;
            case "GGS":
                solution = GlobalGreedySearch.solve(game, boardDimensions, boxes, heuristic);
                break;
            case "IDAStar":
                solution = IDAStar.solve(game, heuristic, boardDimensions, boxes);
                break;
        }
        ends = Instant.now();
        seconds = Duration.between(starts, ends).getSeconds() % 60;minutes = (Duration.between(starts, ends).getSeconds() / 60) % 60;
        hours = (Duration.between(starts, ends).getSeconds() / (60 * 60)) % 24;millis = Duration.between(starts, ends).toMillis() % 1000;
        showSolution(solution, seconds, minutes, hours, millis);
    }

    private static void showSolution(Node solution, long seconds, long minutes, long hours, long millis ) {
        if (solution != null && new Sokoban(solution.getSnapshot()).isOver()) {
            System.out.println("The algorithm succeeded to find a solution");
            System.out.println("Steps from solution to start:");
            Node finish = solution;
            while( finish != null ) {
                new Sokoban(finish.getSnapshot()).show();
                System.out.println();
                finish = finish.getParentNode();
            }
            System.out.println("Solution cost: " + solution.getCost());
            System.out.println("Solution depth: " + solution.getDepth());
            System.out.println("Expanded nodes: " + solution.getExpandedNodes());
            System.out.println("Frontier nodes: " + solution.getFrontierNodes());
        } else {
            System.out.println("The algorithm wasn't able to find a solution");
        }
        System.out.println("Elapsed time: " + hours + "hs, " + minutes + "min, " + seconds + "sec, " + millis + "ms");
    }
}
