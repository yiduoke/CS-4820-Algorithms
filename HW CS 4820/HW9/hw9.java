import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{
    
    public static void main(String[] args){
        
        int n = 0; //number of things
        int W = 0; //maximum weight allowed
        int[] vs = new int[1]; //the n values
        int[] ws = new int[1]; //the n weights
        
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            String line = inputStream.readLine();
            String[] n_and_W = line.split(" ");

            n = Integer.parseInt(n_and_W[0]);
            W = Integer.parseInt(n_and_W[1]);
            vs = new int[n];
            ws = new int[n];

            String[] v_and_w; 
            for (int i = 0; i < n; i++){
                line = inputStream.readLine();
                v_and_w = line.split(" ");
                vs[i] = Integer.parseInt(v_and_w[0]);
                ws[i] = Integer.parseInt(v_and_w[1]);
            }
            
            inputStream.close();
        }
        
        catch(java.io.IOException ex){
            System.out.println("bad file");
        }
        // finish reading the input
        long startTime = System.nanoTime();

        System.out.println("n: " + n);
        System.out.println("W: " + W);
        for (int i = 0; i < n; i++){
            System.out.print("vs[" + i + "]: " + vs[i] + " ");
            System.out.println("ws[" + i + "]: " + ws[i]);
        }
    }
}
