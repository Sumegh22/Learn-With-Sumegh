package testCodingChallenges;

import java.util.List;

public class PrimeNumberTest {

	public static void isPrime(int a)
	{ 
		if (a%2==0) {
System.out.println(a+ " is even");	}
		
		System.out.println(a+" is odd");
		}
	
	
	public static void main(String[] args) {

		List <Integer> ls = List.of(1,3,1,6,6,18,14,28,46);
		
		ls.stream().forEach(PrimeNumberTest::isPrime);
		
	}

}
