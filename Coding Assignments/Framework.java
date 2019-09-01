import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;


public class Framework{

    public static void main(String[] args){
      
        Queue<Integer> all_numbers = new LinkedList<Integer>();
        int num_pairs = 0;

        // change this later for reading both INPUT and OUTPUT file names
        BufferedReader input_buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            String testFileName = input_buffer.readLine();
            input_buffer.close();

            try{
                BufferedReader inputStream = new BufferedReader(new FileReader(testFileName));
                String str = inputStream.readLine();
                num_pairs = Integer.parseInt(str);
                System.out.println(num_pairs + " pairs");

                for (int i = 0; i < 2 * num_pairs; i++){
                    String line = inputStream.readLine();
                    String[] preferences = line.split(" ");
                    for (int c = 0; c < num_pairs; c++){
                        all_numbers.add(Integer.parseInt(preferences[c]));
                    }
                }
                inputStream.close();
            }
            catch(java.io.FileNotFoundException ex){
                System.out.println(testFileName + " doesn't exist");
            }
        }
        catch (java.io.IOException ex){
            System.out.println("your file is bad");
        }


        int m0_fav_woman;

        // will get set after algo
        int r1 = 0;
        int r2 = 0;
    
        // populating mens' preference queues
        ArrayList<Queue<Integer>> men_preferences = new ArrayList<Queue<Integer>>(num_pairs);

        for (int i = 0; i < num_pairs; i++) {
            Queue<Integer> current_man_preferences = new LinkedList<Integer>();
            for (int j=0; j < num_pairs; j++){
                current_man_preferences.add(all_numbers.remove()); //change number later
            }
            men_preferences.add(new LinkedList<Integer>(current_man_preferences));
        }

        // populating womens' preference queues
        ArrayList<Queue<Integer>> women_preferences = new ArrayList<Queue<Integer>>(num_pairs);

        for (int i = 0; i < num_pairs; i++) {
            Queue<Integer> current_woman_preferences = new LinkedList<Integer>();
            for (int j=0; j < num_pairs; j++){
                current_woman_preferences.add(all_numbers.remove()); //change number later
            }
            women_preferences.add(new LinkedList<Integer>(current_woman_preferences));
        }
    }

}