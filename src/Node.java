import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Node {
    private Snapshot snapshot;
    private Node parentNode;
    private int depth;
    private List<Directions> movements;
    private List<Pair> positions;
    private Heuristic heuristic;

    Node(Snapshot snapshot, Node parentNode, int depth, List<Directions> movements, Heuristic heuristic) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
        this.movements = movements;
        this.heuristic = heuristic;
    }

    Node(Snapshot snapshot, Node parentNode, int depth, List<Pair> positions, List<Directions> movements) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
        this.positions = positions;
        this.movements = movements;
    }

    Node( Node node ) {
        this.snapshot = node.snapshot;
        this.parentNode = node.parentNode;
        this.positions = node.positions;
        this.movements = node.movements;
        this.depth = node.depth;
    }

    Node(Snapshot snapshot, Node parentNode, int depth) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
    }

    public List<Directions> getMovements() {
        return movements;
    }

    public void setMovements(List<Directions> movements) {
        this.movements = movements;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
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

    public int evaluate() {
        return heuristic.evaluate(snapshot);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;

        return snapshot.equals(node.snapshot);
    }

    @Override
    public int hashCode() {
        return snapshot.hashCode();
    }
}