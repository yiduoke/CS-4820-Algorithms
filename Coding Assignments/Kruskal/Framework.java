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
    public static void sortbyColumn(int[][] arr, int col){ 
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

        Integer[] components = new Integer[1]; // components[i] is the component that node [i] belongs to
        int[] component_sizes = new int[1]; // component_sizes[i] is the size of the [i]th component
        int[][] edges = new int[1][1];

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            String str = inputStream.readLine();
            num_nodes = Integer.parseInt(str);

            String str2 = inputStream.readLine();
            num_edges = Integer.parseInt(str2);

            components = new Integer[num_nodes];
            component_sizes = new int[num_nodes];
            for (int i = 0; i < num_nodes; i++){
                components[i] = Integer.valueOf(i);
                component_sizes[i] = 1;
            }

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

        long startTime = System.nanoTime();


        /////////////////////////////////the actual algorithm now//////////////////////////////////

        sortbyColumn(edges, 2); //sort edges by increasing value

        int current_edge = 0;
        int num_connected_edges = 0;
        while (num_connected_edges < num_nodes - 3){
            int node1 = edges[current_edge][0];
            int node2 = edges[current_edge][1];

            if (components[node1] == components[node2]){
                current_edge++;
                continue;
            }
            else{
                if (component_sizes[components[node1]] <= component_sizes[components[node2]]){
                    component_sizes[components[node2]] += component_sizes[components[node1]];
                    component_sizes[components[node1]] = 0;

                    components[node1] = components[node2];
                }
                else{
                    component_sizes[components[node1]] += component_sizes[components[node2]];
                    component_sizes[components[node2]] = 0;

                    components[node2] = components[node1];
                }
                current_edge++;
                num_connected_edges++;
            }
        }

        Arrays.sort(component_sizes);
        System.out.println(component_sizes[num_nodes-3]);
        System.out.println(component_sizes[num_nodes-2]);
        System.out.println(component_sizes[num_nodes-1]);
    }
}