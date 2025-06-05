package exercises.session1;

public class FloatAndDouble {
	
	/**
	 * Computes the largest epsilon of the form epsilon = 2^(-n) such that epsilon <= |x-x0|.
	 *
	 * @param x  double
	 * @param x0 double
	 * @return an object of the container class EpsilonAndExponent, where we have
	 *         set the values of the biggest epsilon of the form epsilon = 2^(-n)
	 *         such that epsilon <= |x-x0|, and the correspondent int n.
	 */
	public static EpsilonAndExponent computeLargestEpsilon(double x, double x0) {
		double absoluteDifference = Math.abs(x0 - x);// float if at least one of x and x0 is a float
		double epsilon = 1.0;
		int exponent = 0;
		while (absoluteDifference < epsilon) {
			epsilon /= 2.0;
			exponent++;
		}

		EpsilonAndExponent epsilonAndExponent = new EpsilonAndExponent();
		// set the values of the epsilon and exponent field of the class
		epsilonAndExponent.setEpsilon(epsilon);
		epsilonAndExponent.setExponent(exponent);

		return epsilonAndExponent;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Experiment with x0 = 3 * 0.1 (double) and xFloat = 0.3 (float)");
		double x0 = 3 * 0.1; // double
		float xFloat = 0.3f; // float: we have to type f, otherwise it complains
		System.out.println("_".repeat(70));
		System.out.println("The statement xFloat=x0 is " + (xFloat == x0));

		EpsilonAndExponent epsilonAndExponentWithFloat = computeLargestEpsilon(xFloat, x0);

		double epsilonWithFloat = epsilonAndExponentWithFloat.getEpsilon();
		int exponentWithFloat = epsilonAndExponentWithFloat.getExponent();

		System.out.println("n = " + exponentWithFloat + "\t epsilon = " + epsilonWithFloat);

		System.out.println();
		System.out.println("_".repeat(70));
		System.out.println("Experiment with x0 = 3 * 0.1 (double) and xDouble = 0.3 (double)");
		double xDouble = 0.3; // now it's double
		System.out.println("_".repeat(70));
		System.out.println("The statement xDouble=x0 is " + (xDouble == x0));

		EpsilonAndExponent epsilonAndExponentWithDouble = computeLargestEpsilon(xDouble, x0);
		double epsilonWithDouble = epsilonAndExponentWithDouble.getEpsilon();
		int exponentWithDouble = epsilonAndExponentWithDouble.getExponent();

		System.out.println("n = " + exponentWithDouble + "\t epsilon = " + epsilonWithDouble);
	}

}
