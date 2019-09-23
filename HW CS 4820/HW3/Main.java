import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{

    class Station implements Comparable<Station> {
        int location;
        long cost = Long.MAX_VALUE;

        public int compareTo(Station other){
            return (int) (this.cost - other.cost);
        }
    }

    public static void main(String[] args){

        Main o = new Main();
        Main.Station[] stations = new Main.Station[1];

        // initializing the data structures
        long[] Opt = new long[1]; // minimum cost up until each station
        ArrayList<Integer>[] Solutions = new ArrayList[1]; //solution up until each station
        

        int M = 0; // total highway length
        int m = 0; // max distance between each station
        int n = 0; // number of stations possible

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{

            String line = inputStream.readLine();
            String[] nMm = line.split(" ");
            n = Integer.parseInt(nMm[0]);
            M = Integer.parseInt(nMm[1]);
            m = Integer.parseInt(nMm[2]);

            Opt = new long[M+1];
            Solutions = new ArrayList[M+1];
            stations = new Main.Station[M+1];

            int current_station = 0;
            for (int i = 0; i < n; i++){
                line = inputStream.readLine();
                String[] station_and_cost = line.split(" ");

                int station = Integer.parseInt(station_and_cost[0]);
                int cost = Integer.parseInt(station_and_cost[1]);
                
                Opt[station] = cost;

                Main.Station meow = o.new Station();
                meow.location = station;
                meow.cost = cost;
                stations[station] = meow;

                for (int j = current_station; j < station; j++){
                    meow = o.new Station();
                    meow.location = j;
                    meow.cost = Integer.MAX_VALUE;
                    stations[j] = meow;
                }
                current_station = station + 1;

                if (station <= m){
                    ArrayList<Integer> solution_now = new ArrayList<Integer>();
                    solution_now.add(station);
                    Solutions[station] = solution_now;
                }
            }

            for (int i = current_station; i <= M; i++){
                Main.Station meow = o.new Station();
                meow.location = i;
                meow.cost = Integer.MAX_VALUE;
                stations[i] = meow;
            }

            inputStream.close();
        }

        catch(java.io.IOException ex){
            System.out.println("bad file");
        }

        long startTime = System.nanoTime();


        /////////////////////////////////the actual algorithm now//////////////////////////////////
        TreeMap<Station,Integer> minHeap = new TreeMap<Station,Integer>(); // used to be a min heap so I didn't change the name
        // the Integer value doesn't matter; i don't know how to get them to sort by the 2nd integer

        for (int i = 1; i <= m; i++){
            minHeap.put(stations[i], 1); 
        }

        for (int i = m+1; i <= M; i++){
            Main.Station min_station = minHeap.firstKey();
            minHeap.remove(stations[i-m]);
            if (Opt[i] != 0){
                Opt[i] = Opt[i] + min_station.cost;
                stations[i].cost = Opt[i];
                ArrayList<Integer> prev_solution = (ArrayList<Integer>) (Solutions[min_station.location]).clone();
                prev_solution.add(i);
                Solutions[i] = prev_solution;
            }

            minHeap.put(stations[i], 1);
        }
        
        int min_cost_station = minHeap.firstKey().location;
        if (Opt[M-m] != 0 && Opt[M-m] < minHeap.firstKey().cost){
            min_cost_station = M-m;
        }

        System.out.println(Opt[min_cost_station]);
        ArrayList<Integer> opt_solution = Solutions[min_cost_station];
        int i;
        for (i = 0; i < opt_solution.size()-1; i++){
            System.out.print(opt_solution.get(i) + " ");
        }
        System.out.println(opt_solution.get(i));
    }
}