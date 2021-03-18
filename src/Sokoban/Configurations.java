package Sokoban;

import Heuristics.Heuristic;
import Heuristics.ManhattanDistancePBO;

public class Configurations {
    private final Character[][] board;
    private final int dimX;
    private final int dimY;
    private int boxes;
    private Heuristic heuristic;
    private final String searchAlg;

    public Configurations() {
        board = new Boards().getBoard1();
        dimX = 10; dimY = 15;
        boxes = 2;
        heuristic = new ManhattanDistancePBO();
        searchAlg = "IDAStar";
    }

    public Character[][] getBoard() {
        return board;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int getBoxes() {
        return boxes;
    }

    public void setBoxes(int boxes) {
        this.boxes = boxes;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public String getSearchAlg() {
        return searchAlg;
    }
}
