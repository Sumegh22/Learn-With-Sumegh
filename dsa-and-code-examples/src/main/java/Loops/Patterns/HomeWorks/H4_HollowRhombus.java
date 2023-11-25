package Loops.Patterns.HomeWorks;

public class H4_HollowRhombus {

	public static void main(String[] args) {

		int n=6;
		
		for(int i=1;i<=n;i++) {
			
			for(int j=1;j<=n-i;j++) {System.out.print(" ");	}//spaxes
		
			
			for(int j=1;j<=n;j++)
			{			
					if(i==1 ||i==n || j==1 ||j==n) //condition for star boundary
					{ System.out.print("*");
						}
					else {
					System.out.print(" "); //keeping spaces for hollow portion
							}
				
			 }
		System.out.println();
		 							}
		
		
	}

}
