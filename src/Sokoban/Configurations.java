package Sokoban;

import Heuristics.*;

public class Configurations {
    private Character[][] board;
    private int dimX;
    private int dimY;
    private int boxes;
    private Heuristic heuristic;
    private final String searchAlg;

    public Configurations(String[] args) {
        switch (args[0]) {
            case "1":
                board = new Boards().getBoard1();
                dimX = 10; dimY = 15;
                boxes = 2;
                break;
            case "2":
                board = new Boards().getBoard2();
                dimX = 13; dimY = 13;
                boxes = 2;
                break;
            case "3":
                board = new Boards().getBoard3();
                dimX = 7; dimY = 8;
                boxes = 4;
                break;
            default:
                System.out.println("Invalid level");
                System.exit(1);
                break;
        }
        searchAlg = args[1];
        if(!args[1].equals("DFS") && !args[1].equals("BFS") && !args[1].equals("IDDFS") ) {
            switch (args[2]) {
                case "ManhattanBO":
                    heuristic = new ManhattanDistance();
                    break;
                case "ManhattanPBO":
                    heuristic = new ManhattanDistancePBO();
                    break;
                case "MinMatching":
                    heuristic = new MinMatchingLowerBound();
                    break;
                case "Corner":
                    heuristic = new CombinedHeuristic(new CorneredBoxDeadlock(), new ManhattanDistance());
                    break;
                default:
                    System.out.println("Invalid heuristic");
                    System.exit(1);
            }
        }
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
