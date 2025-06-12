package exercises.session2.discrepancy;

import java.util.Arrays;

/**
 * This class implements the computation of the discrepancy and of the star
 * discrepancy of a set of points in one dimension. The points of the set are
 * given as a one-dimensional array, which is not supposed to be already sorted
 * when passed to the argument list of the methods.
 */
public class DiscrepancyOneDimension {

	/**
	 * Computes the discrepancy of a set of points in one dimension.
	 * In particular, the discrepancy of the one-dimensional set is computed as
	 * max_{a \in set} (max_{b \in set} max(|{x_i \in [a,b]}|/n - (b-a), (b-a)-|{x_i in (a,b)}|/n)),
	 * where n is the length of the array, and x_i are the points in the array.
	 * So we can check intervals [set[position],b], where
	 * position runs from 0 (first element of the set) to totalNumberOfPoints - 1
	 * (second last) and b is bigger than set[position]
	 *
	 * @param set, a one-dimensional array giving the points of the set whose
	 *             discrepancy must be computed. It is not supposed to be already
	 *             sorted.
	 */
	public static double getDiscrepancy(double[] set) {
		Arrays.sort(set); // Java method to sort the set
		int totalNumberOfPoints = set.length;
		/*
		 * We first get the star discrepancy, i.e., we check intervals [0,b], where b
		 * varies in the set of points.
		 */
		double discrepancy = getStarDiscrepancy(set);
		/*
		 * We now check open and closed intervals from a=set[position] to b, where
		 * position runs from 0 (first element of the set) to totalNumberOfPoints - 2
		 * (we don't need to consider the last element in the set, why?)
		 * and b is bigger than set[position]
		 */
		for (int position = 0; position < totalNumberOfPoints - 1; position++) {
			/*
			 * Maximum value of the absolute value that appears in the definition of
			 * discrepancy, given by intervals whose left end is set[position]
			 */
			double newCandidate = getMaximumValue(set, position);
			
			//If this new value is higher than the current maximum, we update the current maximum
			discrepancy = Math.max(discrepancy, newCandidate);
		}
		return discrepancy;
	}

	/*
	 * Returns
	 * max_{b \in set}max(|{x_i in (set[pos],b]}|/n-(b-set[pos]), (b-set[pos])-|{x_i \in (set[pos],b)}|/n),
	 * where set is the (now sorted!) one-dimensional array of the points of the set whose discrepancy must be
	 * computed, n is the length of set, and x_i are points of the set.
	 */
	private static double getMaximumValue(double[] set, int position) {
		int totalNumberOfPoints = set.length;
		/*
		 * They will get incremented as b runs in the set. This is
		 * why having a sorted set in convenient.
		 */
		double numberOfPointsInTheOpenIntervals = 0;
		double numberOfPointsInTheClosedIntervals = 2;
		double maxValue = 0;
		// first we check sets not involving b=1 (unless x_n=1)
		for (int index = 1; index <= totalNumberOfPoints - position - 1; index++) {
			double lengthOfNewInterval = set[position + index] - set[position];
			double newCandidate = Math.max(lengthOfNewInterval - numberOfPointsInTheOpenIntervals / totalNumberOfPoints,
					numberOfPointsInTheClosedIntervals / totalNumberOfPoints - lengthOfNewInterval);
		
			maxValue = Math.max(maxValue, newCandidate); // Updating the maximum
			numberOfPointsInTheOpenIntervals++;
			numberOfPointsInTheClosedIntervals++;
		}
		// now we check the set involving b=1 (if x_n != 1, otherwise we have already checked it)
		if (set[totalNumberOfPoints - 1] != 1) {
			double lengthOfNewInterval = 1 - set[position];
			/*
			 * this is the only one that can increase the discrepancy: for closed sets, you
			 * have same number of points with bigger length of the interval
			 */
			double newCandidate = lengthOfNewInterval - numberOfPointsInTheOpenIntervals / totalNumberOfPoints;
			maxValue = Math.max(maxValue, newCandidate);
		}
		return maxValue;
	}

	/**
	 * Computes the star discrepancy of a set of points in one
	 * dimension. The star discrepancy of the set of points set is computed as
	 * max_{b \in set} max (b - |{x_i \in (0,b)}|/n), |{x_i \in [0,b]}|/n - b)
	 * = max_{0 \le i \le n} max (x_i - i/n, (i+1)/n - x_i),
	 * where n is the length of the array, and x_i are the points in the array.
	 *
	 * @param set, a one-dimensional array giving the points of the set whose
	 *             discrepancy must be computed. It is not supposed to be already
	 *             sorted.
	 */
	public static double getStarDiscrepancy(double[] set) {

		Arrays.sort(set); // Sort the list of sample points

		double starDiscrepancy = 0.0;

		int count = 0;															// Initializing the counter of the points
		for(double point : set) {												// Looping over the points in set

			double low = point - (double)count/set.length;						// Evaluating lambda([0,x)) - i/n = x - i/n
			count++;
			double high = (double)count/set.length - point;						// Evaluating (i+i)/n - lambda([0,x]) = (i + 1)/n - x

			starDiscrepancy = Math.max(starDiscrepancy, Math.max(low, high));	// Taking the maximum over all these values.
		}

		return starDiscrepancy;
	}
}
