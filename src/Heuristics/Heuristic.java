package Heuristics;

import Sokoban.Snapshot;

public interface Heuristic {
    public int evaluate( Snapshot gamePhoto );
}
