
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,5};
        int[] e1 = {0, 3};
        Polynomial p1 = new Polynomial(c1,e1);
        double [] c2 = {-2,-9};
        int[] e2 = {1, 4};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s1 = p1.add(p2);
        System.out.println("s1(0.1) = " + s1.evaluate(0.1));
        if(s1.hasRoot(1))
            System.out.println("1 is a root of s1");
        else
            System.out.println("1 is not a root of s1");
        System.out.println(s1.exponents.length);
        for(int i = 0; i < s1.exponents.length; i++)
        {
            System.out.println("coef is" + s1.coefficients[i] + "expo" + s1.exponents[i]);
        }


        Polynomial m1 = p1.multiply(p2);
        System.out.println(m1.exponents.length);
        for(int i = 0; i < m1.exponents.length; i++)
        {
            System.out.println("coef is" + m1.coefficients[i] + "expo" + m1.exponents[i]);
        }
        System.out.println("m1(2) = " + m1.evaluate(2));
        try{
        File inputFile = new File("/Users/judy/Desktop/b07lab1/input.txt");
        Polynomial filePolynomial = new Polynomial(inputFile);

       for(int i = 0; i < filePolynomial.coefficients.length; i++)
       {
            System.out.println("coef is" + filePolynomial.coefficients[i] + "expo" + filePolynomial.exponents[i]);
       }

       String outputFile = "output.txt";
       filePolynomial.saveToFile(outputFile);}
       catch(IOException e){
        e.printStackTrace();
       }
    }
}