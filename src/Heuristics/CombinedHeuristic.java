package Heuristics;

import Sokoban.Snapshot;

public class CombinedHeuristic implements Heuristic{
    Heuristic h1, h2;

    public CombinedHeuristic(Heuristic h1, Heuristic h2) {
        this.h1 = h1;
        this.h2 = h2;
    }

    @Override
    public int evaluate(Snapshot gamePhoto) {
        int e1, e2;
        e1 = h1.evaluate(gamePhoto);
        e2 = h2.evaluate(gamePhoto);
        if( e1 == Integer.MAX_VALUE ) {
            return e1;
        }
        if( e2 == Integer.MAX_VALUE ) {
            return e2;
        }
        return e1 + e2;
    }
}
