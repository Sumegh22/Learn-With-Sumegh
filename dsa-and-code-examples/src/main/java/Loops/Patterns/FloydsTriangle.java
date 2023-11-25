package Loops.Patterns;

import java.util.Scanner;

public class FloydsTriangle {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter num of rows");
		int n = in.nextInt();
		int num=1;
		
		
//		1 
//		2 3 
//		4 5 6 
//		7 8 9 10 
//		11 12 13 14 15 
		
		for(int i =1;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				System.out.print(num+" ");
				num+=1;
			}
			System.out.println();
		}
	}

}
