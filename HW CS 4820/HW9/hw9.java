import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{

    public static void sortbyValue(int arr[][]) // sort by 2nd column
    { 
        // Using built-in sort function Arrays.sort 
        Arrays.sort(arr, new Comparator<int[]>() { 
            
          @Override              
          // Compare values according to columns 
          public int compare(final int[] entry1,  
                             final int[] entry2) { 
  
            // To sort in descending order revert  
            // the '>' Operator 
            int col = 1;
            if (entry1[col] > entry2[col]){
                return 1;
            }
            else if (entry1[col] < entry2[col]){
                return -1; 
            } 
            else{
                return 0;
            }
          } 
        });  // End of function call sort(). 
    } 

    public static void sortbyDensity(int arr[][]) // sort by 2nd column divided by 3rd column
    { 
        // Using built-in sort function Arrays.sort 
        Arrays.sort(arr, new Comparator<int[]>() { 
            
          @Override              
          // Compare values according to columns 
          public int compare(final int[] entry1,  
                             final int[] entry2) { 
  
            // To sort in descending order revert  
            // the '>' Operator 
            double density1 = entry1[1] * 1.0 / entry1[2];
            double density2 = entry2[1] * 1.0 / entry2[2];
            if (density1 > density2){
                return 1;
            }  
            else if (density1 < density2){
                return -1; 
            }
            else{
                return 0;
            }
          } 
        });  // End of function call sort(). 
    }
    
    public static void main(String[] args){
        
        int n = 0; //number of things
        int W = 0; //maximum weight allowed
        int[][] vs_and_ws = new int[1][3];
        int[] value_answer = new int[1];
        int[] density_answer = new int[1];
        
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            String line = inputStream.readLine();
            String[] n_and_W = line.split(" ");

            n = Integer.parseInt(n_and_W[0]);
            W = Integer.parseInt(n_and_W[1]);
            vs_and_ws = new int[n][3];

            value_answer = new int[n];
            density_answer = new int[n];

            String[] v_and_w; 
            for (int i = 0; i < n; i++){
                line = inputStream.readLine();
                v_and_w = line.split(" ");
                vs_and_ws[i][0] = i;
                vs_and_ws[i][1] = Integer.parseInt(v_and_w[0]);
                vs_and_ws[i][2] = Integer.parseInt(v_and_w[1]);
            }
            
            inputStream.close();
        }
        
        catch(java.io.IOException ex){
            System.out.println("bad file");
        }
        // finish reading the input


        // Arrays.sort(densities);
        // Arrays.sort(vs);
        // sortbyValue(vs_and_ws);
        // // testing input reading; passed, all good
        // System.out.println("n: " + n);
        // System.out.println("W: " + W);
        // for (int i = 0; i < n; i++){
        //     System.out.print("i: " + i + " ");
        //     System.out.print("vs[" + i + "]: " + vs_and_ws[i][1] + " ");
        //     System.out.println("ws[" + i + "]: " + vs_and_ws[i][2]);
        //     // System.out.println("densities[" + i + "]: " + densities[i]);
        // }

        // sortbyDensity(vs_and_ws);
        // // testing input reading; passed, all good
        // System.out.println("\n\n\n\nn: " + n);
        // System.out.println("W: " + W);
        // for (int i = 0; i < n; i++){
        //     System.out.print("i: " + i + " ");
        //     System.out.print("vs[" + i + "]: " + vs_and_ws[i][1] + " ");
        //     System.out.println("ws[" + i + "]: " + vs_and_ws[i][2]);
        //     System.out.println("densities[" + i + "]: " + vs_and_ws[i][1] * 1.0 / vs_and_ws[i][2]);
        // }

        sortbyValue(vs_and_ws);
        int current_weight = 0;
        int current_value_value = 0;
        for (int i = 0; i < n; i++){
            int index = vs_and_ws[i][0];
            current_weight += vs_and_ws[i][2];
            if (current_weight <= W){
                value_answer[index] = 1;
                current_value_value += vs_and_ws[i][1];
            }
            else{
                break;
            }
        }


        sortbyDensity(vs_and_ws);
        current_weight = 0;
        int current_value_density = 0;
        for (int i = 0; i < n; i++){
            int index = vs_and_ws[i][0];
            current_weight += vs_and_ws[i][2];
            if (current_weight <= W){
                density_answer[index] = 1;
                current_value_density += vs_and_ws[i][1];
            }
            else{
                break;
            }
        }

        if (current_value_value >= current_value_density){
            System.out.println(current_value_value);
            for (int i = 0; i < n; i++){
                System.out.println(value_answer[i]);
            }
        }
        else{
            System.out.println(current_value_density);
            for (int i = 0; i < n; i++){
                System.out.println(density_answer[i]);
            }
        }

        long startTime = System.nanoTime();
    }
}
