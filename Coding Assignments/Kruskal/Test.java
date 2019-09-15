import java.util.*; 

class Test {
    public static void sortbyColumn(int arr[][], int col){ 
            // Using built-in sort function Arrays.sort 
        Arrays.sort(arr, new Comparator<int[]>() { 
                
            @Override              
                // Compare values according to columns 
            public int compare(int[] arr1, int[] arr2) { 
                return arr1[col] - arr2[col];
            }  // End of function call sort(). 
        });
    }
    
    public static void main (String[] args){
        Integer one = Integer.valueOf(1);
        Integer two = Integer.valueOf(2);
        Integer three = Integer.valueOf(3);
        Integer four = Integer.valueOf(4);

        two = one;
        three = two;

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }
}