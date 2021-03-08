import java.util.LinkedList;
import java.util.List;

public class Node {
    private Sokoban board;
    private Node parentNode;
    private int depth;
    private List<Directions> movements;

    Node(Sokoban board, Node parentNode, int depth) {
        this.board = board;
        this.parentNode = parentNode;
        this.depth = depth;
    }

    public List<Directions> getMovements() {
        return movements;
    }

    public void setMovements(List<Directions> movements) {
        this.movements = movements;
    }

    public Sokoban getBoard() {
        return board;
    }

    public void setBoard(Sokoban board) {
        this.board = board;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}