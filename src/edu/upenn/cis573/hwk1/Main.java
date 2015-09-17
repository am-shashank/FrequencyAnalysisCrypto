/**
 * 
 */
package edu.upenn.cis573.hwk1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Shashank
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String [] args) {
		
		if(args.length != 1) {
			System.out.println("[ERROR] Incorrect arguments. Usage: java Main <directoryPath>");
			return;
		} 
		
		
		File corpusDirectory = new File(args[0]);
		
		String absPath = "";
		try {
			absPath= corpusDirectory.getCanonicalPath();
		} catch (IOException e) {
			System.out.println("[ERROR] Please check the path again.");
			return;
		}
		if(! corpusDirectory.exists()) {
			System.out.println("[ERROR] "+absPath+" doesn't exist");
			return;
		}
		if(!corpusDirectory.canRead()) {
			System.out.println("[ERROR] " +absPath+ " can't be read.");
			return;
		}
		
		IReadData ird = new ReadData();
		List<File> fileList = ird.readToDirectory(absPath);
		if(fileList.isEmpty()) {
			System.out.println("[ERROR] "+absPath +" is empty");
			return;
		}
		
		// read all files into string
		String [] allData = loadData(fileList);
		CrossValidationInfo [] cvi = new CrossValidationInfo[allData.length];
		
		// iterate over the files to build different training and test sets
		for(int i=0;i<allData.length; i++) {
			String testData = allData[i];
			StringBuilder trainingData = new StringBuilder();
			for (int j=0;j<allData.length; j++) {
				if( i!= j) {
					trainingData.append(allData[j]);					
				}
			}
			SubstitutionCipherCrypto scc  = new SubstitutionCipherCrypto(trainingData.toString(), testData);
			cvi[i] = scc.crossValidate(fileList.get(i).getName());
		}	
		IPresentation ps = new PresentaionConsoleOutput();
		ps.outputResults(cvi);
		return;
		
		

	}
	
	/**
	 * Read file content from all the files in the file list
	 * @param fileList
	 * @return
	 */
	public static String [] loadData(List<File> fileList) {
		String [] allData = new String[fileList.size()];
		for(int i =0; i<fileList.size(); i++) {
			try {
				allData[i] = new String(Files.readAllBytes(fileList.get(i).toPath()));
			} catch (IOException e) {
				System.out.println("[ERROR] Reading "+fileList.get(i));
			}
		}
		return allData;
	}

}
