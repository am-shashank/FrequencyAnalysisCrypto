/**
 * 
 */
package edu.upenn.cis573.hwk1.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Read the data from the corpus directory
 * and get file handlers for every file 
 * in the directory
 * @author Shashank
 */
public class ReadData implements IReadData {

	public List<File> readToDirectory(String source) {
		File inputDirectory = new File(source);
		List<File> fileList = new ArrayList<File>();
		File[] fileArr = inputDirectory.listFiles();
		for (File f : fileArr) {
			if(f.isFile())
				fileList.add(f);
		}
		return fileList;
	}

	public void readToTextFile(File f) {
		
	}
	
}
