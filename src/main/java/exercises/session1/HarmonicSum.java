package exercises.session1;

/**
 * This class deals with forward and backward harmonic sums: for large numbers,
 * the two diverge if single precision is used.
 * The point is that most of the significance of a small float number is lost
 * when it is added to a large number: when the ratio of the larger number to
 * the smaller number becomes sufficiently large, the value of the sum will be
 * identical to the value of the larger number.
 * This issue happens when the forward computation is performed.
 */
public class HarmonicSum {
	
	/**
	 * Computes the forward harmonic sum with double precision
	 *
	 * @param n order of the sum
	 * @return the value of the sum, double
	 */
	static double doubleHarmonicSumForward(int n) {
		double sum = 0; // double
		for (int i = 1; i <= n; i++) {
			sum += (1 / (double) i);
		}
		return sum;
	}

	/**
	 * Computes the backward harmonic sum with double precision
	 *
	 * @param n order of the sum
	 * @return the value of the sum, double
	 */
	static double doubleHarmonicSumBackward(int n, boolean isFloat) {
		double sum = 0; // double
		
		for (int i = n; i > 0; i--) {
			sum += (1.0 / i);
		}
		return sum;
	}

	/**
	 * Computes the forward harmonic sum with single precision
	 *
	 * @param n order of the sum
	 * @return the value of the sum, float
	 */
	static float floatHarmonicSumForward(int n) {
		float sum = 0; // float
		for (int i = 1; i < n + 1; i++) {
			sum += (1.0 / i);
		}
		return sum;
	}

	/**
	 * Computes the backward harmonic sum with single precision
	 *
	 * @param n order of the sum
	 * @return the value of the sum, float
	 */
	static float floatHarmonicSumBackward(int n) {
		float sum = 0; // float
		for (int i = n; i > 0; i--) {
			sum += (1.0 / i);
		}
		return sum;
	}

	public static void main(String[] args) {
		
		int harmonicSumOrder = 10000000;

		System.out.println("Type sum \t order \t precision \t value");
		System.out.println("_".repeat(79));
		System.out.println("forward \t" + harmonicSumOrder + "\t single \t" + floatHarmonicSumForward(harmonicSumOrder));
		
		System.out.println("backward \t" + harmonicSumOrder + "\t single \t" + floatHarmonicSumBackward(harmonicSumOrder));
		
		System.out.println("forward \t" + harmonicSumOrder + "\t double \t" + doubleHarmonicSumForward(harmonicSumOrder));
		
		System.out.println("backward \t" + harmonicSumOrder + "\t double \t" + doubleHarmonicSumBackward(harmonicSumOrder, true));

	}

}
