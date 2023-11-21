package Loops.Patterns;
import java.util.Scanner;

public class ReverseNumberPyramid {

		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter num of rows");
			int n = in.nextInt();
		
//			5 4 3 2 1 
//			5 4 3 2 
//			5 4 3 
//			5 4 
//			5 			
			
			for(int i=1;i<=n;i++) {
				for(int j=n;j>=i;j--) {
					System.out.print(j+" ");
				}System.out.println();
			}
			
			System.out.println("\n Next Pattern \n");	
			
			
//			1 2 3 4 5 
//			1 2 3 4 
//			1 2 3 
//			1 2 
//			1
//			
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n-i+1;j++) {
					System.out.print(j+" ");
				}System.out.println();
			}
			
	}

}
