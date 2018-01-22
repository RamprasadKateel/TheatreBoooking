package solutions.theatre.services.helper;

import java.util.Map;

import solutions.theatre.services.pojo.RowSectionPair;


/**
 * @author Ram
 * This class is used to generate the output on screen
 */
public class PrintOutput {

	public void printOnConsole(Map<String, RowSectionPair> allocationMap) {
		for(String audienceName: allocationMap.keySet()){
			RowSectionPair currentRowPair = allocationMap.get(audienceName);
			String newLine = System.lineSeparator();
			StringBuilder result = new StringBuilder();
			result.append(audienceName);
			if(currentRowPair.getRow() == 0){
				result.append(" Sorry, we can't handle your party.");
			}else if(currentRowPair.getRow() == -1){
				result.append(" Call to split party.");
			}else{
				result.append(" Row ");
				result.append(currentRowPair.getRow());
				result.append(" Section ");
				result.append(currentRowPair.getSection());
			}
			result.append(newLine);
			System.out.println(result.toString());
		}
		
	}

}
