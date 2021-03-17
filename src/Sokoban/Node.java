package Sokoban;
import Heuristics.*;

public class Node {
    private Snapshot snapshot;
    private Node parentNode;
    private int depth, cost, expandedNodes, frontierNodes;
    private Heuristic heuristic;

    public Node(Snapshot snapshot, Node parentNode, int depth, Heuristic heuristic) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
        this.cost = depth;
        this.heuristic = heuristic;
    }

    public Node( Node node, int expandedNodes, int frontierNodes ) {
        this.snapshot = node.snapshot;
        this.parentNode = node.parentNode;
        this.depth = node.depth;
        this.cost = depth;
        this.expandedNodes = expandedNodes;
        this.frontierNodes = frontierNodes;
    }

    public Node(Snapshot snapshot, Node parentNode, int depth) {
        this.snapshot = snapshot;
        this.parentNode = parentNode;
        this.depth = depth;
        this.cost = depth;
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

    public int getDepth() {
        return depth;
    }

    public int getCost() {
        return cost;
    }

    public int getExpandedNodes() {
        return expandedNodes;
    }

    public int getFrontierNodes() {
        return frontierNodes;
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