package solutions.theatre.services.pojo;

import java.util.Scanner;

import solutions.theatre.utilities.config.Constants;

class ReadRequest {

	protected String readFromConsole(Scanner in) {

		StringBuilder request = new StringBuilder();
		String inputFromConsole = null;

		while(in.hasNext()) {
			inputFromConsole =in.nextLine().trim();
			if(inputFromConsole.equals(Constants.END_OF_REQUEST)) {
				break;
			}
			
			if(inputFromConsole.length() <= 0) continue;
			
			try {
				validate(inputFromConsole);
				request.append(inputFromConsole);
				request.append(Constants.NEW_LINE);
			} catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println(Constants.INPUT_RETRY_MESSAGE);
			}
		}

		return request.toString();
	}

	private void validate(String inputFromConsole) throws Exception {
		String[] inputSplit = inputFromConsole.split(Constants.SPLIT_BY_SPACE);
		if(inputSplit.length == 2) {
			int val = Integer.parseInt(inputSplit[1]);
			if(val < 0) {
				throw new Exception(Constants.INVALID_INPUT);
			}
		}else {
			throw new Exception(Constants.INVALID_INPUT);
		}
	}

}
