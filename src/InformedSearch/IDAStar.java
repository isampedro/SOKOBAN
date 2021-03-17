package InformedSearch;
import Heuristics.Heuristic;
import Sokoban.*;
import java.util.*;

public class IDAStar {
    static Integer frontierNodes = 0;
    static int expandedNodes = 0;


    public static Node solve(Sokoban game, Heuristic heuristic, Pair boardDimensions, int boxes) {
        Node startNode = new Node(game.snapshot(), null, 0, heuristic);
        Queue<Node> currentNodes = new LinkedList<>();
        Queue<Node> nextFrontierNodes = new LinkedList<>();
        Map<Integer, Set<Snapshot>> visited = new HashMap<>();
        Node solution = null;
        Node aux = null;
        int limit = startNode.getDepth() + startNode.evaluate(), MAX_MOVEMENTS = boxes*boardDimensions.getX()*boardDimensions.getY();
        boolean max_movements = false;
        currentNodes.add(startNode);
        while (solution == null && !currentNodes.isEmpty() && !max_movements) {
            aux = currentNodes.poll();
            if( aux.getDepth() > MAX_MOVEMENTS ) {
                max_movements = true;
            }
            solution = search(aux, nextFrontierNodes, limit, heuristic, visited);
            if(currentNodes.isEmpty()) {
                // los nodos que voy a arrancar analizando son los que me quedaron pendiente del límite anterior
                currentNodes.addAll(nextFrontierNodes);
                nextFrontierNodes.clear();
                if (!currentNodes.isEmpty()) {
                    limit = Integer.MAX_VALUE;
                }
                // me quedo con el límite menor de los nodos que me quedaron pendiente de analizar
                for (Node n : currentNodes) {
                    int aux_limit = n.getDepth() + n.evaluate();
                    if (aux_limit < limit) {
                        limit = aux_limit;
                    }
                }
            }
        }
        // encontre la solución --> la frontera va a ser igual a los nodos que me quedaron pendeintes de analizar
        frontierNodes = nextFrontierNodes.size();
        return solution == null ? null:new Node(solution, expandedNodes, frontierNodes);
    }

    private static Node search(Node currentNode, Queue<Node> nextFrontierNodes, int limit, Heuristic heuristic, Map<Integer, Set<Snapshot>> visited ) {
        // analizo el currentNode --> lo saco de la frontera
        if(frontierNodes > 0 ) {
            frontierNodes--;
        }
        int f = currentNode.getDepth() + currentNode.evaluate();
        // si f es mayor que el límite --> el nodo será analizado en la próxima iteración --> lo agrego a los nodos de la próxima frontera
        if(f > limit){
            nextFrontierNodes.add(currentNode);
            return null;
        }
        if(new Sokoban(currentNode.getSnapshot()).isOver()) {
            return currentNode;
        }

        // ESTO ES CLAVE PARA OPTIMIZAR EL TIEMPO --> básicamente estoy diciendo si el mismo snapshot
        // apareció antes y tiene una f (costo + heurística) menor al actual, me quedo con el snapshot anterior
        // y no sigo expandiendo este nuevo nodo.
        for(int i = 0; i <= f; i++){
            if(visited.containsKey(i) && visited.get(i).contains(currentNode.getSnapshot())) {
                return null;
            }
        }
        // si todavía no hay snapshot con clave = al valor de f actual, creo el hashset y después lo agrego
        if(!visited.containsKey(f)){
            visited.put(f,new HashSet<>());
        }
        visited.get(f).add(currentNode.getSnapshot());

        expandedNodes++;
        // posibles movimeientos del snapshot actual
        List<Snapshot> moves = new Sokoban(currentNode.getSnapshot()).getPossibleMoves();
        // todos estos movimientos son los que voy a analizar ahora --> van a la frontera
        frontierNodes += moves.size();
        Node auxNode = null;
        for (Snapshot move : moves) {
            auxNode = new Node(move, currentNode, currentNode.getDepth() + 1, heuristic);
            auxNode = search(auxNode, nextFrontierNodes, limit, heuristic, visited);
            if( auxNode != null ) {
                return auxNode;
            }
        }
        return null;
    }
}