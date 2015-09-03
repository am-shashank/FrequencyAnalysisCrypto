/**
 * 
 */
package edu.upenn.cis573.hwk1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Shashank
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		if(args.length == 0){
			System.out.println("[ERROR] Missing argument. Usage: java Main <directoryPath>");
			return;
		} 
			
		File corpusDirectory = new File(args[0]);
		
		// TODO: Resolve the relative path
		if(! corpusDirectory.exists()) {
			System.out.println("[ERROR] "+args[0]+" doesn't exist");
			return;
		}
		if(!corpusDirectory.canRead()) {
			System.out.println("[ERROR] " +args[0]+ " can't be read.");
			return;
		}
		
		File [] fileList = corpusDirectory.listFiles();
		if(fileList.length == 0) {
			System.out.println("[ERROR] "+args[0] +" is empty");
			return;
		}
		
		String [] allData = loadData(fileList);
		CrossValidationInfo [] cvi = new CrossValidationInfo[allData.length];
		
		for(int i=0;i<allData.length; i++) {
			String validationData = allData[i];
			String trainingData = "";
			for (int j=0;j<allData.length; j++) {
				if( i!= j) {
					trainingData += allData[j];					
				}
			}
			SubstitutionCipherCrypto scc  = new SubstitutionCipherCrypto(trainingData, validationData);
			cvi[i] = scc.crossValidate(fileList[i].getName());
		}	
		Presentation ps = new ConsoleOutput();
		ps.outputResults(cvi);
		return;
		
		

	}
	
	public static String [] loadData(File [] fileList) {
		String [] allData = new String[fileList.length];
		for(int i =0;i<fileList.length; i++) {
			try {
				allData[i] = new String(Files.readAllBytes(fileList[i].toPath()));
			} catch (IOException e) {
				System.out.println("[ERROR] Unable to read "+fileList[i]);
			}
		}
		return allData;
	}

}
