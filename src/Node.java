import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Node {
    private Sokoban board;
    private Node parentNode;
    private int depth;
    private List<Directions> movements;
    private List<Pair> positions;

    Node(Sokoban board, Node parentNode, int depth, List<Pair> positions, List<Directions> movements) {
        this.board = board;
        this.parentNode = parentNode;
        this.depth = depth;
        this.positions = positions;
        this.movements = movements;
    }

    Node( Node node ) {
        this.board = node.board;
        this.parentNode = node.parentNode;
        this.positions = node.positions;
        this.movements = node.movements;
        this.depth = node.depth;
    }

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

    public List<Pair> getPositions() {
        return positions;
    }

    public void setPositions(List<Pair> positions) {
        this.positions = positions;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return depth == node.depth &&
                board.equals(node.board) &&
                Objects.equals(parentNode, node.parentNode) &&
                Objects.equals(movements, node.movements) &&
                Objects.equals(positions, node.positions);
    }

    public boolean equalsNotDepth( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return  board.equals(node.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, parentNode, depth, movements, positions);
    }
}