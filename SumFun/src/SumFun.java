import java.util.Scanner;

public class SumFun {
    public static void main(String args[]){
        int a = 0, b = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter two numbers to be added together: \n");
        a = in.nextInt();
        b = in.nextInt();

        System.out.println("The sum of " + a + " and  " + b + " is "+ (a+b));
    }
}


