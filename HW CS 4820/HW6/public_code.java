import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
// algorithm overview: bipartite graph but each recruiter can interview more than one candidate.
//source -> candidates; each edge has capacity 1
//candidate -> qualified recruiters; each edge has capacity 1
//recruiters -> sink; each edge has capacity of the recruiter
//if the max flow on this graph is the number of candidates, then True and return pairings
//   note: don't run the FF from the beginning; they give us a good attempt, so our residual graph will start there
//if the max flow is less than the number of candidates, then bottleneck is all the recruiters n-1 could reach on the residual graph

class Main
{
	int n; // number of candidates
	int k; // number of recruiters

	// provided data structures (already filled in)
	ArrayList<ArrayList<Integer>> neighbors;
	int[] recruiterCapacities;
	int[] preliminaryAssignment;

	// provided data structures (you need to fill these in)
	boolean existsValidAssignment;
	int[] validAssignment;
	int[] bottleneckRecruiters;

	// reading the input
	void input()
	{
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new InputStreamReader(System.in));

			String text = reader.readLine();
			String[] parts = text.split(" ");

			n = Integer.parseInt(parts[0]);
			k = Integer.parseInt(parts[1]);
			neighbors = new ArrayList<ArrayList<Integer>>(n+k);
			recruiterCapacities = new int[n+k];
			preliminaryAssignment = new int[n];

			for (int j = 0; j < n+k; j++) {
				neighbors.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < n; i++) {
				text = reader.readLine();
				parts = text.split(" ");
				int numRecruiters = Integer.parseInt(parts[0]);
				for (int j = 0; j < numRecruiters; j++) {
					int recruiter = Integer.parseInt(parts[j+1]);
					neighbors.get(i).add(recruiter);
					neighbors.get(recruiter).add(i);
				}
			}

			text = reader.readLine();
			parts = text.split(" ");
			for (int j = 0; j < k; j++) {
				recruiterCapacities[n+j] = Integer.parseInt(parts[j]);
			}

			text = reader.readLine();
			parts = text.split(" ");
			for (int i = 0; i < n-1; i++) {
				preliminaryAssignment[i] = Integer.parseInt(parts[i]);
			}

			reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// writing the output
	void output()
	{
		try
		{
			PrintWriter writer = new PrintWriter(System.out);

			if (existsValidAssignment) {
				writer.println("Yes");
				for (int i = 0; i < n-1; i++) {
					writer.print(validAssignment[i] + " ");
				}
				writer.println(validAssignment[n-1]);
			} else {
				writer.println("No");
				for (int j = 0; j < n+k-1; j++) {
					writer.print(bottleneckRecruiters[j] + " ");
				}
				writer.println(bottleneckRecruiters[n+k-1]);
			}

			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Main()
	{
		input();

		// Fill these in as instructed in the problem statement.
		existsValidAssignment = false;
		validAssignment = new int[n];
		bottleneckRecruiters = new int[n+k];

		//YOUR CODE STARTS HERE
        
        for (int i = 0; i<n-1; i++){
            int current_recruiter = preliminaryAssignment[i];
            recruiterCapacities[current_recruiter]--;
        }
        
        int s = n - 1;
        boolean[] visited = new boolean[n+k];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        
        visited[s] = true;
        queue.add(s);
        
        while (queue.size() != 0){
            s = queue.poll();
            
            if (s >= n){ // a recruiter
                if (recruiterCapacities[s] > 0){
                    existsValidAssignment = true;
                    validAssignment = preliminaryAssignment;
                    break;
                }
                else{
                    bottleneckRecruiters[s] = 1;
                    Iterator<Integer> i = neighbors.get(s).listIterator();
                    while (i.hasNext()){
                        int candidate = i.next();
                        if (preliminaryAssignment[candidate] == s && !visited[candidate]){
                            visited[candidate] = true;
                            preliminaryAssignment[candidate] = 0;
                            queue.add(candidate);
                        }
                    }
                }
            }
            else{ // a candidate
                Iterator<Integer> i = neighbors.get(s).listIterator();
                while (i.hasNext()){
                    int recruiter = i.next();
                    if (preliminaryAssignment[s] != recruiter && !visited[recruiter]){
                        visited[recruiter] = true;
                        preliminaryAssignment[s] = recruiter;
                        queue.add(recruiter);
                    }
                }
            }
        }
        
        
        
		//YOUR CODE ENDS HERE

		output();
	}

    // Strings in Args are the name of the input file followed by
    // the name of the output file.
	public static void main(String [] Args) 
	{
		new Main();
	}
}
