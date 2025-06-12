package exercises.session2.randomvariables;

import exercises.usefulmethodsmatricesandvectors.UsefulMethodsMatricesAndVectors;

/**
 * This abstract class implements the interface RandomVariableInterface.
 * The methods whose implementation does not directly depend on
 * the specific type of the random variable are implemented here.
 */
public abstract class RandomVariableAbstract implements RandomVariableInterface {

	// it stores independent realizations of the random variable
	private double[] randomVariableRealizations;
	
	
	@Override
	public double generate() {
		/*
		 * Generalized inversion of the distribution function: here we use the fact
		 * that X := F^(-1)(U) with U uniformly distributed in
		 * (0,1) and F^(-1) defined as F^(-1)(y) := inf{x|F(x) >= y} has cumulative
		 * distribution function F.  F^(-1) is here the quantile function of the random
		 * variable. The implementation of getQuantileFunction(double x) will be given
		 * in the classes extending this abstract one, since it depends on the
		 * specific distribution.
		 */
		double generationOfUniformRandomVariable = Math.random();
		return getQuantileFunction(generationOfUniformRandomVariable);
	}

	/*
	 * This method initializes randomVariableRealizations to be a one-dimensional
	 * array of the given length n, and it fills it by calling generate() n times.
	 * It is used to compute the mean and the standard deviation of a sample of
	 * independent realizations of the random variable.
	 */
	private void generateValues(int n) {
		randomVariableRealizations = new double[n];
		for (int i = 0; i < n; i++) {
			randomVariableRealizations[i] = generate();// generation of the new realization
		}
	}


	@Override
	public double getSampleMean(int n) {
		/*
		 * The method might be called more than once, obtaining different results. So
		 * every time the method is called we call generateValues(n), that is supposed
		 * to give different values to the one-dimensional array randomVariableRealizations
		 * every time is called.
		 */
		generateValues(n);
		double mean = UsefulMethodsMatricesAndVectors.getAverage(randomVariableRealizations);
		return mean;
	}

	@Override
	public double getSampleStdDeviation(int n) {
		/*
		 * The method might be called more than once, obtaining different results. So
		 * every time the method is called we call generateValues(n), that is supposed
		 * to give different values to the one-dimensional array randomVariableRealizations
		 * every time is called.
		 */
		generateValues(n);
		double standardDeviation = UsefulMethodsMatricesAndVectors.getStandardDeviation(randomVariableRealizations);
		return standardDeviation;
	}
}
