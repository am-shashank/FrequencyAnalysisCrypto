/**
 * 
 */
package edu.upenn.cis573.hwk1;

import java.util.HashMap;

/**
 * @author Shashank
 *
 */
public class SubstitutionCipherCrypto implements Crypto {

	HashMap<Character, Character> subCipher;
	String trainingData;
	String validationData;
	Character [] trainingFreq;
	
	public SubstitutionCipherCrypto(String trainingData, String validationData) {
		this.trainingData = trainingData;
		this.validationData = validationData;
		CharacterFrequencyAnalysis faTrain = new CharacterFrequencyAnalysis(trainingData);
		trainingFreq = faTrain.generateFrequencyModel();
		subCipher = new HashMap<Character, Character>();
	}

	CrossValidationInfo crossValidate(String fileName) {
		int numCorrect = 0, numInCorrect = 0;		
		String cipherText = encrypt(validationData);
		//System.out.println("[DEBUG] Plain text: "+plainText);
		//System.out.println("[DEBUG] Cipher text: "+cipherText);
		String decipheredText = decrypt(cipherText);
		//System.out.println("[DEBUG] Deciphered text: "+decipheredText);
		for(int i=0;i < decipheredText.length(); i++) {
			char ch = validationData.charAt(i);
			if(Character.isAlphabetic(ch)) {
				ch = Character.toLowerCase(ch);
				if(ch == decipheredText.charAt(i))
					numCorrect ++;
				else 
					numInCorrect ++;
			}
		}
		CrossValidationInfo cvi = new CrossValidationInfo(fileName, numCorrect, numInCorrect);
		return cvi;
	}
	
	
	/* (non-Javadoc)
	 * @see edu.upenn.cis573.hwk1.Crypto#encrypt(java.lang.String)
	 */
	public String encrypt(String plainText) {
		HashMap<Character, Character> plainCipherMap = new HashMap<Character, Character>();
		char[] cipherTextArray = new char[plainText.length()];
		// int randInt = new Random().nextInt(100);
		for(int i=0;i<plainText.length(); i++) {
			char p = plainText.charAt(i);
			if(Character.isAlphabetic(p)) {
				p = Character.toLowerCase(p);
				cipherTextArray[i] = (char) (((p + 10) % 26 ) + 'a');
				plainCipherMap.put(p, cipherTextArray[i]);
			}else {
				cipherTextArray[i] = (char) p;
			}
		}
		return new String(cipherTextArray);
	}

	/* (non-Javadoc)
	 * @see edu.upenn.cis573.hwk1.Crypto#decrypt(java.lang.String)
	 */
	public String decrypt(String cipherText) {
		// apply frequency analysis to ciphertext
		CharacterFrequencyAnalysis faValidation = new CharacterFrequencyAnalysis(cipherText);
		Character [] validationFreq = faValidation.generateFrequencyModel();		
		for(int i=0;i<validationFreq.length; i++)
			subCipher.put(validationFreq[i], trainingFreq[i]);
		
		char [] decipheredText = new char[cipherText.length()];
		for(int i=0;i<cipherText.length();i++) {
			char ch = cipherText.charAt(i);
			if(Character.isAlphabetic(ch)) {
				ch = Character.toLowerCase(ch);
				decipheredText[i] = subCipher.get(ch);
			}
			else 
				decipheredText[i] = ch;
		}
		return new String(decipheredText);
	}
	
}
