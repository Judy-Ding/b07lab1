import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;


public class Polynomial {

	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		this.coefficients = new double[1];
		this.exponents = new int[1];
		coefficients[0] = 0.0;
		exponents[0] = 0;
	}

	public Polynomial(double[] given_coef, int[] given_expo) {
		this.coefficients = new double[given_coef.length];
		this.exponents = new int[given_expo.length];
		for (int i = 0; i < given_coef.length; i++) {
			this.coefficients[i] = given_coef[i];
			this.exponents[i] = given_expo[i];
		}
			
	}
	
	public Polynomial(File file) throws IOException{
		Scanner input = new Scanner(file);
		String polynomialStrings = input.nextLine();
		String[] terms = polynomialStrings.split("(?=[+-])");
		coefficients =  new double[terms.length];
		exponents = new int[terms.length];
		for(int i = 0; i<terms.length;i++) {
			if(terms[i].contains("x")) {
				 String[] parts = terms[i].split("x");
				 if(parts[0].equals("") | parts[0].equals("+"))
					 coefficients[i] = 1;
				 else if(parts[0].equals("-"))
				 	coefficients[i]=-1;
				 else
					 coefficients[i] = Double.parseDouble(parts[0]);
				 if(parts.length==1)
					 exponents[i] = 1;
				 else
					 exponents[i] = Integer.parseInt(parts[1]);
			}
			else {//if it is a constant value
				coefficients[i] = Double.parseDouble(terms[i]);
				exponents[i] = 0;
			}	
		}
		input.close();
	}
	
	public Polynomial add(Polynomial other) {
	    int given_len = this.coefficients.length;
	    int other_len = other.coefficients.length;
	    double[] result_coef = new double[given_len + other_len];
	    int[] result_expo = new int[given_len + other_len];
	    int idx = 0; 
	    
	    for (int i = 0; i < given_len; i++) {
	        result_coef[idx] = this.coefficients[i];
	        result_expo[idx] = this.exponents[i];
	        idx++;
	    }
	    
	    for (int j = 0; j < other_len; j++) {
	        boolean found = false; 
	        for (int i = 0; i < idx; i++) {
	            if (result_expo[i] == other.exponents[j]) {
	                result_coef[i] += other.coefficients[j]; 
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            result_coef[idx] = other.coefficients[j];
	            result_expo[idx] = other.exponents[j];
	            idx++;
	        }
	    }
	    double[] final_coef = new double[idx];
	    int[] final_expo = new int[idx];
	    int newIdx = 0;
	    
	    for (int i = 0; i < idx; i++) {
	        if (result_coef[i] != 0) {
	            final_coef[newIdx] = result_coef[i];
	            final_expo[newIdx] = result_expo[i];
	            newIdx++;
	        }
	    }
	    return new Polynomial(final_coef, final_expo);
	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			sum = sum + this.coefficients[i] * Math.pow(x, this.exponents[i]);
		}
		return sum;
	}

	public boolean hasRoot(double x) {
		 return this.evaluate(x)==0;
	}
	
	public Polynomial multiply(Polynomial other) {
		Polynomial result = new Polynomial();
		for(int i = 0; i<this.coefficients.length;i++)
		{
			double[] coef = new double[other.coefficients.length];
			int[] expo = new int[other.coefficients.length];
			for(int j = 0; j< other.coefficients.length;j++) {
				coef[j] = this.coefficients[i] * other.coefficients[j];
				expo[j] = this.exponents[i] + other.exponents[j];
			}
			Polynomial p = new Polynomial(coef,expo);
			result = result.add(p);
		}
		return result;
	}
	
	
	public void saveToFile(String name) throws IOException{
		FileWriter writer = new FileWriter(name);
		for (int i = 0; i < coefficients.length; i++) {
			if (i > 0 && coefficients[i] > 0) {
                writer.write("+"); 
            }
            writer.write(String.valueOf(coefficients[i])); 
            
            if (exponents[i] != 0) {  
                writer.write("x"); 
                if (exponents[i] != 1) {
                    writer.write(String.valueOf(exponents[i])); 
                }
            }
		}
		writer.close();
	}
	
}
