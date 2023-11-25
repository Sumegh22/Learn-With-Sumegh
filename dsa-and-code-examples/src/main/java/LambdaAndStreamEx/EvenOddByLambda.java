package LambdaAndStreamEx;

import java.util.List;

public class EvenOddByLambda {

	public static void main(String[] args) {

		List<Integer> list = List.of(1,2,3,4,5,6,7,8,9);
		
		System.out.println("Even");
		printEvenWithFp(list);
		
		System.out.println("\n\n"+"Odd");
		printOddWithFp(list);

	}

	
	private static void printOddWithFp(List<Integer> list) {
		list.stream().
		filter(element -> element%2==1).
		forEach(element-> System.out.print(element+", "));
	}

	private static void printEvenWithFp(List<Integer> list) {
		list.stream().
		filter(element -> element%2==0).
		forEach(element-> System.out.print(element+", "));
	}

}
