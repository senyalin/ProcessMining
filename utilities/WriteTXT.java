package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTXT {
	
	private String fileName = "";
	private File theFile = null;
	private FileWriter ofstream = null;
	private BufferedWriter out;
	
	/*
	 * Initialize the fileHandler
	 */
	public WriteTXT(String fName){
		fileName = fName;
		theFile = new File(fileName);
		// Open the TXT File
		try {
			ofstream = new FileWriter(theFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out = new BufferedWriter(ofstream);
	}
	
	
	public void write(String s){
		
		try {
			out.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
