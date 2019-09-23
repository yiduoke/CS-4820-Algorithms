import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Test{

    class Station implements Comparable<Station> {
            int location;
            int cost = Integer.MAX_VALUE;

            public int compareTo(Station other){
                return this.cost - other.cost;
            }
        }

    public static void main(String[] args){
        int diff = 0;
        for (int i = 5; i > 0; i--){
            for (int j = 1; j <= i; j++){
                System.out.println("[" + j + ", " + (j + diff) + "]");
            }
            diff++;
            System.out.println();
        }
    }
}
