package Loops.Patterns.HomeWorks;

public class H3_PalindromicPyramid {

	public static void main(String[] args) {
		
	
	int n=5;
	
	for(int i =1;i<=n;i++) { 				//Loop for iterations/ repetations
		
		for(int j=(n-i);j>=1;j--)	{System.out.print(" ");} // spaces		
				
		for(int j=i;j>=1;j--) {System.out.print(j);} // left num pyramid
		
		for(int j=2;j<=i;j++) {System.out.print(j);} // right pyramid
		
		
		System.out.println();
			
		
		}
		
	}
	
}
