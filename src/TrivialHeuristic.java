public class TrivialHeuristic implements Heuristic{
    @Override
    public int evaluate(Snapshot gamePhoto) {
        return new Sokoban(gamePhoto).isOver() ? 1:0;
    }
}
