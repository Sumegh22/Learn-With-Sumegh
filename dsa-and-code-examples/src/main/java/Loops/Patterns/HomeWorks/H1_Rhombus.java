package Loops.Patterns.HomeWorks;

public class H1_Rhombus {

	public static void main(String[] args) {
		
		int n =5;
		
		for(int i =1;i<=n;i++) {
			
			for(int j=n-1;j>=i;j--) {System.out.print(" ");
			}for(int k=1;k<=n;k++) {
				System.out.print("*");
			}
				System.out.println();
		}
	}

}
