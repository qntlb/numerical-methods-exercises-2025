package exercises.session3.randomvariables;

import java.text.DecimalFormat;
import java.util.function.DoubleUnaryOperator;



/**
 * In this class we test importance sampling with weighted Monte-Carlo
 * by considering the approximation of P(X > 2.5) where X is a standard normal
 * random variable. Since this is an "extreme" event and the variance is very
 * high, the quality of the approximation with standard sampling might be very
 * poor. We see that by sampling with weighted Monte-Carlo where Y is normal
 * with mean 2.5 the result is much better.
 */
public class ImportanceSamplingTesting {

	private final static DecimalFormat formatterPercentage = new DecimalFormat("0.0000 %");
	private final static DecimalFormat formatterDouble = new DecimalFormat("0.0000");

	public static void main(String[] args) {

		final int numberOfDrawings = 10000;
		/*
		 * We perform 1000 tests in order to get a better idea of the difference between
		 * standard sampling and importance sampling
		 */
		final int numberOfTests = 1000;

		final double barrier = 2.5;// we want compute P(X > barrier)

		final NormalRandomVariable standardNormal = new NormalRandomVariable(0.0, 1.0);
		final NormalRandomVariable shiftedNormal = new NormalRandomVariable(barrier, 1); // the mean is on the barrier!
		final DoubleUnaryOperator indicatorIntegrand = x -> (x > barrier) ? 1.0 : 0.0; // 1_{X > barrier}

		final double analyticResult = 1 - standardNormal.getCumulativeDistributionFunction(barrier);

		// we now compare the two methods

		// number of times when the error of standard sampling is lower
		int numberOfWinsStandardSampling = 0;
		// number of times when the error of importance sampling is lower
		int numberOfWinsImportanceSampling = 0;

		double sumPercentageErrorStandardSampling = 0.0;
		double sumPercentageErrorImportanceSampling = 0.0;

		for (int i = 0; i < numberOfTests; i++) {

			// standard sampling
			final double resultStandardSampling = standardNormal.getSampleMean(numberOfDrawings, indicatorIntegrand);
			// importance sampling(weighted Monte-Carlo)
			final double resultImportanceSampling = standardNormal.getSampleMeanWithWeightedMonteCarlo(numberOfDrawings,
					indicatorIntegrand, shiftedNormal);

			// percentage errors
			final double errorStandardSampling = Math.abs(resultStandardSampling - analyticResult) / analyticResult;
			final double errorImportanceSampling = Math.abs(resultImportanceSampling - analyticResult) / analyticResult;

			// we update the sum
			sumPercentageErrorStandardSampling += errorStandardSampling;
			sumPercentageErrorImportanceSampling += errorImportanceSampling;

			// we check the winner
			if (errorStandardSampling > errorImportanceSampling) {
				numberOfWinsImportanceSampling++;
			} else {
				numberOfWinsStandardSampling++;
			}
		}
		double averagePercentageErrorStandardSampling = sumPercentageErrorStandardSampling / numberOfTests;
		double averagePercentageErrorImportanceSampling = sumPercentageErrorImportanceSampling / numberOfTests;

		// Printing the results

		System.out.println("Analytic percentage probability of a standard normal random variable being more than "
				+ barrier + ": " + formatterPercentage.format(analyticResult));

		System.out.println();

		System.out.println("The average percentage error of standard sampling is "
				+ formatterPercentage.format(averagePercentageErrorStandardSampling));

		System.out.println("The average percentage error of importance sampling is "
				+ formatterPercentage.format(averagePercentageErrorImportanceSampling));

		System.out.println();

		System.out.println("Number of times when standard sampling is better: " + numberOfWinsStandardSampling);
		System.out.println("Number of times when importance sampling is better: " + numberOfWinsImportanceSampling);

		/*
		 * Comment on the difference of the variance: 
		 * Var(h(X)) - Var(h(Y)f(Y)/g(Y)) =
		 * E[h^2(X)]- E[h^2(Y)f^2(Y)/g^2(Y)] (since the expectation of the two is equal)
		 * = \int h^2(x)f(x)dx - \int (h^2(x)f^2(x)/g^2(x))g(x)dx (f and g densities)
		 * =\int h^2(x)f(x)(1-f(x))/g(x))dx, therefore weighted Monte-Carlo leads to a
		 * strong variance reduction if g(x) is significantly bigger than f(x) for the
		 * values of x for which h^2(x) is bigger. This is the case in our example,
		 * since we choose a random variable with distribution centered in the barrier,
		 * so g(x) >> f(x) in the region when h(x)=1
		 */

		// variance of the sample for standard sampling
		System.out.println();
		final double varianceStandardSampling = standardNormal.getSampleStdDeviation(numberOfDrawings,
				indicatorIntegrand);
		System.out.println("Variance for standard sampling: " + formatterDouble.format(varianceStandardSampling));

		final DoubleUnaryOperator weight = x -> (standardNormal.getDensityFunction(x)/ shiftedNormal.getDensityFunction(x));
		final DoubleUnaryOperator whatToSample = (x -> indicatorIntegrand.applyAsDouble(x) * weight.applyAsDouble(x));

		final double varianceImportanceSampling = shiftedNormal.getSampleStdDeviation(numberOfDrawings, whatToSample);

		System.out.println("Variance for importance sampling: " + formatterDouble.format(varianceImportanceSampling));

	}
}
