package exercises.session3.confidenceintervals;

import exercises.session2.randomvariables.RandomVariableInterface;

/**
 * This abstract class provides methods for the computation of upper and
 * lower bounds of confidence intervals for the sample mean of given size of a
 * given random variable. The size of the sample and the random variable are
 * fields of the class. It also has a method frequenceOfInterval which returns
 * the frequency at which the effective mean of the sample falls in the
 * confidence interval for a given confidence level.
 */
public abstract class MeanConfidenceInterval {
	
	protected RandomVariableInterface randomVariable;// it will be inherited and initialized
	protected int sampleSize;// it will be inherited and initialized

	
	//Two abstract methods. The confidence interval is specific of the limit theorem used to compute it.
	/**
	 * Computes the lower bound of the confidence interval of level
	 * confidenceLevel for the mean of a sample of random variables whose size is
	 * specified by the field sampleSize.
	 *
	 * @param confidenceLevel, the confidence level of the interval: we want that
	 *                         P(mean in interval) >= confidenceLevel
	 * @return the lower bound of the confidence interval
	 */
	public abstract double getLowerBoundConfidenceInterval(double confidenceLevel);

	/**
	 * It computes the upper bound of the confidence interval of level
	 * confidenceLevel for the mean of a sample of random variables whose size is
	 * specified by the field sampleSize.
	 *
	 * @param confidenceLevel, the confidence level of the interval: we want that
	 *                         P(mean in interval) >= confidenceLevel
	 * @return the lower bound of the confidence interval
	 */
	public abstract double getUpperBoundConfidenceInterval(double confidenceLevel);

	/**
	 * Computes the frequency with which the mean of the sample falls inside the
	 * confidence interval computed by the methods getLowerBoundConfidenceInterval and
	 * getUpperBoundConfidenceInterval for a given confidence level.
	 *
	 * @param numberOfMeanComputations, the number of the computations of the sample mean
	 * @param confidenceLevel,          the level of the confidence interval
	 * @return the frequency: the number of mean samples within the interval divided
	 *         by the number of mean computations
	 */
	public double frequencyOfInterval(int numberOfMeanComputations, double confidenceLevel) {
		double numberOfTimesInsideTheInterval = 0;
		// computed with CLT or Chebyshev depending on the object calling
		double lowerBound = getLowerBoundConfidenceInterval(confidenceLevel);
		// computed with CLT or Chebyshev depending on the object calling
		double upperBound = getUpperBoundConfidenceInterval(confidenceLevel);

		double sampleMean;
		for (int i = 0; i < numberOfMeanComputations; i++) {
			sampleMean = randomVariable.getSampleMean(sampleSize); // sample mean
			if (sampleMean > lowerBound && sampleMean < upperBound) {
				numberOfTimesInsideTheInterval++; // sample mean within the confidence interval
			}
		}
		return numberOfTimesInsideTheInterval / numberOfMeanComputations;
	}
}