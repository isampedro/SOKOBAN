package Heuristics;
import Sokoban.*;

import java.util.*;

public class MinMatchingLowerBound implements Heuristic {

    public int evaluate(Snapshot gamePhoto) {
        Sokoban game = new Sokoban(gamePhoto);
        int totalDistance = 0;
        List<MatchingBoxObjective> matchingList = getMatchings(game);
        // sumo todas las distancias de la caja al objetivo
        for(MatchingBoxObjective matching : matchingList) {
            totalDistance += matching.getDistance();
        }
        return totalDistance;
    }



    private static List<MatchingBoxObjective> getMatchings (Sokoban game) {
        List<Pair> Objectives = game.getObjectivesPositions();
        List<Pair> Boxes = game.getBoxesPositions();
        Queue<MatchingBoxObjective> matchingsQueue = new PriorityQueue<>(new Comparator<MatchingBoxObjective>() {
            @Override
            public int compare(MatchingBoxObjective o1, MatchingBoxObjective o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });
        // a cada box la asocio con un objetivo
        for (Pair objective : Objectives) {
            for (Pair box : Boxes) {
                MatchingBoxObjective currentMatching = new MatchingBoxObjective(box, objective, manhattanDistance(box, objective));
                matchingsQueue.add(currentMatching);
            }
        }
        // le asigno a cada objetivo una caja --> me tiene que quedar un grafo bipartito completo
        // la asignación la hago utilizando la priority queue --> asigno los caminos con distancoias más cortas
        // siempre y cuando no se repitan objetivo o caja.
        Set<Pair> visitedBoxes = new HashSet<>();
        Set<Pair> visitedObjectives = new HashSet<>();
        List<MatchingBoxObjective> solution = new ArrayList<>();
        while (!matchingsQueue.isEmpty()) {
            MatchingBoxObjective aux = matchingsQueue.poll();
            if (!visitedBoxes.contains(aux.getBox()) && !visitedObjectives.contains(aux.getObjective())) {
                solution.add(aux);
                visitedBoxes.add(aux.getBox());
                visitedObjectives.add(aux.getObjective());
            }
        }

        return solution;
    }

    private static int manhattanDistance(Pair box, Pair objective) {
        return Math.abs(box.getX() - objective.getX()) + Math.abs(box.getY() - objective.getY());
    }




}