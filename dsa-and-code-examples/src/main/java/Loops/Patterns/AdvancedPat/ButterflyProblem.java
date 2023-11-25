package Loops.Patterns.AdvancedPat;
import java.util.Scanner;

public class ButterflyProblem {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter a number to see butterfly");
	int n = sc.nextInt();
		
	//upper half
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=i;j++) {System.out.print("*");} //printing stars
			
			int spaces = 2*(n-i);
			for(int j=1;j<=spaces;j++) {System.out.print(" ");} //spaces
			
			for(int j=1;j<=i;j++) {System.out.print("*");} //stars for next half
			
			System.out.println();
			}
			
	//Lower Half
		for(int i=n;i>=1;i--) {
			for(int j=1;j<=i;j++) {System.out.print("*");} //printing stars
			
			int spaces = 2*(n-i);
			for(int j=1;j<=spaces;j++) {System.out.print(" ");} //spaces
			
			for(int j=1;j<=i;j++) {System.out.print("*");} //stars for next half
			
			System.out.println();
			}
		
		}
	
	}


