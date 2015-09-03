/**
 * 
 */
package edu.upenn.cis573.hwk1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Shashank
 * 
 */
public class CharacterFrequencyAnalysis {

	HashMap<Character, Integer> charCounter = new HashMap<Character, Integer>();
	String str;
	
	public CharacterFrequencyAnalysis(String str) {
		this.str = str;
		updateCounter();
	}
	/**
	 * Update the character frequency by adding 
	 * character counts from the characters in each file
	 * @param filename
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	void updateCounter() {
		for(int i=0;i < str.length(); i++) {
			char ch = str.charAt(i);
			int cnt = 0;
			if(Character.isAlphabetic(ch)) {
				ch = Character.toLowerCase(ch);
				if(charCounter.containsKey(ch))
					cnt = charCounter.get(ch);
				charCounter.put(ch, cnt + 1);
			}
		}
	}
	
	/**
	 * 
	 */
	public Character[] generateFrequencyModel() {
		
		// sort the unique values and store in an ArrayList
		Character [] sortedChars = new Character[charCounter.size()];
		HashSet<Integer> uniqueCounts = new HashSet<Integer>();
		for(Integer m : charCounter.values()) {
			uniqueCounts.add(m);
		}
		int ctr = 0;
		ArrayList<Integer> sortedCounts = new ArrayList<Integer>(uniqueCounts);
		Collections.sort(sortedCounts, Collections.reverseOrder());
		// Do a reverse lookup in hashmap
		for(Integer cnt: sortedCounts) {
			for(Character key : charCounter.keySet()) {
				if(charCounter.get(key).intValue() == cnt.intValue()) {
					sortedChars[ctr] = key;
					ctr += 1;
				}
			}
		}
		return sortedChars;
	}
			
}
