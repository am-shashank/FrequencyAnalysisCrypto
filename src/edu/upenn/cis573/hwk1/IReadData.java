/**
 * 
 */
package edu.upenn.cis573.hwk1;

import java.io.File;
import java.util.List;

/**
 * Interface representing the data layer
 * which abstracts the reading of the input
 * @author Shashank
 *
 */
public interface IReadData {
	
	/**
	 * read the data from the source and return a list of file descriptors
	 * @param source (eg : directory, database, web services)
	 * @return
	 */
	public List<File> readToDirectory(String source);
	
	/**
	 * Read the data from any file format
	 * to a text file
	 * @param f
	 */
	public void readToTextFile(File f);
}
