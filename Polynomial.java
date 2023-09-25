
public class Polynomial{
    //I
    double[] A;

    //II
    public Polynomial(){
        A = new double[1];
        A[0] = 0.0;
    }

    //III
    public Polynomial(double[] my_array){
        //for loop change the double from my array to A
        A = new double[my_array.length];
        for(int i = 0; i<my_array.length; i++)
        {
            A[i] = my_array[i];
        }
    }

    //IV
    public Polynomial add(Polynomial given_array)
    {   
        int min_len, max_len;
        if(A.length > given_array.A.length)
        {
            min_len = given_array.A.length;
            max_len = A.length;
        }
        else
        {
            min_len = A.length;
            max_len = given_array.A.length;
        }
        int i = 0;
        double[] resultPolynomial = new double[max_len];
        while(i < max_len)
        {
            if(i < min_len)
                resultPolynomial[i] = A[i] + given_array.A[i];
            else
                if(max_len == A.length)
                    resultPolynomial[i] = A[i];
                else
                    resultPolynomial[i] = given_array.A[i];
            i++;
        }
        return new Polynomial(resultPolynomial);
    }

    //V
    public double evaluate(double x)
    {
        double result = 0.0;
        for(int i = 0; i < A.length; i ++)
        {
            result = result + A[i] * Math.pow(x, i);
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
}