/**
 * 
 */
package edu.upenn.cis573.hwk1;

import java.util.HashMap;

/**
 * Encrypt using Substitution cipher and 
 * decrypt using Frequency Analysis
 * @author Shashank
 */
public class SubstitutionCipherCrypto implements ICrypto {

	HashMap<Character, Character> subCipher;
	String trainingData;
	String testData;
	Character [] trainingFreq;
	
	/**
	 * 
	 * @param trainingData
	 * @param testData
	 */
	public SubstitutionCipherCrypto(String trainingData, String testData) {
		this.trainingData = trainingData;
		this.testData = testData;
		CharacterFrequencyAnalysis faTrain = new CharacterFrequencyAnalysis(trainingData);
		trainingFreq = faTrain.generateFrequencyModel();
		subCipher = new HashMap<Character, Character>();
	}

	CrossValidationInfo crossValidate(String fileName) {
		int numCorrect = 0, numInCorrect = 0;		
		String cipherText = encrypt(testData);
		//System.out.println("[DEBUG] Plain text: "+plainText);
		//System.out.println("[DEBUG] Cipher text: "+cipherText);
		String decipheredText = decrypt(cipherText);
		//System.out.println("[DEBUG] Deciphered text: "+decipheredText);
		for(int i=0;i < decipheredText.length(); i++) {
			char ch = testData.charAt(i);
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
	
	
	/**
	 * Encrypt the string using substitution cipher
	 */
	public String encrypt(String plainText) {
		HashMap<Character, Character> plainCipherMap = new HashMap<Character, Character>();
		char[] cipherTextArray = new char[plainText.length()];
		for(int i=0;i<plainText.length(); i++) {
			char p = plainText.charAt(i);
			// Encrypt only ASCII alphabets
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

	/**
	 * Decrypt the cipher text using Character Frequency Analysis
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
