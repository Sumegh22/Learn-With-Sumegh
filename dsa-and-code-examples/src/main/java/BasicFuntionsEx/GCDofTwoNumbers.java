package BasicFuntionsEx;

import java.util.Scanner;

public class GCDofTwoNumbers {
	
	public static int getGCD(int n1, int n2 ) {
		
		
		while(n1!=n2)
		{
			if(n1>n2) {
				n1=n1-n2;
			}else {
				n2= n2-n1;
			}
		}
		return n2;		
	}

	public static void main(String[] args) {
		
		Scanner sc = new  Scanner(System.in);
		System.out.println("Enter 2 numbers to find GCD ");
		int a= sc.nextInt();
		int b = sc.nextInt();
		
		System.out.println("\nGCD of two numbers "+a+", "+b+" is "+getGCD(a,b));
		
		
		
	}

}
