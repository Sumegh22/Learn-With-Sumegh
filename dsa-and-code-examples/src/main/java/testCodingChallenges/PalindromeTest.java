package testCodingChallenges;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.logging.Logger;

public class PalindromeTest {
    public static void main(String[] args) {

        System.out.println("Enter a String to check if it is Palindrome");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        System.out.println("Is the given String a Palindrome?..:  "+checkIfPalindrome(s));

    }

    public static boolean checkIfPalindrome(String s){
        s= s.toLowerCase();
    String rev = "";
    char[] ch = s.toCharArray();
    for(int i = ch.length-1; i>=0; i-- ){
        rev = rev+ch[i];
    }
        if (rev.equals(s)){
            return true;
        }
     return false;
    }
}
