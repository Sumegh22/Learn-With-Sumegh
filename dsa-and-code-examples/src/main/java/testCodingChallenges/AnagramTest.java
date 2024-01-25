package testCodingChallenges;

import java.sql.Array;
import java.util.*;

public class AnagramTest {


    private static List<List<String>> findAnagrams(List<String> inputList) {

        Map<String, List<String>> mapOfAnagrams = new HashMap<>();

        for(String s : inputList){
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String sortedWord = new String(ch);
            mapOfAnagrams.computeIfAbsent(sortedWord, e->new ArrayList<>()).add(s);
        }

        List<List<String>> anagramList = new ArrayList<>();
        for(List<String> ls :mapOfAnagrams.values() ){

            if(ls.size()>1){
                anagramList.add(ls);
            }
        }
        return anagramList;
    }

    public static void main(String[] args) {

        List<String> inputList = List.of("cat","tac","god","dog","rat","tar","super","awe");

        List<List<String>> anagramList = findAnagrams(inputList);
        System.out.println(anagramList);
    }

}
