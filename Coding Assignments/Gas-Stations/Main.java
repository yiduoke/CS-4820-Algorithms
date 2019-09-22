import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{

    static int min_index(long[] array, int start_inclusive, int end_inclusive){
      int min_index = start_inclusive;
      long min = array[start_inclusive];

      for (int i = start_inclusive; i <= end_inclusive; i++){
          if (array[i] != 0){
              min_index = i;
              min = array[i];
              break;
          }
      }
     
      for(int i = min_index; i <= end_inclusive; i++) {
         if(array[i] < min && array[i] != 0) {
            min = array[i];
            min_index = i;
         }
      }
      return min_index;
    }

    public static void main(String[] args){

        // initializing the data structures
        long[] Opt = new long[1]; // minimum cost up until each station
        ArrayList<Integer>[] Solutions = new ArrayList[1]; //solution up until each station
        int[] solution_index;
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
            solution_index = new int[M+1];

            for (int i = 0; i < n; i++){
                line = inputStream.readLine();
                String[] station_and_cost = line.split(" ");

                int station = Integer.parseInt(station_and_cost[0]);
                long cost = Integer.parseInt(station_and_cost[1]);
                
                Opt[station] = cost;
                if (station <= m){
                    ArrayList<Integer> solution_now = new ArrayList<Integer>();
                    solution_now.add(station);
                    Solutions[station] = solution_now;
                }
            }

            inputStream.close();
        }

        catch(java.io.IOException ex){
            System.out.println("bad file");
        }

        long startTime = System.nanoTime();


        /////////////////////////////////the actual algorithm now//////////////////////////////////

        for (int i = m+1; i < M; i++){
            if (Opt[i] != 0){
                int min_station = min_index(Opt, i-m, i-1); //TODO: write min_index(int array, int start, int end) later
                Opt[i] = Opt[i] + Opt[min_station];
                ArrayList<Integer> prev_solution = (ArrayList<Integer>) (Solutions[min_station]).clone();
                prev_solution.add(i);
                Solutions[i] = prev_solution;
            }
        }

        int min_cost_station = min_index(Opt, M-m, M);
        System.out.println(Opt[min_cost_station]);
        ArrayList<Integer> opt_solution = Solutions[min_cost_station];
        for (int i = 0; i < opt_solution.size(); i++){
            System.out.print(opt_solution.get(i) + " ");
        }
        System.out.println();
    }
}