package edu.upenn.cis573.hwk1;

/**
 * POJO class containing information
 * about the correctly and non-correctly 
 * decrypted letters for a document
 * @author Shashank
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

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the numCorrect
	 */
	public int getNumCorrect() {
		return numCorrect;
	}

	/**
	 * @param numCorrect the numCorrect to set
	 */
	public void setNumCorrect(int numCorrect) {
		this.numCorrect = numCorrect;
	}

	/**
	 * @return the numInCorrect
	 */
	public int getNumInCorrect() {
		return numInCorrect;
	}

	/**
	 * @param numInCorrect the numInCorrect to set
	 */
	public void setNumInCorrect(int numInCorrect) {
		this.numInCorrect = numInCorrect;
	}
}
