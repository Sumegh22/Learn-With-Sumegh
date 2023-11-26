package testCodingChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFinderList {
    public static List<List<String>> findAnagrams(List<String> words) {
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Group words by their sorted character array representation (anagram key)
        for (String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            // Add the word to the list associated with its anagram key
            anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }

        // Filter out groups with only one word (non-anagrams)
        List<List<String>> result = new ArrayList<>();
        for (List<String> group : anagramMap.values()) {
            if (group.size() > 1) {
                result.add(group);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("listen", "silent", "enlist", "heart", "earth", "night", "thing");

        List<List<String>> anagramGroups = findAnagrams(words);

        System.out.println("Anagram groups:");
        for (List<String> group : anagramGroups) {
            System.out.println(group);
        }
    }
}
