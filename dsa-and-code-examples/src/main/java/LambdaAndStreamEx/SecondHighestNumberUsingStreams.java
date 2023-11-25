package LambdaAndStreamEx;

import java.util.Arrays;
public class SecondHighestNumberUsingStreams {
        public static void main(String[] args) {
            int[] numbers = {10, 5, 8, 20, 15};

            int secondHighest = Arrays.stream(numbers)
                    .boxed()
                    .sorted((a, b) -> b - a)
                    .distinct()
                    .skip(1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Array is too small to find the second highest number."));

            System.out.println("Second Highest Number: " + secondHighest);
        }
    }

