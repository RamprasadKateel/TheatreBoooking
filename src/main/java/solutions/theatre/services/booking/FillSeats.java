package solutions.theatre.services.booking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import solutions.theatre.services.algorithm.BestFit;
import solutions.theatre.services.algorithm.FirstFit;
import solutions.theatre.services.algorithm.Fit;
import solutions.theatre.services.pojo.RowSectionPair;
import solutions.theatre.services.pojo.Section;

/**
 * @author Ram
 * This class orchestrates the generation of the result with input from Request and Layout
 */
public class FillSeats {
	private int remainingCapacity = -1;

	/**
	 * 
	 * @param audienceMap - Contains original request Map of audience Name and number of tickets
	 * @param layoutList - List from the Layout intake
	 * @param totalCapacity - its the total capacity of the theatre
	 */
	public Map<String, RowSectionPair> analyze(Map<String, Integer> audienceMap, List<Section> layoutList,
			int totalCapacity) {
		HashMap<Integer, LinkedList<Integer>> rowMap = getRowMap(layoutList);
		TreeMap<Integer, TreeSet<RowSectionPair>> sectionSizeMap = getSectionMap(layoutList);
		Map<String, RowSectionPair> allocationMap = new LinkedHashMap<>();
		remainingCapacity = totalCapacity;
		BestFit bestFit = new BestFit();
		allocationMap = bestFit.seatAllocation(audienceMap, allocationMap, sectionSizeMap, rowMap, totalCapacity);
		
		if (bestFit.getRemainingCount() > 0) {
			Fit firstFit = new FirstFit();
			allocationMap = firstFit.seatAllocation(audienceMap, allocationMap, sectionSizeMap, rowMap, totalCapacity);
		}
		allocationMap = checkCallToSplit(audienceMap, allocationMap);

		return allocationMap;

	}

	//This method is used to check whether the Call for split is legit or do we need to correct them.  
	private Map<String, RowSectionPair> checkCallToSplit(Map<String, Integer> audienceMap,
			Map<String, RowSectionPair> allocationMap) {
		for (String audienceName : audienceMap.keySet()) {
			int numOfticket = audienceMap.get(audienceName) != null ? audienceMap.get(audienceName) : 0;
			RowSectionPair currentRowPair = allocationMap.get(audienceName);
			if (currentRowPair.getRow() == -1 && numOfticket > remainingCapacity) {
				RowSectionPair audienceFullObj = new RowSectionPair(0, 0);
				allocationMap.put(audienceName, audienceFullObj);
			} else if (currentRowPair.getRow() == -1) {
				remainingCapacity -= numOfticket;
			}
		}
		return allocationMap;
	}

	//Converting the input to TreeMap which is used by Best fit algorithm
	private TreeMap<Integer, TreeSet<RowSectionPair>> getSectionMap(List<Section> layoutList) {
		TreeMap<Integer, TreeSet<RowSectionPair>> resultMap = new TreeMap<>();
		for (Section sect : layoutList) {
			TreeSet<RowSectionPair> rowSectTree = new TreeSet<>();
			if (resultMap.containsKey(sect.getSectionCapacity()) && resultMap.get(sect.getSectionCapacity()) != null) {
				rowSectTree = resultMap.get(sect.getSectionCapacity());
			}
			rowSectTree.add(new RowSectionPair(sect.getRowNumber(), sect.getSectionNumber()));
			resultMap.put(sect.getSectionCapacity(), rowSectTree);
		}

		return resultMap;

	}

	private HashMap<Integer, LinkedList<Integer>> getRowMap(List<Section> layoutList) {
		HashMap<Integer, LinkedList<Integer>> resultMap = new HashMap<>();
		for (Section sect : layoutList) {
			LinkedList<Integer> sectCapacityArr = new LinkedList<>();
			if (resultMap.containsKey(sect.getRowNumber()) && resultMap.get(sect.getRowNumber()) != null) {
				sectCapacityArr = resultMap.get(sect.getRowNumber());
			}
			sectCapacityArr.add(sect.getSectionCapacity());
			resultMap.put(sect.getRowNumber(), sectCapacityArr);
		}

		return resultMap;
	}
}
