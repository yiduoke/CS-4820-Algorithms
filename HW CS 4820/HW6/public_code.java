import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;

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
		// boolean[] visited = new boolean[n+k];
		int[] parent = new int[n+k]; //also works as visited; -1 means not visited
		Arrays.fill(parent, -1);
        LinkedList<Integer> queue = new LinkedList<Integer>();

		int s = n - 1;

		//YOUR CODE STARTS HERE
        
        for (int i = 0; i<n-1; i++){
            int current_recruiter = preliminaryAssignment[i];
            recruiterCapacities[current_recruiter]--;
        }
        
		parent[s] = -2;
        queue.add(s);
        
        while (queue.size() != 0){
            s = queue.poll();
            
            if (s >= n){ // a recruiter
                if (recruiterCapacities[s] > 0){
                    existsValidAssignment = true;
                    validAssignment = preliminaryAssignment;

					int current_person = s;
					while (current_person != n-1){
						int current_parent = parent[current_person];
						if (current_person >= n){ //recruiter
							validAssignment[current_parent] = current_person;
						}
						current_person = current_parent;
					}
                    break;
                }
                else{
                    bottleneckRecruiters[s] = 1;
                    Iterator<Integer> i = neighbors.get(s).listIterator();
                    while (i.hasNext()){
                        int candidate = i.next();
						if (preliminaryAssignment[candidate] == s && parent[candidate] == -1){
							parent[candidate] = s;
                            queue.add(candidate);
                        }
                    }
                }
            }
            else{ // a candidate
                Iterator<Integer> i = neighbors.get(s).listIterator();
                while (i.hasNext()){
                    int recruiter = i.next();
					if (preliminaryAssignment[s] != recruiter && parent[recruiter] == -1){
						parent[recruiter] = s;
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
