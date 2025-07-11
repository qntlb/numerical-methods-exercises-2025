package exercises.session3.confidenceintervals;

import exercises.session2.randomvariables.NormalRandomVariable;
import exercises.session2.randomvariables.RandomVariableInterface;

/**
 * This class computes the lower and the upper bound of the
 * confidence interval at a given level for the mean of a sample of given size
 * of a random variable. Here the Central Limit Theorem is used.
 */
public class CLTMeanConfidenceInterval extends MeanConfidenceInterval {

	// both inherited from ConfidenceIntervalOfMean
	public CLTMeanConfidenceInterval(RandomVariableInterface randomVariable, int sampleSize) {
		this.randomVariable = randomVariable;
		this.sampleSize = sampleSize;
	}

	/**
	 * It computes the lower bound of a confidence interval of a give level, based on the Central Limit Theorem.
	 *
	 * @param level, level of confidence
	 * @return value of the lower bound
	 */
	@Override
	public double getLowerBoundConfidenceInterval(double level) {
		NormalRandomVariable normal = new NormalRandomVariable(0, 1);
		return randomVariable.getAnalyticMean() - randomVariable.getAnalyticStdDeviation() / Math.sqrt(sampleSize)
				* normal.getQuantileFunction((1 + level) / 2);
	}

	/**
	 * It computes the upper bound of a confidence interval of a give level, based on the Central Limit Theorem.
	 *
	 * @param level, level of confidence
	 * @return value of the upper bound
	 */
	@Override
	public double getUpperBoundConfidenceInterval(double level) {
		NormalRandomVariable normal = new NormalRandomVariable(0, 1);
		return randomVariable.getAnalyticMean() + randomVariable.getAnalyticStdDeviation() / Math.sqrt(sampleSize)
				* normal.getQuantileFunction((1 + level) / 2);
	}
}
