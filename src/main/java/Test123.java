

import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'distinctOrder' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts UNWEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i].
     *
     */

    public static List<Integer> distinctOrder(int gNodes, List<Integer> gFrom, List<Integer> gTo) {

        List<Integer> result = new ArrayList<>();

            /*
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Integer i = (Integer) o1;
                Integer j = (Integer) o2;
                return j - i;
            }

        });
        */

        int[][] adjMatrix = getAdjMatrix(gNodes, gFrom, gTo);

        boolean [] visited = new boolean[gNodes];

        Stack<Integer> stack = new Stack<>();
        stack.push(gNodes-1);
        visited[gNodes - 1] = true;
        result.add(gNodes);

        while(!stack.isEmpty()) {

            Integer peek = stack.peek();
            Integer nextNode = getAdjUnvisitedVertex(peek, visited, adjMatrix);
            if (nextNode == -1) {
                stack.pop();
            } else {
                visited[nextNode] = true;
                stack.push(nextNode);
                result.add(nextNode + 1);
            }


        }


        /*Deque<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        visited[0] = true;
        //pq.add(0+1);
        // add to PQ
        result.add(0+1);
*/
        /*while (!queue.isEmpty()) {

            int  i = queue.removeFirst();

            List<Integer> adjVertices = getAdjUnvisited(i, visited, adjMatrix);

            for (int adjVertex : adjVertices) {
                visited[adjVertex] = true;
                queue.addLast(adjVertex);
                // add to PQ
                //pq.add(adjVertex + 1);
                result.add(adjVertex + 1);
            }

        }

        Collections.sort(result, Collections.reverseOrder());*/

        /*
        for (int i : pq) {
            result.add(i);
        }
        */

        return result;



    }

    private static Integer getAdjUnvisitedVertex(int i, boolean[] visited, int[][] adjMatrix) {

        List<Integer> adjNodes = new ArrayList<>();
        Integer MAX_VERTEX = -1;

        for (int j = 0; j < visited.length; j++) {

            if ( i!=j && adjMatrix[i][j] == 1 && !visited[j]) {
                if (j > MAX_VERTEX) {
                    MAX_VERTEX = j;
                }
                adjNodes.add(j);
            }

        }

        return MAX_VERTEX;

    }

    private static List<Integer> getAdjUnvisited(int i, boolean[] visited, int[][] adjMatrix) {

        List<Integer> adjNodes = new ArrayList<>();

        for (int j = 0; j < visited.length; j++) {

            if ( i!=j && adjMatrix[i][j] == 1 && !visited[j]) {
                adjNodes.add(j);
            }

        }

        return adjNodes;

    }

    private static int[][] getAdjMatrix(int gNodes, List<Integer> gFrom, List<Integer> gTo) {
        int[][] adjMatrix = new int[gNodes][gNodes];

        for (int i = 0; i < gFrom.size(); i++) {
            adjMatrix[gFrom.get(i) - 1][gTo.get(i) - 1] = 1;
            adjMatrix[gTo.get(i) - 1][gFrom.get(i) - 1] = 1;
        }

        return adjMatrix;
    }

}


public class Test123 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromTo[0]));
                gTo.add(Integer.parseInt(gFromTo[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.distinctOrder(gNodes, gFrom, gTo);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}