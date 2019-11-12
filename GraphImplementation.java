import java.util.*;

// UDG = Unweighted, directed graph
public class GraphImplementation implements Graph {
    private int size;
    private int[][] matrix;

    public GraphImplementation(int input) {
        size = input;
        matrix = new int[size][size];
    }

    // Makes an edge from src to tar (not other way around)
    public void addEdge(int src, int tar) throws Exception {
        if (src < 0 || src >= size || tar < 0 || tar >= size)
            throw new Exception("src or tar out of bounds!");
        matrix[src][tar] = 1;
    }

    // returns list of neighbors of vertex
    // If matrix[vertex][y] == 1, y is a neighbor of vertex
     public List<Integer> neighbors(int vertex) throws Exception {
        if (vertex < 0 || vertex >= size)
            throw new Exception("vertex out of bounds!");

        List<Integer> neighbors = new LinkedList<>();

        for (int i = 0; i < size; i++)
            if (matrix[vertex][i] == 1)
                neighbors.add(i);

        return neighbors;
    }


    // Sorts vertices such that whichever vertices have all...
    // ...dependencies already in sorted list are first
    public List<Integer> topologicalSort() {
        List<Integer> sorted = new LinkedList<>();
        int[] sums = getSums();

        for (int i = 0; i < size; i++) {
            int nextZero = getNextZero(sums);

            // Case: Entire list not sorted, but cannot sort further
            if (nextZero == -1) {
                System.out.println("Cannot sort any further: " +
                        "here is the list as far as we can go: ");
                return sorted;
            }
            sorted.add(nextZero);
            sums[nextZero] = -1;

            for (int index = 0; index < size; index++)
                sums[index] -= matrix[nextZero][index];
        }

        System.out.println("Entire list sorted. This is the list: ");
        return sorted;

    }


    // Creates an arr of sums such that sums[i] is total of...
    // ...values of column i
    private int[] getSums() {

        int[] sums = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                sums[j] += matrix[i][j];
        }

        return sums;



    }

    // Returns 1st index in sums that sums[index] = 0
    // Returns -1 if all indices != 0
    private int getNextZero(int[] arr) {
        for (int i = 0; i < size; i++)
            if (arr[i] == 0)
                return i;

        return -1;


    }

    // Getters and setters
    public int getSize() {return size;}
    public int[][] getMatrix() {return matrix;}

    public void setMatrix(int newSize) {
        size = newSize;
        matrix = new int[size][size];
    }

}
