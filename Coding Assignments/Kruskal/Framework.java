import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{
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

        long startStartTime = System.nanoTime();
        Queue<Integer> all_numbers = new LinkedList<Integer>();
        int num_pairs = 0;
        int m0_fav_woman = -1;

        // initializing the data structures
        int[][] women_preferences = new int[1][1];
        int[][] men_preferences = new int[1][1];
        int[][] men_preferences_clone = new int[1][1];
        int[] current_women = new int[1];

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            String str = inputStream.readLine();
            num_pairs = Integer.parseInt(str);
            int current_number; // for parsing later

            // populating mens' preference queues
            men_preferences = new int[num_pairs][num_pairs];
            men_preferences_clone = new int[num_pairs][num_pairs];
            current_women = new int[num_pairs];

            for (int i = 0; i < num_pairs; i++){
                String line = inputStream.readLine();
                String[] preferences = line.split(" ");
                for (int j=0; j < num_pairs; j++){
                    current_number = Integer.parseInt(preferences[j]);
                    men_preferences[i][j] = current_number;
                    men_preferences_clone[i][j] = current_number;
                }
            }

            m0_fav_woman = men_preferences[0][0]; //to be used for r2


            // populating womens' preference queues
            women_preferences = new int[num_pairs][num_pairs]; //women_preferences[woman's number][man's number] = mans' ranking in her heart
            for (int i = 0; i < num_pairs; i++) {
                String line = inputStream.readLine();
                String[] preferences = line.split(" ");
                for (int j=0; j < num_pairs; j++){
                    current_number = Integer.parseInt(preferences[j]);
                    women_preferences[i][current_number] = j;
                }
            }


            inputStream.close();
        }
        catch(java.io.IOException ex){
            System.out.println("bad file");
        }


        long startTime = System.nanoTime();
        /////////////////////////////////the actual algorithm now//////////////////////////////////
        int marriage_records[][] = new int[num_pairs][3]; 
        int r1;
        int r2;
        int which_part = 1;
        int old_rank = -1;
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
                if (which_part == 2){
                    int new_rank = marriage_records[m0_fav_woman][1];
                    if (new_rank <= old_rank){
                        r2 = 3;
                    }
                    else{
                        r2 = 2;
                    }
                    
                    System.out.println(r2);

                    long endTime = System.nanoTime();
                    long entire_duration = (endTime - startStartTime) / 1000000;
                    long duration = (endTime - startTime) / 1000000;

                    System.exit(0); //we're done
                }

                else{
                    r1 = marriage_records[0][2];
                    System.out.println(r1); //TODO: change this later to print to the other file
                    which_part = 2; // move on to part 2 of the hw
                    old_rank = marriage_records[m0_fav_woman][1];
                    for (int i = 0; i < num_pairs; i++){
                        marriage_records[i][0] = -1;
                        marriage_records[i][1] = -1;
                        marriage_records[i][2] = -1;
                    }
                    men_preferences = men_preferences_clone;
                    current_women = new int[num_pairs];
                }
            }
            else{//proceed
                for (int i = first_single_man; i < num_pairs; i++){ // starting with the first single man by index
                    if (marriage_records[i][0] != -1){
                        continue;
                    }
                    else{
                        // if (which_part == 2 && men_preferences.get(i).size() == 0) {
                        if (which_part == 2 && current_women[i] == num_pairs) { //man's out of options
                                r2 = 1;
                                System.out.println(r2); 
                                
                                long endTime = System.nanoTime();
                                long entire_duration = (endTime - startStartTime) / 1000000;
                                long duration = (endTime - startTime) / 1000000;

                                System.exit(0); //we're done
                        }
                        // int propose_woman = men_preferences.get(i).remove();
                        int propose_woman = men_preferences[i][current_women[i]];
                        int woman_status = marriage_records[propose_woman][1];
                        int man_ranking_by_woman = women_preferences[propose_woman][i];
                        if (which_part == 2 && i == 0 && propose_woman == m0_fav_woman) { // man 0 gets rejected by his favorite woman
                            current_women[i]++;
                            continue;
                        }
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
                        current_women[i]++;
                    }
                }
            }
        }
    }
}