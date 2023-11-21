package Loops.Patterns.AdvancedPat;

public class DiamondPattern {

	public static void main(String[] args) {
		
		int n= 5;

				//upper half
	for(int i=1;i<=n;i++ ) {
			
		for(int j=1;j<=n-i;j++) {System.out.print(" ");} //spaces 
		
		for(int j=1;j<=2*i-1;j++) {System.out.print("*");}
		
		
		System.out.println();}	
		
	
	//lower half
	for(int i=n;i>=1;i-- ) {
		
		for(int j=1;j<=n-i;j++) {System.out.print(" ");} //spaces 
		
		for(int j=1;j<=2*i-1;j++) {System.out.print("*");}
		
		
		System.out.println();}
	
	}
	
	

}
