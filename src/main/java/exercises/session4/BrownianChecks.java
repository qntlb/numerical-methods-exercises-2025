package exercises.session4;

import java.text.DecimalFormat;


/**
 * This class tests some features of Brownian motion through Monte Carlo simulation
 */
public class BrownianChecks {

	public static void main(String[] args) {
		final DecimalFormat formatterValue = new DecimalFormat(" ##0.00000;" + "-##0.00000");
		final DecimalFormat formatterTime = new DecimalFormat(" ##0.00;");

		final int numberOfPaths = 100000;

		// time discretization parameters
		final int numberOfTimeSteps = 100;
		final double timeHorizon = 1.0;
		final double timeStepLength = timeHorizon / numberOfTimeSteps;

		// we first construct the Brownian motion object
		final BrownianMotion brownianMotion = new BrownianMotion(timeStepLength, numberOfTimeSteps, numberOfPaths);

		// we plot a path
//		brownianMotion.plotSpecificPath(99);

		/*
		 * And now we want to compute, for some specific times t_i, average and variance.
		 * Of course we expect the average to be close to zero and the variance close to t_i.
		 * We will give a value to the following doubles for every t_i.
		 */
		double averagesOfTheBrownianMotion;

		double standardDeviationsOfTheBrownianMotion;

		double variancesOfTheBrownianMotion;

		System.out.println(" Time \t \t E(W_t) \t MC_E(W_T) \t Var(W_t) \t MC_Var(W_t)");
		System.out.println("_".repeat(78));
		for (int timeIndex = 0; timeIndex < numberOfTimeSteps + 1; timeIndex += 10) {

			RandomVariableFromArray currentRealizations = brownianMotion.getProcessAtGivenTimeIndex(timeIndex);
			
			// Here you see that we exploit a method of BrownianMotion and then one of RandomVariableFromArray.
			averagesOfTheBrownianMotion = currentRealizations.getAverage();
			standardDeviationsOfTheBrownianMotion = currentRealizations.getStandardDeviation();

			// we want to print the variance, since we know it should be close to t_i
			variancesOfTheBrownianMotion = standardDeviationsOfTheBrownianMotion * standardDeviationsOfTheBrownianMotion;

			// we know t_i = timeStepLength * i.
			System.out.print(formatterValue.format(timeIndex * timeStepLength)); // time
			System.out.print("\t"+ formatterValue.format(0.)); // expected value analytic
			System.out.print("\t"+ formatterValue.format(averagesOfTheBrownianMotion)); // expected value Monte Carlo
			System.out.print("\t"+ formatterValue.format(timeIndex * timeStepLength)); // variance analytic
			System.out.print("\t"+ formatterValue.format(variancesOfTheBrownianMotion)); // variance Monte Carlo
			System.out.println();
		}

		System.out.println();
		System.out.println("_".repeat(78));
		System.out.println();

		// we now compute the cross correlation E[B_{t_j}B_{t_i}], for t_i != t_j

		/*
		 * Suppose t>s. Then it holds 
		 * E[B_tB_s] = E[(B_t-B_s)B_s+B_s^2] = E[B_t - B_s]E[B_s] + E[B_s^2] = s, 
		 * where the second equality comes from the independence of the increments of Brownian motion.
		 */

		final double firstTime = 0.7;
		final double secondTime = 0.5;

		
		 // Here we compute the cross correlation at certain times
		final double crossCorrelationTest = brownianMotion.getProcessAtGivenTime(firstTime)
				.mult(brownianMotion.getProcessAtGivenTime(secondTime)).getAverage();

		System.out.println("E(W_t W_s) for t=" + formatterTime.format(firstTime)
				+ " and s=" + formatterTime.format(secondTime) + ": "
				+ formatterValue.format(crossCorrelationTest));
	}
}