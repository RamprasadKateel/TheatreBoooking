package solutions.theatre.booking.test.steps;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import solutions.theatre.services.booking.FillSeats;
import solutions.theatre.services.pojo.BookingRequest;
import solutions.theatre.services.pojo.RowSectionPair;
import solutions.theatre.services.pojo.Section;
import solutions.theatre.services.readinput.ReadInput;
import solutions.theatre.services.readinput.ReadLayout;
import static org.junit.Assert.*; 
import org.junit.Test;


public class CaseSteps {
   static String time;
   static Date now;
    ReadLayout validReadLayObj = new ReadLayout();
    BookingRequest requestInp = new BookingRequest();
    public CaseSteps()
	{
		now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US);
		time = dateFormat.format(now);
	}

    @Given("^The valid layout is provided$")
    public void the_valid_layout_is_provided() throws Throwable {
    	List<Section> sectList = new ArrayList<>();
    	Section sample =new Section(1, 1, 3);
    	Section sample2 =new Section(1, 2, 3);
    	sectList.add(sample);
    	sectList.add(sample2);
    	validReadLayObj.setSectionList(sectList);
    }
    @When("^I enter the valid request  with (\\d+) user$")
    public void i_enter_the_valid_request_with_user(int numOfTickets) throws Throwable {
		requestInp.setLayoutList(validReadLayObj.getSectionList());
		requestInp.setMaxCapacity(6);
		Map<String,Integer> validRequestMap = new LinkedHashMap<>();
		validRequestMap.put("Ram", 3);
		requestInp.setRequestObject(validRequestMap);
	}
    @Then("^I test the output of the algorithm$")
    public void i_test_the_output_of_the_algorithm() throws Throwable {
		System.out.println("Calling the readLayout");
		Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		FillSeats filleSeats = new FillSeats();
		 resultMap= filleSeats.analyze(requestInp.getRequestObject(), requestInp.getLayoutList(), requestInp.getMaxCapacity());
		 int correctRow = 1;
		 int correctSection = 1;
		 for(String audienceName: resultMap.keySet()){
			 System.out.println("The name is "+audienceName);
			 assertTrue(audienceName.equals("Ram"));
			 RowSectionPair currentRowSectionPair = resultMap.get(audienceName);
			 System.out.println("Predicted row is "+currentRowSectionPair.getRow()+" and section is "+currentRowSectionPair.getSection());
			 assertTrue(currentRowSectionPair.getRow() == correctRow);
			 assertTrue(currentRowSectionPair.getSection() == correctSection);
		 }
		 
	}
	
    @When("^I enter the request  with more number of tickets than available in theatre$")
    public void i_enter_the_invalid_request_with_user() throws Throwable {
		requestInp.setLayoutList(validReadLayObj.getSectionList());
		requestInp.setMaxCapacity(6);
		Map<String,Integer> validRequestMap = new LinkedHashMap<>();
		validRequestMap.put("Emma", 10);
		requestInp.setRequestObject(validRequestMap);
	}
    
    @Then("^I test the output of the algorithm for can't handle the party$")
    public void i_test_the_output_of_the_algorithm_for_invalid() throws Throwable {
		System.out.println("Calling the readLayout");
		Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		FillSeats filleSeats = new FillSeats();
		 resultMap= filleSeats.analyze(requestInp.getRequestObject(), requestInp.getLayoutList(), requestInp.getMaxCapacity());
		 int correctRow = 0;
		 int correctSection = 0;
		 for(String audienceName: resultMap.keySet()){
			 System.out.println("The name is "+audienceName);
			 assertTrue(audienceName.equals("Emma"));
			 RowSectionPair currentRowSectionPair = resultMap.get(audienceName);
			 System.out.println("Predicted row is "+currentRowSectionPair.getRow()+" and section is "+currentRowSectionPair.getSection());
			 assertTrue(currentRowSectionPair.getRow() == correctRow);
			 assertTrue(currentRowSectionPair.getSection() == correctSection);
			 System.out.println("If row is zero and section is zero means theatre cant handle the party");
		 }
		 
	}
    
    @When("^I enter the request  with more number of tickets than available in one section$")
    public void i_enter_the_valid_request_with_split() throws Throwable {
		requestInp.setLayoutList(validReadLayObj.getSectionList());
		requestInp.setMaxCapacity(6);
		Map<String,Integer> validRequestMap = new LinkedHashMap<>();
		validRequestMap.put("Jennifer", 6);
		requestInp.setRequestObject(validRequestMap);
	}
    
    @Then("^I test the output of the algorithm for Call to split party$")
    public void i_test_the_output_of_the_algorithm_for_split() throws Throwable {
		System.out.println("Calling the readLayout");
		Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		FillSeats filleSeats = new FillSeats();
		 resultMap= filleSeats.analyze(requestInp.getRequestObject(), requestInp.getLayoutList(), requestInp.getMaxCapacity());
		 int correctRow = -1;
		 int correctSection = -1;
		 for(String audienceName: resultMap.keySet()){
			 System.out.println("The name is "+audienceName);
			 assertTrue(audienceName.equals("Jennifer"));
			 RowSectionPair currentRowSectionPair = resultMap.get(audienceName);
			 System.out.println("Predicted row is "+currentRowSectionPair.getRow()+" and section is "+currentRowSectionPair.getSection());
			 assertTrue(currentRowSectionPair.getRow() == correctRow);
			 assertTrue(currentRowSectionPair.getSection() == correctSection);
			 System.out.println("If row is -1 and section is -1 means Call to split party ");
		 }
		 
	}
    
    @Given("^The valid big layout is provided$")
    public void the_valid_big_layout_is_provided() throws Throwable {
    	List<Section> sectList = new ArrayList<>();
    	Section sample =new Section(1, 1, 6);
    	Section sample2 =new Section(1, 2, 6);
    	Section sample3 =new Section(2, 1, 3);
    	Section sample4 =new Section(2, 2, 5);
    	Section sample5 =new Section(2, 3, 5);
    	Section sample6 =new Section(2, 4, 3);
    	Section sample7 =new Section(3, 1, 4);
    	Section sample8 =new Section(3, 2, 6);
    	Section sample9 =new Section(3, 3, 6);
    	Section sample10 =new Section(3, 4, 4);
    	Section sample11 =new Section(4, 1, 2);
    	Section sample12=new Section(4, 2, 8);
    	Section sample13 =new Section(4, 3, 8);
    	Section sample14 =new Section(4, 4, 2);
    	Section sample15 =new Section(5, 1, 6);
    	Section sample16=new Section(5, 2, 6);
    	
    	sectList.add(sample);
    	sectList.add(sample2);
    	sectList.add(sample3);
    	sectList.add(sample4);
    	sectList.add(sample5);
    	sectList.add(sample6);
    	sectList.add(sample7);
    	sectList.add(sample8);
    	sectList.add(sample9);
    	sectList.add(sample10);
    	sectList.add(sample11);
    	sectList.add(sample12);
    	sectList.add(sample13);
    	sectList.add(sample14);
    	sectList.add(sample15);
    	sectList.add(sample16);
    	validReadLayObj.setSectionList(sectList);
    }
    
    @When("^I enter the request  with less number of tickets$")
    public void i_enter_the_valid_request_with_in_less_number() throws Throwable {
		requestInp.setLayoutList(validReadLayObj.getSectionList());
		requestInp.setMaxCapacity(80);
		Map<String,Integer> validRequestMap = new LinkedHashMap<>();
		validRequestMap.put("Smith", 2);
		validRequestMap.put("Jones", 5);
		validRequestMap.put("Davis", 6);
		validRequestMap.put("Johnson", 3);
		requestInp.setRequestObject(validRequestMap);
	}
    
    @Then("^I test the output of the algorithm for seating arrangment$")
    public void i_test_the_output_of_the_algorithm_to_check_seating() throws Throwable {
		System.out.println("Calling the readLayout");
		Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		FillSeats filleSeats = new FillSeats();
		 resultMap= filleSeats.analyze(requestInp.getRequestObject(), requestInp.getLayoutList(), requestInp.getMaxCapacity());
		 for(String audienceName: resultMap.keySet()){
			 System.out.println("The name is "+audienceName);
			  RowSectionPair currentRowSectionPair = resultMap.get(audienceName);
			 System.out.println("Predicted row is "+currentRowSectionPair.getRow()+" and section is "+currentRowSectionPair.getSection());
			 assertTrue(currentRowSectionPair.getRow() < 3);
		}
		 System.out.println("If row is less than 3  means everyone is sitting front row ");
	}
    
    @When("^I enter the request  with more number of tickets$")
    public void i_enter_the_valid_request_with_in_more_number() throws Throwable {
		requestInp.setLayoutList(validReadLayObj.getSectionList());
		requestInp.setMaxCapacity(80);
		Map<String,Integer> validRequestMap = new LinkedHashMap<>();
		validRequestMap.put("Smith", 2);
		validRequestMap.put("Jones", 5);
		validRequestMap.put("Davis", 6);
		validRequestMap.put("Johnson", 3);
		validRequestMap.put("Wilson", 100);
		validRequestMap.put("Williams", 4);
		validRequestMap.put("Brown", 8);
		validRequestMap.put("Miller", 12);
		validRequestMap.put("Harry", 3);
		validRequestMap.put("Potter", 4);
		validRequestMap.put("Snape", 5);
		validRequestMap.put("Vader", 10);
		validRequestMap.put("Darth", 5);
		validRequestMap.put("Han", 3);
		validRequestMap.put("Solo", 1);
		validRequestMap.put("Eleven", 7);
		
		requestInp.setRequestObject(validRequestMap);
	}
    
    @Then("^I test the output of the algorithm for number of seats allocated$")
    public void i_test_the_output_of_the_algorithm_to_check_whether_max_orders() throws Throwable {
		System.out.println("Calling the readLayout");
		Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		FillSeats filleSeats = new FillSeats();
		 resultMap= filleSeats.analyze(requestInp.getRequestObject(), requestInp.getLayoutList(), requestInp.getMaxCapacity());
		 int countOrderServiced = 0;
		 for(String audienceName: resultMap.keySet()){
			 System.out.println("The name is "+audienceName);
			  RowSectionPair currentRowSectionPair = resultMap.get(audienceName);
			 System.out.println("Predicted row is "+currentRowSectionPair.getRow()+" and section is "+currentRowSectionPair.getSection());
			 if(currentRowSectionPair.getRow() > 0)countOrderServiced++;
		}
		 assertTrue(countOrderServiced > 11);
		 System.out.println("we have serviced more than 80% of orders without Call for split and cant handle request");
	}
}
