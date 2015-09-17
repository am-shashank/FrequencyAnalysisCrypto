/**
 * 
 */
package edu.upenn.cis573.hwk1.io;

import edu.upenn.cis573.hwk1.CrossValidationInfo;


/**
 * Prints the cross validation results on the console
 * @author Shashank
 */
public class PresentaionConsoleOutput implements IPresentation {

	public void outputResults(CrossValidationInfo[] results) {
		int totalCorrect = 0, totalIncorrect = 0;
		for(int i=0; i<results.length; i++) {
			System.out.println(results[i].getDocumentName() + " " + results[i].getNumCorrect() + " " + results[i].getNumInCorrect());
			totalCorrect += results[i].getNumCorrect();
			totalIncorrect += results[i].getNumInCorrect();
		}
		float accuracy = totalCorrect * 100 / (float)(totalCorrect + totalIncorrect);
		System.out.println("Total "+totalCorrect+ " "+totalIncorrect);
		System.out.println("Accuracy "+accuracy);
		
	}
}
