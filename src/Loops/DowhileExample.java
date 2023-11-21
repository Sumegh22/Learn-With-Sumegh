package Loops;

import java.util.Scanner;

/**
 * Make a menu driven program. The user can enter 2 numbers, either 1 or 0. 
If the user enters 1 then keep taking input from the user for a student’s marks(out of 100). 
If they enter 0 then stop.
If he/ she scores :
Marks >=90 -> print “This is Good”
89 >= Marks >= 60 -> print “This is also Good”
59 >= Marks >= 0 -> print “This is Okay”

 * */

public class DowhileExample {

	public static void main(String[] args) {

		Scanner sc= new Scanner(System.in);
		
	System.out.println("Enter 1 or 0");	
		int m= sc.nextInt();
		int marks =0;
		
		if (m==1){
			
			do {
				int subm = sc.nextInt();
				
				if(subm==0) {System.out.println("Entered 0 "); break;}
				marks = marks +subm;
				}while (marks<=100);
				}
		
		else if (m==0)
		{
			System.out.println("Total marks = "+marks);
		}
		
		
		 if (marks<60) {
			 System.out.println("Total marks = "+marks);
			 System.out.println("failed");
			 
		}else if (marks >=90) {System.out.println("Total marks = "+marks);
							   System.out.println("Excellent");
			
		}else if (marks>=60 && marks<90) {System.out.println("Total marks = "+marks);
										  System.out.println("good !..");
			
		}
		
		
		
		
		
	}

}
