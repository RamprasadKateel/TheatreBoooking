package solutions.theatre.services.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import solutions.theatre.services.pojo.RowSectionPair;

/**
 * @author Ram
 * This algorithm is a greedy approach, where we try to fit the audience form the first row. 
 */
public class FirstFit implements Fit {
	
	/**
	 * @param audienceMap - Contains original request Map of audience Name and number of tickets
	 * @param allocationMap - Contains the result. Key audience Name and Value Row and Section pair value
	 * @param sectionSizeMap - This is the map which holds the secionSize as Key and corresponding Row and Section values which arranged in ascending order
	 * @param rowMap - This LinkedHashMap holds Row number and corresponding section size it currently has.
	 * @param totalCapacity - Its the total capacity of the theatre
	 */
	@Override
	public Map seatAllocation(Map<String, Integer> audienceMap, Map<String, RowSectionPair> allocationMap,
			TreeMap<Integer, TreeSet<RowSectionPair>> sectionSizeMap, HashMap<Integer, LinkedList<Integer>> rowMap,int totalCapacity) {
		
		for (String audienceName : audienceMap.keySet()) {
			int numOfticket = audienceMap.get(audienceName) != null ? audienceMap.get(audienceName) : 0;
			int rowNumber = 0;
			int oldSectionVal = 0;
			int newSectionVal = -1;
			if (numOfticket > 0) {
				RowSectionPair allocatedRowSect = allocationMap.get(audienceName);
				Set<Map.Entry<Integer, LinkedList<Integer>>> rowSetEntry = rowMap.entrySet();
				Iterator<Entry<Integer, LinkedList<Integer>>> rowSetEntryIterator = rowSetEntry.iterator();
				while (rowSetEntryIterator.hasNext()) {
					Entry rowMapEntry = rowSetEntryIterator.next();
					rowNumber = (int) rowMapEntry.getKey();
					if (rowNumber < allocatedRowSect.getRow()) {
						LinkedList<Integer> rowValList = (LinkedList<Integer>) rowMapEntry.getValue();
						for (int sect = 0; sect < rowValList.size(); sect++) {
							if (rowValList.get(sect) >= numOfticket) {
								RowSectionPair newRowSecPair = new RowSectionPair(rowNumber, sect + 1);
								allocationMap.put(audienceName, newRowSecPair);
								oldSectionVal = rowValList.get(sect);
								newSectionVal = rowValList.get(sect) - numOfticket;
								break;
							}
						}

					}
					if (newSectionVal != -1)
						break;
				}
				if (rowNumber < allocatedRowSect.getRow() && newSectionVal > -1 && oldSectionVal > 0) {
					LinkedList<Integer> changeList = rowMap.get(rowNumber);
					int replaceZeroIndex = changeList.indexOf(oldSectionVal);
					changeList.removeFirstOccurrence(oldSectionVal);
					changeList.add(replaceZeroIndex, newSectionVal);
					rowMap.put(rowNumber, changeList);
					LinkedList<Integer> addOldList = rowMap.get(allocatedRowSect.getRow());

					int replaceOldValIndex = allocatedRowSect.getSection()-1;
					int oldValue = addOldList.get(replaceOldValIndex);
					addOldList.remove(replaceOldValIndex);
					addOldList.add(replaceOldValIndex, oldValue + numOfticket);
					rowMap.put(allocatedRowSect.getRow(), addOldList);

				}
					
			}

		}
		return allocationMap;
	}



}
