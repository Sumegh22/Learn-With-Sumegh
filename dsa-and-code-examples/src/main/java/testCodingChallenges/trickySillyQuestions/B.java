package testCodingChallenges.trickySillyQuestions;

public class B extends A {

    @Override
    public B getANumber(){
        return new B();
    }
    public static void main(String[] args) {

        A b =  new B();

        System.out.println(b.getANumber());
    }
}
