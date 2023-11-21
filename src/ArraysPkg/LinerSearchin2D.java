package ArraysPkg;

import java.util.Scanner;

public class LinerSearchin2D {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);	
	System.out.println("Enter size of Array rows and cols !..");
	int row = sc.nextInt();
	int col = sc.nextInt();
	
	int[][] a= new int[row][col];
	
	for(int i=0;i<row;i++) {
		for(int j=0;j<col;j++) {
			a[i][j] = sc.nextInt();}
		
		System.out.println();}
	
	System.out.println("Enter Number to search in Array..");
	int f= sc.nextInt();
		
	for(int b=0;b<row;b++) {
		for (int c=0;c<col;c++) {
			if(a[b][c]==f) {
		System.out.print(a[b][c]+" is found in array at:  ("+b+","+c+")");}
								}
		System.out.println();}
							
						}	}

