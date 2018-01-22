package solutions.theatre.services.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import solutions.theatre.utilities.config.Constants;

public class BuildInput {
	public Map<String,Integer> buildRequestMapObject(String inputFromConsole) throws Exception {

		String[] inputSplitByLine = inputFromConsole.split(Constants.NEW_LINE);

		Map<String,Integer> requestObject = new LinkedHashMap<String,Integer>();

		for(String each_line : inputSplitByLine) {
			String[] splitBySpace = each_line.split(Constants.SPLIT_BY_SPACE);
			
			if(splitBySpace.length == 2) {
				requestObject.put(splitBySpace[0], Integer.parseInt(splitBySpace[1]));
			}else {
				throw new Exception(Constants.INVALID_INPUT);
			}
		}

		return requestObject;
	}
}
