package testCodingChallenges;

public class ReverseANumberQuick {

    private static int revNumber(int input){
        int i = Math.abs(input);
        int rem = 0;
        int reverseVal = 0;

            while(i>0) {
                rem = i%10;
                reverseVal = (reverseVal * 10)+rem;
                i = i/10;
            }
        if (input <0 ){
            return reverseVal * (-1);
        }
        return reverseVal;
    }

    static void reverseUsingSb(int input){
        int i = Math.abs(input);
        StringBuilder sb = new StringBuilder(String.valueOf(i));
        sb.reverse();
        int revInt = Integer.parseInt(sb.toString());
        if (input<0) {
            System.out.println((-1)* revInt );
        }else{
            System.out.println(revInt);
        }

    }


    public static void main(String[] args) {
        int input = -1416;
        System.out.println(revNumber(input));

        System.out.println("using stringBuilder");
        reverseUsingSb(input);

    }
}
