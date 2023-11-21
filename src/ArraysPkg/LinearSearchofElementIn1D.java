package ArraysPkg;

import java.util.Scanner;

public class LinearSearchofElementIn1D {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		 System.out.println("Enter Size of Array");

		int s= sc.nextInt();
		 int [] arr = new int[s];
		 
		 System.out.println("Enter elements of Array");
		 for (int i =0;i<s;i++) {
			 arr[i]=sc.nextInt();
		 }
		
		 for(int j:arr) {System.out.print(j+" ");}
		 
		 System.out.println("\nEnter Number to sear in this Array !..");
		  int f= sc.nextInt();
		  
		  for(int j=0;j<s;j++) {
			  if (arr[j]==f) {System.out.println("\nNumber "+f+" is found in the Array at "+j);}
		  }
		 
	}

}
