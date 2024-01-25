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

        // Create List of Strings for words which are anagrams
        List<List<String>> ListOfAanagramsList = new ArrayList<>();

        // Filter out groups with only one word (non-anagrams)
        List<String> nonAnagrams = new ArrayList<>();

        for (List<String> group : anagramMap.values()) {
            if (group.size() == 1) {
                nonAnagrams.add(group.get(0));
            }
            if (group.size() > 1) {
                ListOfAanagramsList.add(group);
            }
        }

        System.out.println("This list contains, words which were not ANagrams"+ nonAnagrams+"\n");



        return ListOfAanagramsList;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("listen", "silent", "enlist", "heart", "earth", "night", "thing", "Adrak", "Lassan", "Hahaha");

        List<List<String>> anagramGroups = findAnagrams(words);

        System.out.println("Anagram groups:");
        for (List<String> group : anagramGroups) {
            System.out.println(group);
        }
    }
}
