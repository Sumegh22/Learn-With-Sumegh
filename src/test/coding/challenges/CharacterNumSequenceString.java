package test.coding.challenges;

import java.util.HashMap;
import java.util.Map;

public class CharacterNumSequenceString {

	public static void main(String[] args) {

		findMaxRepetions("a2b3b1c5a1d7");

		// Given a String which has characters followed by their number of occurences in single-digits, the chars might repeat,
		// find the max repetition of a char in this String.
	}

	public static void findMaxRepetions(String str) {
		
		Map<Character, Integer> hm = new HashMap<>();
		char[] ch = str.toCharArray();

		for(int i = 0; i< ch.length; i+=2) {
			int currentVal = Integer.parseInt(String.valueOf(ch[i+1]));
			if(hm.get(ch[i])!=null) {
				hm.put(ch[i], hm.get(ch[i])+currentVal);
			}else {
				hm.put(ch[i], currentVal);
			}
		}System.out.println(hm);
		
	}
	
	


	
		
	

}
