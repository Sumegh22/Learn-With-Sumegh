package test.coding.challenges;

import java.util.HashMap;
import java.util.Map;

public class DuplicateWordsinAString {

	public static void findDuplicates(String str) {
		
		Map<String, Integer> hm = new HashMap<>();
		String[] sar= str.split(" ");
		for(String s: sar) {
			if(hm.get(s)!=null) {
				
				hm.put(s, hm.get(s)+1);
				
			}else {
				hm.put(s, 1);
			}
		}System.out.println(hm);
		
	}
	
	
	public static void main(String[] args) {
		
		findDuplicates("Hello Hello my name name name is Sumegh");
		
	}

	
		
	

}
