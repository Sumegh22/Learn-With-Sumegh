package Loops;

import java.util.Scanner;

/*
 * Print all even numbers till n.
*/

public class ForLoopExample {

	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		int n = in.nextInt();
		for(int i=2;i<n;i+=2)
			System.out.print(i+" ");
		
		
	}

}
