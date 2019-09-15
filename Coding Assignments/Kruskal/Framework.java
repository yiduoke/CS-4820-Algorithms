import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{
    /**
        sorts 2D int array [arr] by column [col]
     */
    public static void sortbyColumn(int arr[][], int col){ 
            // Using built-in sort function Arrays.sort 
        Arrays.sort(arr, new Comparator<int[]>() { 
                
            @Override              
                // Compare values according to columns 
            public int compare(int[] arr1, int[] arr2) { 
                return arr1[col] - arr2[col];
            }  // End of function call sort(). 
        });
    }

    public static void main(String[] args){

        // initializing the data structures
        int num_nodes = 0;
        int num_edges = 0;

        int[] components; // components[i] is the component that node [i] belongs to
        int[] component_sizes; // component_sizes[i] is the size of the [i]th component
        int[][] edges = new int[1][1];

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            String str = inputStream.readLine();
            num_nodes = Integer.parseInt(str);

            String str2 = inputStream.readLine();
            num_edges = Integer.parseInt(str2);

            components = new int[num_nodes];
            component_sizes = new int[num_nodes];
            edges = new int[num_edges][3];

            for (int i = 0; i < num_edges; i++){
                String line = inputStream.readLine();
                String[] current_edge = line.split(" ");

                for (int j=0; j < 3; j++){
                    edges[i][j] = Integer.parseInt(current_edge[j]);
                }
            }
            inputStream.close();
        }

        catch(java.io.IOException ex){
            System.out.println("bad file");
        }

        System.out.println(num_nodes + " nodes");
        System.out.println(num_edges + " edges");
        System.out.println("edges:");

        for (int i = 0; i < num_edges; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(edges[i][j] + ", ");
            }
            System.out.println();
        }

        long startTime = System.nanoTime();
        /////////////////////////////////the actual algorithm now//////////////////////////////////

    }
}