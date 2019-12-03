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
            if (entry1[col] > entry2[col]) 
                return 1;
            else
                return -1; 
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
            else{
                return -1; 
            }
          } 
        });  // End of function call sort(). 
    }
    
    public static void main(String[] args){
        
        int n = 0; //number of things
        int W = 0; //maximum weight allowed
        // int[] vs = new int[1]; //the n values
        // int[] ws = new int[1]; //the n weights
        int[][] vs_and_ws = new int[1][3];
        // double[] densities = new double[1]; //densities vi/wi
        
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            String line = inputStream.readLine();
            String[] n_and_W = line.split(" ");

            n = Integer.parseInt(n_and_W[0]);
            W = Integer.parseInt(n_and_W[1]);
            // vs = new int[n];
            // ws = new int[n];
            // densities = new double[n];
            vs_and_ws = new int[n][3];

            String[] v_and_w; 
            for (int i = 0; i < n; i++){
                line = inputStream.readLine();
                v_and_w = line.split(" ");
                // vs[i] = Integer.parseInt(v_and_w[0]);
                // ws[i] = Integer.parseInt(v_and_w[1]);
                // densities[i] = vs[i] * 1.0 / ws[i];
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
        sortbyValue(vs_and_ws);
        // testing input reading; passed, all good
        System.out.println("n: " + n);
        System.out.println("W: " + W);
        for (int i = 0; i < n; i++){
            System.out.print("i: " + i + " ");
            System.out.print("vs[" + i + "]: " + vs_and_ws[i][1] + " ");
            System.out.println("ws[" + i + "]: " + vs_and_ws[i][2]);
            // System.out.println("densities[" + i + "]: " + densities[i]);
        }

        sortbyDensity(vs_and_ws);
        // testing input reading; passed, all good
        System.out.println("\n\n\n\nn: " + n);
        System.out.println("W: " + W);
        for (int i = 0; i < n; i++){
            System.out.print("i: " + i + " ");
            System.out.print("vs[" + i + "]: " + vs_and_ws[i][1] + " ");
            System.out.println("ws[" + i + "]: " + vs_and_ws[i][2]);
            System.out.println("densities[" + i + "]: " + vs_and_ws[i][1] * 1.0 / vs_and_ws[i][2]);
        }
        long startTime = System.nanoTime();
    }
}
