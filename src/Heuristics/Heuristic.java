package Heuristics;

import Sokoban.Snapshot;

public interface Heuristic {
    int evaluate(Snapshot gamePhoto);
}
