package Loops.Patterns;

import java.util.Scanner;

public class One1sAnd0s {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter num of rows");
		int n = in.nextInt();
		
//		
//		1 
//		0 1 
//		1 0 1 
//		0 1 0 1 
//		1 0 1 0 1 
		
		
		for(int i =1;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				int sum=i+j;
				if(sum%2==0) {System.out.print("1 ");}
				else { 
					System.out.print("0 ");}
			}
			System.out.println();
		}
	}

}
