package exercises.session2.discrepancy;

import java.util.Arrays;

public class TestDiscrepancy {

	public static void main(String[] args) throws Exception {

		double[] firstSet = { 0.125, 0.25, 0.5, 0.75 };
		System.out.println("The discrepancy of the set " + Arrays.toString(firstSet) + " is "
				+ DiscrepancyOneDimension.getDiscrepancy(firstSet));
		System.out.println("The star discrepancy of the set " + Arrays.toString(firstSet) + " is "
				+ DiscrepancyOneDimension.getStarDiscrepancy(firstSet));

		System.out.println();

		double[] secondSet = { 0.25, 0.5, 5.0 / 8, 0.75 };
		System.out.println("The discrepancy of the set " + Arrays.toString(secondSet) + " is "
				+ DiscrepancyOneDimension.getDiscrepancy(secondSet));
		System.out.println("The star discrepancy of the set " + Arrays.toString(secondSet) + " is "
				+ DiscrepancyOneDimension.getStarDiscrepancy(secondSet));

	}

}