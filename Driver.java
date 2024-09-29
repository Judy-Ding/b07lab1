import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = { 6, 5 };
        int[] e1 = {0,3};
        Polynomial p1 = new Polynomial(c1,e1);
        double[] c2 = { -2, -9 };
        int[] e2 = {1,4};
        Polynomial p2 = new Polynomial(c2,e2);
        Polynomial s = p1.add(p2);

        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        
        //test multiply
        Polynomial product = p1.multiply(p2);
        System.out.println("product(-1) = " + product.evaluate(-1));//-7
        System.out.println("product(-2) = " + product.evaluate(-2));//4760
        System.out.println("product(1) = " + product.evaluate(1));//-121
        System.out.println("product(2) = " + product.evaluate(2));//-6808
        
        
        double[] c3 = { 4, 3 };
        int[] e3 = { 2, 3 };
        Polynomial p3 = new Polynomial(c3, e3);
        
        double[] c4 = { -4, 3 };
        int[] e4 = { 2, 3 };
        Polynomial p4 = new Polynomial(c4, e4);
        
        Polynomial redundantSum = p3.add(p4);
        System.out.println("Redundant Sum evaluated at 1: " + redundantSum.evaluate(1));  // 6
        System.out.println("Redundant Sum evaluated at 2: " + redundantSum.evaluate(2));  // 48
        
        Polynomial product2 = p3.multiply(p4);
        System.out.println("product(-1) = " + product2.evaluate(-1));//-7
        System.out.println("product(-2) = " + product2.evaluate(-2));//320
        System.out.println("product(1) = " + product2.evaluate(1));//-7
        System.out.println("product(2) = " + product2.evaluate(2));//320
        
        try {
        	File testFile = new File("testPolynomial.txt");
            Polynomial p5 = new Polynomial(testFile);
            System.out.println("Polynomial loaded from file.");
            
            String fileName = "output_polynomial.txt";
            p5.saveToFile(fileName);
            System.out.println("Polynomial saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
    }
}