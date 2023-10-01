
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polynomial{
    //I
    double[] coefficients;
    int[] exponents;

    //II
    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0.0;
        exponents = new int[1];
        exponents[0] = 0; 
    }

    //III
    public Polynomial(double[] my_array, int[] my_exponents){
        //for loop change the double from my array to A
        coefficients = new double[my_array.length];
        exponents = new int[my_exponents.length];
        for(int i = 0; i<my_array.length; i++)
        {
            coefficients[i] = my_array[i];
            exponents[i] = my_exponents[i];
        }
    }

    public Polynomial(File file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] terms = line.split("(?=[-+])");
        double[] resultcoefficients = new double[terms.length];
        int[] resultexponents = new int[terms.length];
        int k = 0;
        for(int i = 0; i < terms.length; i++)
        {
            String[] parts = terms[i].split("x");
            if(parts.length == 1)
            {
                resultcoefficients[k] = Double.parseDouble(parts[0]);
                resultexponents[k] = 0;
            }
            else
            {
                resultcoefficients[k] = Double.parseDouble(parts[0]);
                resultexponents[k] = Integer.parseInt(parts[1]);
            }
            k++;
        }
        this.coefficients = resultcoefficients;
        this.exponents = resultexponents;
        reader.close();
    }

    //IV add
    //same exponents, return the number of same exponents of the two formulas
    public int sameExponents(int[] A, int[] B)
    {
        int sum = 0;
        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < B.length; j++)
            {
                if(A[i] == B[j]){
                    sum = sum + 1;
                }
            }
        }
        return sum;
    }

    //if there is a duplicate exponents in the given exponents array, return the index of 
    public boolean inArray(int exponent, int[] given_exponents)
    {
        for(int i =0; i<given_exponents.length; i++)
        {
            if(given_exponents[i]==exponent)
                return true;
        }
        return false;
    }
    
    //given polynomial, combine it with A
    public Polynomial combine(Polynomial given_polynomial)
    {
        int length = this.exponents.length + given_polynomial.exponents.length;
        double[] resultcoefficients = new double[length];
        int[] resultexponents = new int[length];
        int k = 0;
        for(int i = 0; i <this.exponents.length;i++)
        {
            resultcoefficients[i] = this.coefficients[i];
            resultexponents[i] = this.exponents[i];
        }
        for(int j = this.exponents.length; j <length;j++)
        {
            resultcoefficients[j] = given_polynomial.coefficients[k];
            resultexponents[j] = given_polynomial.exponents[k];
            k++;
        }
        return new Polynomial(resultcoefficients, resultexponents);
    }

    public Polynomial move_zeros(Polynomial A)
    {
        int length = 0;
        for(int i = 0; i <A.coefficients.length; i++)
        {
            if(A.coefficients[i] != 0)
                length++;
        }
        double[] resultcoefficients = new double[length];
        int[] resultexponents = new int[length];
        int k = 0;
        for(int j = 0; j <A.coefficients.length; j++)
        {
            if(A.coefficients[j] != 0)
            {
                resultcoefficients[k] = A.coefficients[j];
                resultexponents[k] = A.exponents[j];
                k++;
            }
        }
        if(length == 0)
        {
            return null;
        }
        return new Polynomial(resultcoefficients,resultexponents);
    }

    public Polynomial add(Polynomial given_Polynomial)
    {   
        Polynomial combined = combine(given_Polynomial);
        int length = this.exponents.length + given_Polynomial.exponents.length - sameExponents(this.exponents, given_Polynomial.exponents);
        double[] resultcoefficients = new double[length];
        int[] resultexponents = new int[length];
        for(int m = 0; m < resultexponents.length; m++)
        {
            resultexponents[m] = -1;
        }
        int k = 0; //track the idx of result
        for(int i = 0; i < combined.exponents.length; i++)
        {
            for(int j = 0; j < combined.exponents.length;j++)
            {
                if((combined.exponents[i] == combined.exponents[j]) && (i != j) && (inArray(combined.exponents[i], resultexponents) == false))
                {
                    resultcoefficients[k] = combined.coefficients[i] + combined.coefficients[j];
                    resultexponents[k] = combined.exponents[i];
                    k++;
                }
            }
        }
        for(int n = 0; n <combined.exponents.length;n++)
        {
            if(inArray(combined.exponents[n], resultexponents) == false)
            {
                resultcoefficients[k] = combined.coefficients[n];
                resultexponents[k] = combined.exponents[n];
                k++;
            }
        }
        Polynomial A = new Polynomial(resultcoefficients, resultexponents);
        return move_zeros(A);
    }

    //V
    public double evaluate(double x)
    {
        double result = 0.0;
        for(int i = 0; i < coefficients.length; i ++) 
        {
            result = result + coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    //VI
    public boolean hasRoot(double y)
    {
        double result = evaluate(y);
        if(result == 0)
        {
            return true;
        }
        return false;
    }

    //multiply: one by one and collect the like terms 
    //helper function:find the index of a given exponents
    public int findIndex(int exponent, int[] exponents)
    {
        for(int i = 0; i<exponents.length; i++)
        {
            if(exponent == exponents[i])
                return i;
        }
        return -1; 
    }

    public Polynomial multiply(Polynomial givePolynomial)
    {
        int length1 = exponents.length * givePolynomial.exponents.length;
        double[] before_collect_coefficients = new double[length1];
        int[] before_collect_exponents = new int[length1];
        for(int a = 0; a < before_collect_exponents.length; a++)
        {
            before_collect_exponents[a] = -1;
        }
        int k = 0;
        //multiply one by one
        for(int i = 0; i < exponents.length; i++)
        {
            for(int j = 0; j<givePolynomial.exponents.length; j++)
            {
                int newExponents = exponents[i] + givePolynomial.exponents[j];
                if(inArray(newExponents, before_collect_exponents)==false)
                {
                    before_collect_coefficients[k] =  this.coefficients[i] * givePolynomial.coefficients[j];
                    before_collect_exponents[k] = newExponents;
                    k++;
                }
                else
                {
                    int exsitingidx = findIndex(newExponents, before_collect_exponents);
                    before_collect_coefficients[exsitingidx] += this.coefficients[i] * givePolynomial.coefficients[j];
                }
            }
        }
        //check duplicate
        double[] resultcoefficients = new double[k];
        int[] resultexponents = new int[k];
        for(int m = 0; m < k; m++)
        {
            resultcoefficients[m] = before_collect_coefficients[m];
            resultexponents[m] = before_collect_exponents[m];
        }
        Polynomial result = move_zeros(new Polynomial(resultcoefficients,resultexponents));
        return result;
    }


    public void saveToFile(String fileName) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String polynomialString = "";

        for(int i = 0; i<coefficients.length; i++)
        {
            if(polynomialString.length()!=0)
            {
                if(coefficients[i]>0)
                {
                    polynomialString = polynomialString + "+";
                }
                else
                {
                    polynomialString = polynomialString + "-";
                }
            }
            else if(coefficients[i]<0)
            {
                polynomialString += '-';
            }

            polynomialString = polynomialString + Math.abs(coefficients[i]);

            if(exponents[i]>0)
            {
                polynomialString = polynomialString + "x";
                polynomialString = polynomialString + exponents[i];
            }
        }

        writer.write(polynomialString);
        writer.close();
    }
}

    