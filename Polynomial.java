public class Polynomial {

	double[] coefficients;

	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0.0;
	}

	public Polynomial(double[] given_coef) {
		this.coefficients = new double[given_coef.length];
		for (int i = 0; i < given_coef.length; i++)
			this.coefficients[i] = given_coef[i];
	}

	public Polynomial add(Polynomial other) {
		int given_len = this.coefficients.length;
		int other_len = other.coefficients.length;
		int max_length = Math.max(given_len, other_len);
		double[] result = new double[max_length];
		for (int i = 0; i < max_length; i++) {
			if (given_len == max_length && i >= other_len)
				result[i] = this.coefficients[i];
			else if (other_len == max_length && i >= given_len)
				result[i] = other.coefficients[i];
			else
				result[i] = this.coefficients[i] + other.coefficients[i];
		}
		return new Polynomial(result);
	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			sum = sum + this.coefficients[i] * Math.pow(x, i);
		}
		return sum;
	}

	public boolean hasRoot(double x) {
		double sum = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			sum = sum + this.coefficients[i] * Math.pow(x, i);
		}
		return sum == 0;
	}
}
