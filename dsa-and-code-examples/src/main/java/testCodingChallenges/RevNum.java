package testCodingChallenges;

import java.util.Scanner;

//Write a logic to reverse a Number or a String

public class RevNum {
    public static int reverseAnInteger(int x) {
        int y = Math.abs(x);
        String s = String.valueOf(y);
        char[] ch  = s.toCharArray();
        StringBuilder sb= new StringBuilder("");
        for (int i=ch.length-1; i>=0; i--){sb.append(ch[i]);}

        int finalNum = Integer.parseInt(sb.toString());
        return isInRange(x, finalNum);
    }

    static int isInRange(int originalVal, int finalVal){
        final int ceil = (int) Math.pow(2,31) -1;
        final int floor = (int) Math.pow(-2,31) ;

        if (originalVal<0){ finalVal = -1 * finalVal;}
        if (finalVal < floor){      return 0; }
        else if (finalVal > ceil){  return 0; }
        return finalVal;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number to be reversed");
        int x = in.nextInt();
        System.out.println("The reversed integer of entered: "+x+" is: "+reverseAnInteger(x));

    }
}


