import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;


public class Framework{

    /**
        returns the first man by index that's single
     */
    public static int any_man_single(int[][] records){
        for (int i = 0; i < records.length; i++){
            if (records[i][0] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
      
        Queue<Integer> all_numbers = new LinkedList<Integer>();
        int num_pairs = 0;

        // TODO: change this later for reading both INPUT and OUTPUT file names
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

    
        // populating mens' preference queues
        ArrayList<Queue<Integer>> men_preferences = new ArrayList<Queue<Integer>>(num_pairs);

        for (int i = 0; i < num_pairs; i++) {
            Queue<Integer> current_man_preferences = new LinkedList<Integer>();
            for (int j=0; j < num_pairs; j++){
                current_man_preferences.add(all_numbers.remove());
            }
            men_preferences.add(current_man_preferences);
        }
        int m0_fav_woman = men_preferences.get(0).peek(); //to be used for r2
        // populating womens' preference queues
        ArrayList<HashMap<Integer, Integer>> women_preferences = new ArrayList<HashMap<Integer, Integer>>(num_pairs); //(each man's #, preference rank)

        for (int i = 0; i < num_pairs; i++) {
            HashMap<Integer, Integer> current_woman_preferences = new HashMap<Integer, Integer>();
            for (int j=0; j < num_pairs; j++){
                current_woman_preferences.put(all_numbers.remove(), j);
            }
            women_preferences.add(current_woman_preferences);
        }

        /////////////////////////////////the actual algorithm now//////////////////////////////////
        int marriage_records[][] = new int[num_pairs][3]; 
        //1st column details each man's spouse; -1 means he's single
        // 2nd column details each woman's current man's rank in her heart; -1 means she's single
        // 3rd column details each woman's current man's identity; -1 means she's single
        int respondent0_man;
        for (int i = 0; i < num_pairs; i++){
            marriage_records[i][0] = -1;
            marriage_records[i][1] = -1;
            marriage_records[i][2] = -1;
        }
        
        while (true){
            int first_single_man = any_man_single(marriage_records);
            if (first_single_man == -1){ //no more single men!! yay!!
                System.out.println("\nr0 = " + marriage_records[0][2]); //TODO: change this later to print to the other file
                break; //we're done
            }
            else{//proceed
                for (int i = first_single_man; i < num_pairs; i++){ // starting with the first single man by index
                    if (marriage_records[i][0] != -1){
                        continue;
                    }
                    else{
                        int propose_woman = men_preferences.get(i).remove();
                        int woman_status = marriage_records[propose_woman][1];
                        int man_ranking_by_woman = women_preferences.get(propose_woman).get(i);
                        if (woman_status == -1){ // if the woman in question is single, accept
                            // woman accepts
                            marriage_records[propose_woman][1] =man_ranking_by_woman;
                            marriage_records[propose_woman][2] = i;
                            //man is cuffed
                            marriage_records[i][0] = propose_woman;
                        }
                        else if (man_ranking_by_woman < woman_status) {// this is a better man
                            // woman accepts
                            int dumped_man = marriage_records[propose_woman][2];
                            marriage_records[propose_woman][1] = man_ranking_by_woman; // woman accepts
                            marriage_records[propose_woman][2] = i;

                            //her former man is dumped
                            marriage_records[dumped_man][0] = -1;
                            // this man is cuffed
                            marriage_records[i][0] = propose_woman;
                        }
                    }
                }
            }
        }
    }
}