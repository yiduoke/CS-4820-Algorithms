import java.util.*;
import java.util.Map.Entry;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{
    public static void main(String[] args){
        
        int n = 0; //length of the array
        int[] array = new int[1];
        
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            
            String line = inputStream.readLine();
            n = Integer.parseInt(line);
            line = inputStream.readLine();
            String[] array_string = line.split(" ");
            
            array = new int[n];
            
            for (int i = 0; i < n; i++){
                array[i] = Integer.parseInt(array_string[i]);
            }
            
            inputStream.close();
        }
        
        catch(java.io.IOException ex){
            System.out.println("bad file");
        }
        
        long startTime = System.nanoTime();
        
        
        /////////////////////////////////the actual algorithm now//////////////////////////////////
    
    }
}
