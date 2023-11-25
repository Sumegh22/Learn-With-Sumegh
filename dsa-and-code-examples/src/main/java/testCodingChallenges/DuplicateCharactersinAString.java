package testCodingChallenges;

import java.util.HashMap;
import java.util.Map;

public class DuplicateCharactersinAString {

	public static void findDuplicates(String str) {
		
		Map<Character, Integer> hm = new HashMap<>();
		char[] ch = str.toCharArray();
		for(char c: ch) {
			if (c==' '){
				continue;  // This block skips the loop iteration when a black space is encountered
			}
			if(hm.get(c)!=null) {
				hm.put(c, hm.get(c)+1);
			}else {
				hm.put(c, 1);
			}
		}System.out.println(hm);
		
	}
	
	
	public static void main(String[] args) {
		
		findDuplicates("Hello Hello my name name name is Sumegh");
		
	}

	
		
	

}
