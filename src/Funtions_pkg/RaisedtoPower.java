package Funtions_pkg;

import java.util.Scanner;

public class RaisedtoPower {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		System.out.println("By Method 1:");
		System.out.println("Value of "+a+"raised to "+b+" is "+(int) Math.pow(a, b));
		
		System.out.println("\nBy Methhod 2");
		System.out.println("Value of "+a+"raised to "+b+" is "+aPowb(a,b));
		
		sc.close();
		
	}
	
	public static int aPowb(int a, int b ) {
		if(b==0) {
			return 1;
			
		}else if(b==1) { return a;
		}else{
		
		for(int i=2;i<=b;i++ ){
			a=a*a;
		}
	return a;
			}
		
	}
	

}
