/**
 * 
 */
package edu.upenn.cis573.hwk1;

/**
 * Interface which handles encryption and decryption
 * Any encryption scheme like substitution cipher, 
 * symmetric or assyemtric cryptography can be used
 * and the functionality can be made abstract through 
 * this interface
 * @author Shashank
 *
 */
public interface ICrypto {
	/**
	 * Encryption algorithm
	 * @param plainText
	 * @return cipherText
	 */
	public String encrypt(String plainText);
	
	/**
	 * Decryption algorithm
	 * @param cipherText
	 * @return
	 */
	public String decrypt(String cipherText);
}
