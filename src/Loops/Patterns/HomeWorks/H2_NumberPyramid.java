package Loops.Patterns.HomeWorks;

public class H2_NumberPyramid {

	public static void main(String[] args) {
		
		int n=5;
		
		for(int i =1;i<=n;i++) { 				//Loop for iterations/ repetations
			
			for(int j=1;j<=n-i;j++) {System.out.print(" ");} // spaces
			
			for(int j=1; j<=i;j++) {System.out.print(i +" ");}
			
			System.out.println();
		}
		
	}

}
