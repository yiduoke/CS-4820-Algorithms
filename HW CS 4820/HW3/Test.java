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
        

        Test o = new Test();
        
        Test.Station meow = o.new Station();
        meow.location = 1;
        meow.cost = 2;

        Test.Station meow1 = o.new Station();
        meow1.location = 3;
        meow1.cost = 1;

        Test.Station meow2 = o.new Station();
        meow2.location = 5;
        meow2.cost = 6;

        PriorityQueue<Station> pQueue = new PriorityQueue<Station>(2); 
        pQueue.add(meow);
        pQueue.add(meow1);
        pQueue.add(meow2);

        int size = pQueue.size();

        for (int i = 0; i < size; i++){
            Test.Station station = pQueue.poll();
            System.out.println("location: " + station.location + " cost: " + station.cost);
        }
    }
}