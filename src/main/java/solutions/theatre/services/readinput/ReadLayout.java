package solutions.theatre.services.readinput;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import solutions.theatre.services.pojo.Section;
import solutions.theatre.utilities.config.*;
public class ReadLayout {
    private List<Section> sectionList = null;

    public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public int readLayout(Scanner inputReader) {
        sectionList = new LinkedList<>();
       

       String line = inputReader.nextLine();
        int totalCapacity = 0;
        int rowNumber = 1;
        while (line.length() > 0) {
        	if(line.equalsIgnoreCase("q"))break;
            String[] sectionCapacities = line.split("\\s+");
            List<Section> tempSectionList = new LinkedList<>();
            boolean errorFlag = false;

            for(int sectionNumber = 1; sectionNumber <= sectionCapacities.length; sectionNumber++) {
                int capacity = validateCapacity(sectionCapacities[sectionNumber - 1]);
                if(capacity > 0) {
                    tempSectionList.add(new Section(rowNumber, sectionNumber, capacity));
                    totalCapacity+= capacity;
                } else {
                    System.out.println(Constants.INPUT_RETRY_MESSAGE);
                    errorFlag = true;
                    break;
                }
            }

            line = inputReader.nextLine();
            if(!errorFlag) {
                sectionList.addAll(tempSectionList);
                rowNumber++;
            }
        }
        return totalCapacity;
    }

    private int validateCapacity(String capacityString) {
        int capacity;
        try {
            capacity = Integer.parseInt(capacityString);
        } catch (NumberFormatException numFormatException) {
            return -1;
        }
        if(capacity < 1) {
            return -1;
        }
        return capacity;
    }
    
    

    public List<Section> getSectionList() {
    	return this.sectionList;
    }
}
