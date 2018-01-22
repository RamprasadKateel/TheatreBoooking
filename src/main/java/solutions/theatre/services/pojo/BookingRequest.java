package solutions.theatre.services.pojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import solutions.theatre.services.booking.FillSeats;
import solutions.theatre.services.helper.BuildInput;

/**
 * @author Ram
 * This Bean class is for taking or processing the input request
 */
public class BookingRequest {

	private String inputFromConsole;
	private Scanner inputScanner;
	private Map<String,Integer> requestObject;
	private int maxCapacity;
	private List<Section> layoutList;

	public BookingRequest(){
		inputFromConsole = null;
		inputScanner = null;
	}

	public BookingRequest(Scanner in,List<Section> layoutList,int maxCapacity){
		this.setInputScanner(in);
		this.setMaxCapacity(maxCapacity);
		this.setLayoutList(layoutList);
	}

	public Scanner getInputScanner() {
		return inputScanner;
	}

	public void setInputScanner(Scanner inputScanner) {
		this.inputScanner = inputScanner;
	}

	public String getRequest() {
		return inputFromConsole;
	}

	public void setRequest(String inputFromConsole) {
		this.inputFromConsole = inputFromConsole;
	}

	public void processBookingRequest() {
		this.inputFromConsole = new ReadRequest().readFromConsole(inputScanner);
	}

	public Map<String,RowSectionPair> getResult() {
		Map<String,RowSectionPair> resultMap = new LinkedHashMap<>();
		if(this.inputFromConsole.length() > 0) {
				try {
				this.requestObject = new BuildInput().buildRequestMapObject(inputFromConsole);
				FillSeats filleSeats = new FillSeats();
				 resultMap= filleSeats.analyze(requestObject, this.layoutList, this.maxCapacity);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Input Null");
		}

		return resultMap;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public List<Section> getLayoutList() {
		return layoutList;
	}

	public void setLayoutList(List<Section> layoutList) {
		this.layoutList = layoutList;
	}

	
	public Map<String,Integer> getRequestObject() {
		return requestObject;
	}

	public void setRequestObject(Map<String,Integer> requestObject) {
		this.requestObject = requestObject;
	}
}
