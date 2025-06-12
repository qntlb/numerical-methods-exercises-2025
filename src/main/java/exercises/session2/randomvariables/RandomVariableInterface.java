package exercises.session2.randomvariables;

/**
 * This interface defines methods for a class representing a
 * random variable with a given distribution.
 */
public interface RandomVariableInterface {

	/**
	 * Generates a realization of a random variable with given distribution. The
	 * random variable is represented by the object calling the method.
	 *
	 * @return a realization of the random variable
	 */
	double generate();

	/**
	 * Returns the analytic expectation of a random variable with a given
	 * distribution. The random variable is represented by the object calling the
	 * method
	 *
	 * @return the analytic expectation of the random variable
	 */
	double getAnalyticMean();

	/**
	 * Returns the analytic standard deviation of a random variable with a given
	 * distribution. The random variable is represented by the object calling the
	 * method.
	 *
	 * @return the analytic standard deviation of the random variable
	 */
	double getAnalyticStdDeviation();

	/**
	 * Returns the average of a one-dimensional array of specified length n of
	 * independent realizations of a random variable with given distribution. The
	 * random variable is represented by the object calling the method. Note: the
	 * result can differ from the value returned by getAnalyticMean called by the
	 * same object, but it should converge to it for increasing n.
	 *
	 * @param n, the length of the sample
	 * @return the mean of the sample of realizations of the random variable
	 *
	 */
	double getSampleMean(int n);

	/**
	 * Returns the standard deviation of a one-dimensional array of specified
	 * length n of independent realizations of a random variable with given
	 * distribution. The random variable is represented by the object calling the
	 * method. Note: the result can differ from the value returned by
	 * getAnalyticStdDeviation called by the same object, but it should converge to
	 * it for increasing n.
	 *
	 * @param n, the length of the sample
	 * @return the standard deviation of the sample of realizations of the random
	 *         variable
	 */
	double getSampleStdDeviation(int n);

	/**
	 * Returns the density function of the random variable calling the method,
	 * evaluated at x.
	 *
	 * @param x, the point where the density function is evaluated
	 * @return the density function evaluated at x
	 */
	double getDensityFunction(double x);
	
	/**
	 * Returns the cumulative distribution function of the random variable
	 * calling the method, evaluated at x. Note: depending on the distribution of
	 * the random variable, this might be approximated.
	 *
	 * @param x, the point where the cumulative distribution function is evaluated
	 * @return the cumulative distribution function evaluated at x
	 */
	double getCumulativeDistributionFunction(double x);// P(X <= x)

	/**
	 * Returns the quantile function of the random variable calling the method,
	 * evaluated at x. Note: depending on the distribution of the random variable,
	 * this might be approximated.
	 *
	 * @param x, the point where the quantile function is evaluated
	 * @return the quantile function evaluated at x
	 */
	double getQuantileFunction(double x); // inf{y|cdf(y)>=x}
	
}