import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static ArrayList<Long> binary = new ArrayList<>();

    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Extended Euclid Algorithm Test\n");
//
//        /** Make an object of ExtendedEuclid class **/
//        Main ee = new Main();
//
//        /** Accept two integers **/
//        System.out.println("Enter a b of ax + by = gcd(a, b)\n");
//        long a = scan.nextLong();
//        long b = scan.nextLong();
//        /** Call function solve of class ExtendedEuclid **/
//        ee.solve(a, b);
//
//        BigInteger bi = new BigInteger("26");
//        System.out.println(bi.modPow(new BigInteger("12"), new BigInteger("3120")));

        long d = 2753; // exponent
        long m = 1519; // main number
        long origM = m;
        m = 1;
        long e = 35;
        long n = 3233; // modulus

        toBinary(d);

        //modulus method
        for (int i = 0; i < binary.size(); i++) {
            System.out.print(i+1 + ". " + m + "(" + m + ") % " + n + " = ");
            m = (m * m) % n;
            System.out.println(m);
            if (binary.get(i) == 1) {
                System.out.print("   " + m + "(" + origM + ") % " + n + " = ");
                m = (m * origM) % n;
                System.out.println(m);
            }
        }
    }

    public static void toBinary(long num){
        long original = num;
        while (num > 0) {
            binary.add(num % 2);
            num = num / 2;
        }
        Collections.reverse(binary);
        System.out.print(original + " in binary: ");
        for (long number : binary) {
            System.out.print(number);
        }
        System.out.println();
    }

//public void solve(long a, long b)
//
//{
//
//    long x = 0, y = 1, lastx = 1, lasty = 0, temp;
//
//    while (b != 0)
//
//    {
//
//        long q = a / b;
//
//        long r = a % b;
//
//
//
//        a = b;
//
//        b = r;
//
//
//
//        temp = x;
//
//        x = lastx - q * x;
//
//        lastx = temp;
//
//
//
//        temp = y;
//
//        y = lasty - q * y;
//
//        lasty = temp;
//
//    }
//
//    System.out.println("Roots  x : "+ lastx +" y :"+ lasty);
//
//}
}
