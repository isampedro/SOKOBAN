package Sokoban;
import Heuristics.*;

public class Node {
    private Snapshot snapshot;
    private Node parentNode;
    private int depth;
    private Heuristic heuristic;

    public Node(Snapshot snapshot, Node parentNode, int depth, Heuristic heuristic) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
        this.heuristic = heuristic;
    }

    public Node( Node node ) {
        this.snapshot = node.snapshot;
        this.parentNode = node.parentNode;
        this.depth = node.depth;
    }

    public Node(Snapshot snapshot, Node parentNode, int depth) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
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

    public Heuristic getHeuristic() {
        return heuristic;
    }
}