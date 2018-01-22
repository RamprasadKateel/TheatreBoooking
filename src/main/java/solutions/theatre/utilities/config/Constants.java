package solutions.theatre.utilities.config;

public class Constants {
    public static final String INSTRUCTION_MESSAGE = "Please enter 1 to modify layout or enter 2 to send request or enter 3 to print or enter 4 to exit";
    public static final String WELCOME_MESSAGE = "Welcome, please enter the layout information";//LAYOUT_MESSAGE
    public static final String LAYOUT_MESSAGE = "Valid format for layout, each section is seperated by space and each row is seperated by newline. Press Q to complete Layout";
    public static final String LAYOUT_END_MESSAGE = "---------Layout Complete----------------";
    public static final String REQUEST_MESSAGE = "Valid format for Request,Each entry is separated by newline. Each entry contains Person name and Ticket Count which is separated by space. Press Q to complete request";
    public static final String REQUEST_END_MESSAGE = "---------Request Complete----------------";
    public static final String NEW_LINE = System.lineSeparator();
	public static final String END_OF_REQUEST = "q";
	public static final String INVALID_INPUT = "Invalid Input";
	public static final String SPLIT_BY_SPACE = "\\s+";
	public static final String INPUT_RETRY_MESSAGE = "Please Input Again";
	public static final String INVALID_OUTPUT = "No request to process";
}
