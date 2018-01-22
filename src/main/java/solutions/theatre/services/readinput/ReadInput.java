package solutions.theatre.services.readinput;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import solutions.theatre.services.helper.PrintOutput;
import solutions.theatre.services.pojo.BookingRequest;
import solutions.theatre.services.pojo.RowSectionPair;
import solutions.theatre.services.pojo.Section;
import solutions.theatre.utilities.config.Constants;

public class ReadInput {
	public static void main(String args[]){
		 System.out.println(Constants.WELCOME_MESSAGE);
		 System.out.println(Constants.LAYOUT_MESSAGE);
		 Scanner inputReader = new Scanner(System.in);
		 ReadLayout layoutObj = new ReadLayout();
		 int totalCapacity = layoutObj.readLayout(inputReader);
		 System.out.println(Constants.LAYOUT_END_MESSAGE);
		 List<Section> layoutList = layoutObj.getSectionList();
		 Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		 while(true){
			 System.out.println(Constants.INSTRUCTION_MESSAGE);
				
			 int choiceInput = inputReader.nextInt();
			 if(choiceInput == 1){
				 inputReader = new Scanner(System.in);
				 System.out.println(Constants.LAYOUT_MESSAGE);
				 totalCapacity = layoutObj.readLayout(inputReader);
				 System.out.println(Constants.LAYOUT_END_MESSAGE);
				 layoutList = layoutObj.getSectionList();
			 }
			 else if(choiceInput == 2)  {
				 System.out.println(Constants.REQUEST_MESSAGE);
				 BookingRequest bookRequest  =new BookingRequest(inputReader,layoutList,totalCapacity); 
				 bookRequest.processBookingRequest();
				 System.out.println(Constants.REQUEST_END_MESSAGE);
				 resultMap = bookRequest.getResult();
			 }
			 else if(choiceInput == 3){
				 if(resultMap.isEmpty())System.out.println(Constants.INVALID_OUTPUT);
				 else{
					 PrintOutput printOutput = new PrintOutput();
					 printOutput.printOnConsole(resultMap);
				 }
			 }
			 else break;
		 }
		 
	}

   public static void readInput(String inp) {
		
		for(String row:inp.split("\n")){
			for(String section: inp.split(" ")){
				System.out.println(section);
			}
		}
		
	}
}
