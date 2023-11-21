package Loops.Patterns;

//*******
//******
//*****
//****
//***
//**
//*


import java.util.Scanner;

public class ReverseHalfPyramid {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter num of rows");
		int m = in.nextInt();
		
		for(int i=1;i<=m;i++) {
			for(int j=m;j>=i;j--) {
				System.out.print("*");
			}System.out.println();
			
		}
		
		System.out.println("\n Using method 2 \n");
		
		for(int i=m;i>=1;i--) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}System.out.println();
		}
		 
		
	}
	
	
	}
	

