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
//if the max flow is less than the number of candidates, then increase the capacity of EVERY QUALIFIED recruiter of
//uninterviewed candidates by 1 ONLY ONCE (since many candidates can go to one recruiter and we can accidentally increase capacity by more
//than 1) and run FF from that point every recruiter whose incoming flow increased by one is part of the answer!
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