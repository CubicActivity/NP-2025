package auditoriski.aud2_lambda_functional_interfaces;


interface Operations {
    int apply (int a, int b);
}

interface MessageProvider {
    String getMessage();
}


class Addition implements Operations{

    @Override
    public int apply(int a, int b) {
        return a+b;
    }
}

class StaticMessage implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hello from regular class";
    }
}


public class aud2_1 {
    public static void main (String [] args){

        // Regular Classes
        Operations op1 = new Addition();
        System.out.println("Addition: " + op1.apply(5,3));

        MessageProvider m1 = new StaticMessage();
        System.out.println(m1.getMessage());


        // Anonymous Classes
        Operations op2 = new Operations(){
            @Override
            public int apply(int a, int b){
                return a * b;
            }
        };
        System.out.println("Multiplication: " + op2.apply(5,3));

        MessageProvider m2 = new MessageProvider(){
            @Override
            public String getMessage(){
                return "Hello from anonymous class";
            }
        };
        System.out.println(m2.getMessage());


        // Lambda
        Operations op3 = (a, b) -> a -b;
        System.out.println("Subtraction: " + op3.apply(5,3));

        MessageProvider m3 = () -> "Hello from a lambda!";
        System.out.println(m3.getMessage());


    }
}
