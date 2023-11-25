package Loops.Patterns.HomeWorks;

public class H5_HollowButterfly {

	public static void main(String[] args) {

		int n = 5;
		
		//upper half
		for(int i=1;i<=n;i++){
			
			//Left Portion
				for(int j=1;j<=i;j++) {
					if(j==1 ||j==i) {System.out.print("*");}
					else { System.out.print(" "); }
										}
			
			//Space condtion for Hollow
			int sp= 2*(n-i);
			for(int j=sp;j>=1;j--){System.out.print(" "); }
			
			
			//Right Portion
			for(int j=1;j<=i;j++){
				if(j==1 ||j==i) {System.out.print("*");}
				else { System.out.print(" "); }
										}
			
			System.out.println();
			
								}
		//upper half
				for(int i=n;i>=1;i--){
					
					//Left Portion
						for(int j=1;j<=i;j++) {
							if(j==1 ||j==i) {System.out.print("*");}
							else { System.out.print(" "); }
												}
					
					//Space condtion for Hollow
					int sp= 2*(n-i);
					for(int j=sp;j>=1;j--){System.out.print(" "); }
					
					
					//Right Portion
					for(int j=1;j<=i;j++){
						if(j==1 ||j==i) {System.out.print("*");}
						else { System.out.print(" "); }
												}
					
					System.out.println();
					
										}
		
		
	}

}
