package ArraysPkg;

import java.util.Scanner;

public class MaxMinIn1D {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the size of Array");
	    int s = sc.nextInt();
	    
	    int [] arr= new int [s];
	    System.out.println("Enter the elememts of Array");
	    
	    for(int i=0;i<s;i++) {arr[i] = sc.nextInt();}
	    
	    int min= Integer.MAX_VALUE;
	    int max= Integer.MIN_VALUE;
	    
	    for(int a :arr) {
	    	
	    	if(a<min){min=a;
	    	}
	    	if(a>max){max=a;
	    	}
	    	
	    }
	    System.out.println("The max value in Array is: "+max+
	    		"\nThe min value in Array is: "+min);
	    }
		
	}


