package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//THIS CLASS IS CREATED SPECIFICALLY FOR LOADING PROCESSES FROM TXT FILES
/*
 */

public class ReadTXT {

	private String fileName = "";
	private File theFile = null;
	private FileReader ifstream = null;
	public BufferedReader in;
	
	/*
	 * Initialize the fileHandler
	 */
	public ReadTXT(String fName){
		fileName = fName;
		theFile = new File(fileName);
		// Open the TXT File 
		try {
			ifstream = new FileReader(theFile);
		} catch (FileNotFoundException e) {
			System.err.print(fName + " is not a file");
			e.printStackTrace();
		}
		in = new BufferedReader(ifstream);
	}

	public void close(){
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
