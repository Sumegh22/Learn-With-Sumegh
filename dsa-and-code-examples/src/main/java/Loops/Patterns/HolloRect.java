package Loops.Patterns;
//**********
//*        *
//*        *
//*        *
//**********


import java.util.Scanner;

public class HolloRect {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter num of rows");
		int m = in.nextInt();
		
		System.out.println("Enter num of cols");
		int n = in.nextInt();
		
		
		for(int i=1;i<=m;i++) {
			for (int j=1;j<=n;j++) {
				 if (i==1||i==m ||j==1||j==n) {
								System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			 System.out.println();

		}
			
		
	}

}
