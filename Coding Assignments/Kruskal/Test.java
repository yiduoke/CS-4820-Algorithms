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
        Random random = new Random();

        int[][] meow = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                meow[i][j] = random.nextInt(34);
                System.out.print(meow[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println();


        sortbyColumn(meow, 0);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                
                System.out.print(meow[i][j] + ", ");
            }
            System.out.println();
        }
    }
}