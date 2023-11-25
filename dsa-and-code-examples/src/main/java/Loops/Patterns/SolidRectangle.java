package Loops.Patterns;
//*****
//*****
//*****
//*****

import java.util.Scanner;

public class SolidRectangle {

	public static void main(String[] args) {

		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter num of rows for solid rect");
		int m = sc.nextInt(); // rows
		
		System.out.println("Enter column for solid rect");
		int n = sc.nextInt(); //column
		
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
	}

}
