package testCodingChallenges.trickySillyQuestions;


abstract class ParentAbsClass {
    int number1;
    abstract ParentAbsClass getANumber(int number);

    public ParentAbsClass (int num){
        this.number1 = num;
    }
}

class ChildAbsClasImpl  extends ParentAbsClass {

    public ChildAbsClasImpl(int num) {
        super(num);
    }

    @Override
    public  ChildAbsClasImpl getANumber(int number){
        return new ChildAbsClasImpl(5);
    }



    public static void main(String[] args) {


        ParentAbsClass b =  new ChildAbsClasImpl(1);
        System.runFinalization();

        System.out.println(b.getANumber(1));
    }
}
