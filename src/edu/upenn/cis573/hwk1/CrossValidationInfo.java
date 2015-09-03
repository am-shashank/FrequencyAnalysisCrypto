/**
 * Plain Old Java Object containing information
 * about the correctly and non-correclty decrypted 
 * letters for a document
 */
package edu.upenn.cis573.hwk1;

/**
 * @author Shashank
 *
 */
public class CrossValidationInfo {
	String documentName;
	int numCorrect;
	int numInCorrect;
	
	public CrossValidationInfo(String documentName, int numCorrect, int numIncorrect) {
		this.documentName = documentName;
		this.numCorrect = numCorrect;
		this.numInCorrect = numIncorrect;
	}
}
