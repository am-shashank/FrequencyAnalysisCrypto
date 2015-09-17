/**
 * 
 */
package edu.upenn.cis573.hwk1;


/**
 * Prints the cross validation results on the console
 * @author Shashank
 */
public class PresentaionConsoleOutput implements IPresentation {

	public void outputResults(CrossValidationInfo[] results) {
		int totalCorrect = 0, totalIncorrect = 0;
		for(int i=0; i<results.length; i++) {
			System.out.println(results[i].documentName + " " + results[i].numCorrect + " " + results[i].numInCorrect);
			totalCorrect += results[i].numCorrect;
			totalIncorrect += results[i].numInCorrect;
		}
		float accuracy = totalCorrect * 100 / (float)(totalCorrect + totalIncorrect);
		System.out.println("Total "+totalCorrect+ " "+totalIncorrect);
		System.out.println("Accuracy "+accuracy);
		
	}
}
