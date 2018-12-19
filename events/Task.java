package events;

import java.util.ArrayList;
import java.util.List;

public class Task {
	String[] tmp;

	
	/** 
	  * Class constructor.
	*/	
	public Task(){
		//Initialization
	}
	
	//Extract the header information
	public String getHeadInfo(String str){
		String info;     //Head info such as frequency and Case ID
		String cut = str.substring(str.indexOf(" ")+1, str.length());
		String frequency = str.substring(0, str.indexOf(" ")-1);
        String caseID = cut.substring(0, cut.indexOf(" "));
        
        info = frequency+"x "+caseID+" ";
        return info;
	}	

	//Extract the transitions
	public List<String> getSymbol(String str){
		List<String> symbols = new ArrayList<String>();  //Transitions
		String cut = str.substring(str.indexOf(" ")+1, str.length());
        String trace = cut.substring(cut.indexOf(" ")+1, cut.length());
        tmp = trace.split(" ");
        
        for (int i=0; i<tmp.length; i++){
        	if (tmp[i] != " ") {
        		symbols.add(tmp[i]);
        	}
        }
        
        return symbols;
	}
}
