import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{

    // a Number class because Java doesn't have pointers; using this for faster union
    class Node implements Comparable<Node> {
        int pointing_to;
        int component_size;

        public int compareTo(Node other){
            return this.component_size - other.component_size;
        }
    }

    // using pointers and path compression
    public static int ancestor(Node[] nodes, int i){
        if (nodes[i].pointing_to != i){
            nodes[i].pointing_to = ancestor(nodes, nodes[i].pointing_to);
        }
        return nodes[i].pointing_to;
    }

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

        Main o = new Main();
        
        Main.Node[] components = new Main.Node[1];

        int[][] edges = new int[1][1];

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            String str = inputStream.readLine();
            num_nodes = Integer.parseInt(str);

            String str2 = inputStream.readLine();
            num_edges = Integer.parseInt(str2);

            // components = new Integer[num_nodes];
            // components = new int[num_nodes];
            components = new Main.Node[num_nodes];

            // component_sizes = new int[num_nodes];
            for (int i = 0; i < num_nodes; i++){
                // components[i] = Integer.valueOf(i);
                // components[i] = i;
                Main.Node current_node = o.new Node();
                current_node.pointing_to = i;
                current_node.component_size = 1;
                components[i] = current_node;

                // component_sizes[i] = 1;
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
            int node1 = ancestor(components, edges[current_edge][0]);
            int node2 = ancestor(components, edges[current_edge][1]);

            if (components[node1].pointing_to==components[node2].pointing_to){
                current_edge++;
                continue;
            }
            else{
                if (components[node1].component_size >= components[node2].component_size){

                    (components[node1]).component_size += (components[node2]).component_size;
                    (components[node2]).component_size = 0;
                    components[node2].pointing_to = node1;

                    
                }
                else{
                    (components[node2]).component_size += components[node1].component_size;
                    (components[node1]).component_size = 0;

                    components[node1].pointing_to = node2;
                }
                current_edge++;
                num_connected_edges++;
            }
        }

        Arrays.sort(components);
        System.out.println((components[num_nodes-3]).component_size);
        System.out.println((components[num_nodes-2]).component_size);
        System.out.println((components[num_nodes-1]).component_size);
    }
}