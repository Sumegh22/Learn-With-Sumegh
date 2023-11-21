package Loops.Patterns;

import java.util.Scanner;

public class NumberPyramid {

		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter num of rows");
			int n = in.nextInt();
			
			for(int i =1;i<=n;i++) {
				for(int j=1;j<=i;j++) {
					System.out.print(j+" ");
				}
				System.out.println();
			}
		
	}

}
