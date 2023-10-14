import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Finder {
    
    static int pathFinder(String maze) {
        return dijkstrasAlgo(maze);
    }
    
    public static int dijkstrasAlgo(String graph) {
       
    	int graphLength = graph.length();
        int graphWidth = graph.indexOf('\n');
        // initialize unvisited and visited elements
        List<Integer> unvisitedNodes = new LinkedList<>();
        boolean[] visited = new boolean[graphLength];

        // initialize distances with max value
        int[] distances = new int[graphLength];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // initialize start node-
        int start = 0;
        distances[start] = 0;
        unvisitedNodes.add(start);

        while(!unvisitedNodes.isEmpty()) {
            // get unvisited node with the smallest known distance from root (start) node 
        	int currentNode = getMinDistanceNode(distances, unvisitedNodes);
            unvisitedNodes.remove(Integer.valueOf(currentNode));
            visited[currentNode] = true;
            // early exit case
            if (currentNode == graphLength - 1) {
                return distances[currentNode];
            }

            // child nodes
            int[] moves = {
                currentNode - graphWidth - 1,
                currentNode + graphWidth + 1,
                currentNode - 1,
                currentNode + 1
            };
            // process the child nodes
            for (var move : moves) {
                if (move >= 0 && move < graphLength && graph.charAt(move) != '\n' && !visited[move]) {
                    int currentNodeHeightValue = Character.getNumericValue(graph.charAt(currentNode));
                    int childNodeHeightValue = Character.getNumericValue(graph.charAt(move));
                    int compare = Integer.compare(currentNodeHeightValue, childNodeHeightValue);
                    int differences = (compare > 0) ? currentNodeHeightValue - childNodeHeightValue : (compare < 0) ? childNodeHeightValue - currentNodeHeightValue : compare;
                    int newDistance = distances[currentNode] + differences;
                    if (newDistance < distances[move]) {
                        distances[move] = newDistance;
                        unvisitedNodes.add(move);
                    }
                }
            }
        }

        return -1; // no path found
    }

    private static int getMinDistanceNode(int[] distances, List<Integer> unvisitedNodes) {
    	
    	int minDistance = Integer.MAX_VALUE;
        int minNode = -1;
        for (var node : unvisitedNodes) {
            if (distances[node] < minDistance) {
                minDistance = distances[node];
                minNode = node;
            }
        }
        return minNode;
    }
}