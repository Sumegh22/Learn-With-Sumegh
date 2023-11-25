package LambdaAndStreamEx;

import java.util.List;

public class FunctionalProgammingRunner {

	public static void main(String[] args) {

		List<String> list = List.of("Adam","Eve","God","Satan","Cat","Rat","Bat");	
		
		
		printBasic(list);			
		System.out.println("-----------End------------\n-- Implented with Lamda---\2n");
		printWithFP(list);
		System.out.println("\n-----------End------------\n\n-- Filtered with  Lamda---\n");
		printWithFpFilter(list);

	
	}

	
	
	public static void printBasic(List<String> list) {
		for (String item:list)
		{
			System.out.println(item);
		}
	}

	
	public static void printWithFP(List<String> l) {
		
		l.stream().forEach(element -> System.out.print(" -- "+element));
	}
	
public static void printWithFpFilter(List<String> l) {
		
		l.stream()
		.filter(element -> element.endsWith("at"))
		.forEach(element -> System.out.println(" ELement- "+element));
	}
}
