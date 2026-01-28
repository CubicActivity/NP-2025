package auditoriski.aud2_lambda_functional_interfaces;

import java.util.function.*;

public class aud2_3 {

    public static void main(String[] args){
        // Function to get the length of a string
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of \"hello\" is: "+stringLength.apply("hello"));

        // BiFunction to sum two integers
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println("Sum of 5 and 3 is: " + sum.apply(5,3));

        // Predicate to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Predicate int test (boolean lambda):");
        System.out.println("Is 10 even?: " + isEven.test(10));
        System.out.println("Is 9 even?: " + isEven.test(9));

        // Consumer to print a string
        Consumer<String> printString = str -> System.out.println("Consumer takes string to lambda print: " + str);
        printString.accept("String123!");

        // Supplier to get current time in ms
        Supplier<Long> curTimeMs = () -> System.currentTimeMillis();
        System.out.println("Supplier gets lambda CurTimeMS: " + curTimeMs.get());

    }

}
