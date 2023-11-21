package test.coding.challenges;

public class ReverseAString {

	public static void main(String[] args) {

		reverseMethod1("Language");
		reverseMethod2("Sumegh");
		reverseMethod3("Waah Kya Baat Hai");
		
	}

	// Using StringBuilder
	private static void reverseMethod1(String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		System.out.println(sb);
	}

	// Using for loop
	private static void reverseMethod2(String str1) {
		String s="";
		for(int i=(str1.length()-1);i>=0;i--) {
			s+=str1.charAt(i);
		}
		System.out.println(s);
	}

	// Reverse Using recursion Method
	private static void reverseMethod3(String input) {
		input = input.toLowerCase();
		if ((input == null) || (input.length() <= 1)){
			System.out.println(input);
		}else
		{
			System.out.print(input.charAt(input.length()-1));
			reverseMethod3(input.substring(0,input.length()-1));
		}
	}

}
