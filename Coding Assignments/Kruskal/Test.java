import java.util.*; 

class Test {
    
    public class Number{
        int value;
        Number(int value){
            this.value = value;
        }
        public String toString() {
            return "" + value;
        }

        public int get(){
            return value;
        }
    }

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
    
        Test o = new Test();

        
        Test.Number one = o.new Number(1);
        Test.Number two = o.new Number(2);
        Test.Number three = o.new Number(3);
        Test.Number four = o.new Number(4);
        two = one;
        three = two;
        four = two;

        one.value = 2;

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);

        one.value = 3;
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(four == two);
    }
}