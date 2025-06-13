package exercises.session3.confidenceintervals;

import java.text.DecimalFormat;

import exercises.session2.randomvariables.ExponentialRandomVariable;
import exercises.session2.randomvariables.RandomVariableInterface;

/**
 * This class provides some experiment for the calculation of confidence
 * intervals based on the Central Limit Theorem and on the Chebychev inequality
 */
public class ConfidenceIntervalsTesting {

	static DecimalFormat formatterValue = new DecimalFormat("#0.00000");

	static DecimalFormat formatterPercentage = new DecimalFormat("#0.00%");

	public static void main(String[] args) {
		double lambda = 0.2;
		int numberOfMeanComputations = 100; // 10000;
		int sampleSize = 100000;
		double confidenceLevel = 0.9;
		/*
		 * exponentially distributed random variables: we want to compute the confidence
		 * intervals for the sample mean of size given by sampleSize.
		 */
		RandomVariableInterface exponential = new ExponentialRandomVariable(lambda);
		// with Chebychev inequality
		ChebychevMeanConfidenceInterval chebychevIntervalCalculator = new ChebychevMeanConfidenceInterval(exponential,
				sampleSize);
		// and with the Central Limit Theorem
		CLTMeanConfidenceInterval cLTIntervalCalculator = new CLTMeanConfidenceInterval(exponential, sampleSize);

		System.out.println("_".repeat(100));
		System.out.println("Confidence level \tMethod    \tSamples \tLower bound \tUpper bound \tFrequency");
		System.out.println(confidenceLevel + " ".repeat(15) + "\tChebyshev\t" + sampleSize  + " ".repeat(5) + "\t"
				+ formatterValue.format(chebychevIntervalCalculator.getLowerBoundConfidenceInterval(confidenceLevel)) + " ".repeat(5) + "\t"
				+ formatterValue.format(chebychevIntervalCalculator.getUpperBoundConfidenceInterval(confidenceLevel))  + " ".repeat(5) + "\t"
				+ formatterPercentage.format(chebychevIntervalCalculator.frequenceOfInterval(numberOfMeanComputations, confidenceLevel))
				);
		System.out.println(confidenceLevel + " ".repeat(15) + "\tCLT" + " ".repeat(10) + "\t" + sampleSize + " ".repeat(5) + "\t"
				+ formatterValue.format(cLTIntervalCalculator.getLowerBoundConfidenceInterval(confidenceLevel))  + " ".repeat(5) + "\t"
				+ formatterValue.format(cLTIntervalCalculator.getUpperBoundConfidenceInterval(confidenceLevel))  + " ".repeat(5) + "\t"
				+ formatterPercentage.format(cLTIntervalCalculator.frequenceOfInterval(numberOfMeanComputations, confidenceLevel))
				);
	}
}