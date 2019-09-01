import java.util.*;
import java.io.Reader;
import java.io.Writer;

public class Framework{

    public static void main(String[] args){
        int m0_fav_woman;
        int num_pairs = 2; //will get changed later

        // will get set after algo
        int r1 = 0;
        int r2 = 0;
    

        // populating mens' preference queues
        ArrayList<Queue<Integer>> men_preferences = 
            new ArrayList<Queue<Integer>>(num_pairs);

        for (int i = 0; i < num_pairs; i++) {
            Queue<Integer> current_man_preferences = new LinkedList<Integer>();
            for (int j=0; j < num_pairs; j++){
                current_man_preferences.add(0); //change number later
            }
            men_preferences.add(new LinkedList<Integer>(current_man_preferences));
        }

        // populating womens' preference queues
        ArrayList<Queue<Integer>> women_preferences = 
            new ArrayList<Queue<Integer>>(num_pairs);

        for (int i = 0; i < num_pairs; i++) {
            Queue<Integer> current_woman_preferences = new LinkedList<Integer>();
            for (int j=0; j < num_pairs; j++){
                current_woman_preferences.add(0); //change number later
            }
            men_preferences.add(new LinkedList<Integer>(current_woman_preferences));
        }
    }

}