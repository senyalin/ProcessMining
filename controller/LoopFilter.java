package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import events.Task;
import utilities.ReadTXT;
import utilities.WriteTXT;
//import utilities.MyList;

/**
 * Returns the simplified logs after collapsing the loop patterns
 * 
 * @return new logs specified in txt
 */
public class LoopFilter {
	
	
	static int loopCtr = 0;
	
	/** 
	 * Class constructor.
	 * */
	
	public LoopFilter(){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputfileName = "data/log.txt";
		String outputfileName = "data/newlog.txt";
		ReadTXT reader = new ReadTXT(inputfileName);
		WriteTXT writer = new WriteTXT(outputfileName);
		Task t = new Task();
		String scan;  
					
		List<String> str = new ArrayList<String>();
		//List<String> output = new ArrayList<String>();
		
		try {
			
			// n-gram collapse of string
			while ((scan = reader.in.readLine()) != null) {
				// Reset string container
				str.clear();
				
				// Read the trace
				str = t.getSymbol(scan);
				
				// The multiple-gram scanning window
				for (int i=1; i<=(str.size()/2); i++) {	
					System.out.print("\nOriginal trace:\n");
					printSequence(str);
					//CRPs: Consecutive repeating patterns
					str = collapseCRPs(str, i);
					System.out.print("After "+i+"-gram collapse:\n");
					printSequence(str);
				}
				
				writer.write(t.getHeadInfo(scan));
				// Output the result to text file
				for (int i=0; i<str.size(); i++) {
					if (i == (str.size()-1))
						writer.write(str.get(i)+"\n");
					else
						writer.write(str.get(i)+" ");
					}
				}
			writer.close();
			
			// TO CAPTURE the symbols and store in a array	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	/*
	 * @par1: the trace string
	 * @par2: the sliding window size
	 * @return: the collapsed trace
	 */
	public static  List<String> collapseCRPs(List<String> str, int winSize) {
		List<String> newstr = new ArrayList<String>();
		
		// Set early termination condition
		if (winSize <= (str.size()/2)) {
			// The number of iteration for n-gram window to sliding scan
			for (int i=0; i<winSize; i++) {
				newstr = removeCRPs(str, winSize, i);
			}
			
			return newstr;
		}
		else {
			return str;
		}
	}
	
	
	/*
	 * @par1: the trace string
	 * @par2: the sliding window size
	 * @par3: the starting transition
	 * @return: the collapsed trace   
	 */
	public static List<String> removeCRPs(List<String> str, int winSize, int startIdx) {
		
		List<String> tmpstr = new ArrayList<String>();
		boolean flag = false; //For detection of non-CRPs
		int ctr = 0;		  //For detecting if it is an equal string matching
		
		// Initialize temporal string container
		tmpstr.clear();
		
		// Append the first pattern
		for (int i=0; i<startIdx+winSize; i++) {
			if (i < str.size())
				tmpstr.add(str.get(i));
			}
		
		// The starting index for the second pattern to the last pattern
		for (int i=startIdx+winSize; i<str.size(); i+=winSize) {
			ctr = winSize;
			//System.out.println(ctr);
			
			// This part is to collapse CRPs of winSize-gram window
			for (int j=i; j<i+winSize; j++) {
				// out-of-range check
				if (j < str.size() && !str.get(j).equals(str.get(j-winSize))) {
					//System.out.print("Element["+(k-w+1)+"] & Element["+(k+1)+"] Miss Match!\n");
					flag = true; // Mismatch detected!!
					break;
				}
				
				ctr--;
			}
			
			if (flag) {
				for (int j=i; j<i+winSize; j++) {
					// out-of-range check
					if (j < str.size()) {
						tmpstr.add(str.get(j));
						//System.out.print("Import: "+str.get(j)+"\n");
					}
				}
				
				//Reset flag
				flag = false;
			}
			else if (ctr == 0) {	//CRPs detected
				//System.out.println(ctr);
				System.out.print("Detected Loop Pattern:\n");
				tmpstr = markLoopSymbols(tmpstr, i-winSize, i);
				printLoop(str, i-winSize, i);
			}
		}
		
		// Update string container and reset the temporal string containers
		str.clear();
		str.addAll(tmpstr);
		return str;
	}
	
	public static List<String> markLoopSymbols(List<String> str, int startIdx, int endIdx) {
		for (int i=startIdx; i<endIdx; i++) {
			// out-of-range check
			if (i < str.size())
				str.set(i, str.get(i));
		}
		
		return str;
	}
	
	public static void printLoop(List<String> str, int startIdx, int endIdx) {
		List<String> loopstr = new ArrayList<String>();
		
		for (int i=startIdx; i<endIdx; i++) {
			// out-of-range check
			if (i < str.size())
				loopstr.add(str.get(i));
		}
		
		System.out.println(loopstr);
	}
	
	public static void printSequence(List<String> str) {
		System.out.println(str+"\t"+"Length: "+str.size());
	}
	
}

	
