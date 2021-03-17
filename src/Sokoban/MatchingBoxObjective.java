package Sokoban;

public class MatchingBoxObjective {
    private Pair box;
    private Pair objective;
    private Integer distance;

    public MatchingBoxObjective(Pair box, Pair objective, Integer distance) {
        this.box = box;
        this.objective = objective;
        this.distance = distance;
    }

    public Pair getBox() {
        return box;
    }

    public Pair getObjective() {
        return objective;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}