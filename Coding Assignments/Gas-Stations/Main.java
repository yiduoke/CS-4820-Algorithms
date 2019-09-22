import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{

    public static void main(String[] args){

        // initializing the data structures
        long[] Opt; // minimum cost up until each station
        List[] solutions; //solution up until each station
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
            solutions = new ArrayList[M+1];

            for (int i = 0; i < n; i++){
                line = inputStream.readLine();
                String[] station_and_cost = line.split(" ");

                int station = Integer.parseInt(station_and_cost[0]);
                int cost = Integer.parseInt(station_and_cost[1]);
                
                Opt[station] = cost;
                if (station <= m){
                    ArrayList<Integer> solution_now = new ArrayList<Integer>();
                    solution_now.add(station);
                    solutions[station] = solution_now;
                }
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