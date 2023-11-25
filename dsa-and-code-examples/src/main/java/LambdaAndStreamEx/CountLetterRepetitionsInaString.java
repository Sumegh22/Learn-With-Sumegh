package LambdaAndStreamEx;

import java.util.HashMap;
import java.util.Map;

public class CountLetterRepetitionsInaString {
    public static void main(String[] args) {
        String inputString = "hello world";

        Map<Character, Integer> letterCountMap = new HashMap<>();

        // Count repetitions of each letter
        for (char c : inputString.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCountMap.put(c, letterCountMap.getOrDefault(c, 0) + 1);
            }
        }

        // Print the results
        System.out.println("Letter repetitions in the string:");

        for (Map.Entry<Character, Integer> entry : letterCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
