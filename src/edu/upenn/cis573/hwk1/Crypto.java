/**
 * 
 */
package edu.upenn.cis573.hwk1;

/**
 * @author Shashank
 *
 */
public interface Crypto {
	public String encrypt(String plainText);
	public String decrypt(String cipherText);
}
