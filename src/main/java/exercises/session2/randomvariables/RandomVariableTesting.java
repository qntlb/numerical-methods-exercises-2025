package exercises.session2.randomvariables;

/**
 * This class tests some methods of the class
 * ExponentialRandomVariable, which (indirectly) implement RandomVariableInterface.
 */
public class RandomVariableTesting {

	public static void main(String[] args) {
		
		double lambda = 0.2;
		int numberOfSimulations = 1000000;
		ExponentialRandomVariable exponentialSampler = new ExponentialRandomVariable(lambda);

		System.out.println("Exponential random variable: comparing Empirical mean and std dev with mu and sigma");

		System.out.println();

		System.out.println("Sample mean: " + exponentialSampler.getSampleMean(numberOfSimulations));
		System.out.println("Analytic mean: " + exponentialSampler.getAnalyticMean());

		System.out.println();

		System.out.println("Sample std dev: " + exponentialSampler.getSampleStdDeviation(numberOfSimulations));
		System.out.println("Analytic std dev: " + exponentialSampler.getAnalyticStdDeviation());

	}
}