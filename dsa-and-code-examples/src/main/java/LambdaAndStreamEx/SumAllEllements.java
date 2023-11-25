package LambdaAndStreamEx;

import java.util.List;

public class SumAllEllements {

	public static void main(String[] args) {

		List<Integer> l1 = List.of(1,2,4,5,6,2,4,3,5,6,4);
		
		System.out.println(addAllElements(l1));
		
		
	}

	private static int addAllElements(List<Integer> list) {
		
		int sum = list.stream().reduce(1, (number1,number2)->number1+number2);
		return sum;
						}

}
