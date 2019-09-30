import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Main{
    
    public static int index_of_first_sig_greater(long[] array, long num, int low, int high){
        while (low <= high){
            int m = (high + low) / 2;
            if (array[m] > num + 1 && (m == 0 || array[m-1] <= num + 1)){
                return m;
            }
            else if (array[m] > num + 1){
                high = m-1;
            }
            else{
                low = m+1;
            }
        }
        return array.length;
    }
    
    public static long[][] merge_and_count(long[] left, long[] right){
        long inversions = 0;
        int left_current = 0;
        int right_current = 0;
        long[] merged_list = new long[left.length + right.length];
        
        while (left_current < left.length && right_current < right.length) {
            long a = left[left_current];
            long b = right[right_current];
            if (a <= b){
                merged_list[left_current + right_current] = a;
                left_current++;
            }
            else {
                merged_list[left_current + right_current] = b;
//                if (b+1 < a) {
//                    inversions += left.length - left_current;
//                    System.out.println("a and b: " + a + ", " + b);
//                }
                inversions += left.length - index_of_first_sig_greater(left, b, 0, left.length - 1);
                right_current++;
            }
        }
        
        // fill from the rest of the left array
        while (left_current < left.length){
            merged_list[left_current + right_current] = left[left_current];
            left_current++;
        }
        
        // fill from the rest of the right subarray
        while (right_current < right.length){
            merged_list[left_current + right_current] = right[right_current];
            right_current++;
        }
        
        long[][] inversions_and_merged = new long[2][left.length + right.length];
        inversions_and_merged[0] = merged_list;
        inversions_and_merged[1][0] = inversions;
        return inversions_and_merged;
    }
    
    public static long[][] sort_and_count(long[] array){
        long[][] merged_and_sorted = new long[2][array.length];
        int inversions;
        if (array.length == 1){
            merged_and_sorted[1][0] = 0;
            merged_and_sorted[0] = array;
        }
        else{
            int m = array.length / 2;
            long[] left = Arrays.copyOfRange(array, 0, m);
            long[] right = Arrays.copyOfRange(array, m, array.length);
            
//            System.out.println(Arrays.toString(left));
//            System.out.println(Arrays.toString(right));
            
            long[][] sorted_and_counted_left = sort_and_count(left);
            long[][] sorted_and_counted_right = sort_and_count(right);
            
            left = sorted_and_counted_left[0];
            right = sorted_and_counted_right[0];
            
//            System.out.println(Arrays.toString(left));
//            System.out.println(Arrays.toString(right));
            
            merged_and_sorted = merge_and_count(left, right);
            merged_and_sorted[1][0] =  sorted_and_counted_left[1][0] + sorted_and_counted_right[1][0] + merged_and_sorted[1][0];
        }
        return merged_and_sorted;
    }
    
    public static void main(String[] args){
        
        int n = 0; //length of the array
        long[] array = new long[1];
        
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        
        // reading the input
        try{
            
            String line = inputStream.readLine();
            n = Integer.parseInt(line);
            line = inputStream.readLine();
            String[] array_string = line.split(" ");
            
            array = new long[n];
            
            for (int i = 0; i < n; i++){
                array[i] = Integer.parseInt(array_string[i]);
            }
            
            inputStream.close();
        }
        
        catch(java.io.IOException ex){
            System.out.println("bad file");
        }
        
        long startTime = System.nanoTime();
        
        System.out.println((sort_and_count(array))[1][0]);
    }
}
