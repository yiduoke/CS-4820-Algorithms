import java.util.*;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;


class Test{
    public static void main(String[] args){
        LinkedList<Integer> meow = new LinkedList<Integer>();
        meow.add(5);
        meow.add(2);
        meow.add(3);
        meow.add(6);
        meow.add(1);

        meow.remove();
        meow.forEach(System.out::println);
    }
}