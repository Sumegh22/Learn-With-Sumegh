package Funtions_pkg;

import java.util.Scanner;

public class FiboFucntion {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Ente the num upto which Fibo is to be printed:");
		int n=sc.nextInt();
		
		if(n>0) {
			printFibo(n);
		}
		
		
	}	
	
	public static void printFibo(int n) {
		
		int a =0; int b=1;
		System.out.print(a+" ");
		for(int i=1;i<=n;i++)
		{
			System.out.print(b+" ");
			int temp = b;
			b=a+b;
			a=temp;
		}	
										}
	
						}


