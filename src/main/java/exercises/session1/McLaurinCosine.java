package exercises.session1;

/**
 * This class implements the Maclaurin series expansion for the cosine in a point x in
 * the domain [0, pi).
 * When computing the expansion, an overflow can be caused by the
 * factorial of i, if i is an integer. In this case, the factorial may give negative
 * numbers because of the overflow, and this can lead to bugs. The situation gets better
 * defining factorial as a long, because in this case the overflow happens for higher
 * orders. The problem gets "almost" solved defining factorial as a double: in this case,
 * not only it takes much more to get an overflow, but the overflow gives now Infinity,
 * which results in a zero term when we divide the power of x by the factorial.
 * Still, we might have a problem: which one?
 */
public class McLaurinCosine {

	/**
	 * Computes the Maclaurin series of the cosine in a point x in the domain [0,pi).
	 *
	 * @param x, point in [0,pi).
	 * @param n, the order of the approximation
	 * @return the value of the approximation
	 */
	public static double macLaurinCosineInZeroPi(double argument, int order) {

		double macLaurinApproximation = 0.0; // initialization of the sum

		int factorial = 1;// you can get negative numbers due to an overflow for large order. Play with different types.
		double powerOfX = 1;
		int sign = 1;
		
		for (int i = 0; i < (order + 1); i++) {

			// Maclaurin formula
			macLaurinApproximation += sign * powerOfX / factorial;
			
			factorial *= (2 * i + 1) * (2 * i + 2);// factorial*(2(k-1)+1)*(2 * (k-1) + 2)=factorial*(2k-1)*(2*k)

			powerOfX *= argument * argument;
			
			sign *= -1;
		}
		return macLaurinApproximation;
	}

	
	/**
	 * Computes the Maclaurin series of the cosine in a point x.
	 *
	 * @param angle, the point where we want to compute the approximation
	 * @param order, the order of the approximation
	 * @return the value of the approximation
	 */
	public static double macLaurinCosineSeries(double angle, int order) {

		double reduction = angle % (2 * Math.PI);// the reminder of the ratio
		if (reduction < Math.PI) { // we directly return the value of the series
			return macLaurinCosineInZeroPi(reduction, order);
		}
		return -macLaurinCosineInZeroPi(reduction - Math.PI, order);// property of the cosine
	}

	
	public static void main(String[] args) {

		double angle = 3;
		int maxOrder = 15;
		
		double analyticValue = Math.cos(angle);
		System.out.println("Testing the Maclaurin expansion for cos(x)");
		System.out.println("_".repeat(70));
		System.out.println("\t x \t Order \t Absolute Error");
		System.out.println("_".repeat(70));
		for (int order = 1; order <= maxOrder; order++) {
			double mcLaurinValue = macLaurinCosineSeries(angle, order);
			double error = Math.abs(mcLaurinValue - analyticValue);
			System.out.println("\t" + angle + "\t" + order + "\t" + error);
			
		}

	}
	
}
