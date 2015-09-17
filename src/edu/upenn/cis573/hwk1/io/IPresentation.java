/**
 * 
 */
package edu.upenn.cis573.hwk1.io;

import edu.upenn.cis573.hwk1.CrossValidationInfo;

/**
 * Interface representing the Presentation layer 
 * which presents the results of FrequencyAnalysis
 * @author Shashank
 *
 */
public interface IPresentation {
	/**
	 * Output cross validation results ( eg: on GUI, console)
	 * @param results
	 */
	public void outputResults(CrossValidationInfo [] results); 
}
