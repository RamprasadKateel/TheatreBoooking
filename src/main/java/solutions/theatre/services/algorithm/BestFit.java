/**
 * 
 */
package solutions.theatre.services.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import solutions.theatre.services.pojo.RowSectionPair;

/**
 * @author Ram
 * In this algorithm we are try in to fit the seats according to the section size rather just filling from the front, This optimizes the algorithm to process masimum orders
 */
public class BestFit implements Fit {
	private int remainingCapacity;
	
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
		this.remainingCapacity = totalCapacity;
		for (String audienceName : audienceMap.keySet()) {
			int numOfTicketsReq = audienceMap.get(audienceName) != null ? audienceMap.get(audienceName) : 0;
			if (numOfTicketsReq != 0) {
				Entry<Integer, TreeSet<RowSectionPair>> bestFitEntry = sectionSizeMap.ceilingEntry(numOfTicketsReq);
				if (numOfTicketsReq > remainingCapacity)
					allocationMap.put(audienceName, new RowSectionPair(0, 0));
				else if (bestFitEntry == null)
					allocationMap.put(audienceName, new RowSectionPair(-1, -1));
				else {
					TreeSet<RowSectionPair> currentRowList = (bestFitEntry.getValue() != null ? bestFitEntry.getValue()
							: new TreeSet<RowSectionPair>());
					RowSectionPair firstRowSectPair = currentRowList.pollFirst();
					allocationMap.put(audienceName, firstRowSectPair);
					int seatsInSectLeft = bestFitEntry.getKey() - numOfTicketsReq;
					if (seatsInSectLeft > 0) {
						TreeSet<RowSectionPair> rowSectTree = new TreeSet<>();
						if (sectionSizeMap.containsKey(seatsInSectLeft)) {
							rowSectTree = sectionSizeMap.get(seatsInSectLeft);
						}
						rowSectTree.add(firstRowSectPair);
						sectionSizeMap.put(seatsInSectLeft, rowSectTree);
					}
					LinkedList<Integer> bookingList = rowMap.get(firstRowSectPair.getRow());
					int replaceZeroIndex = bookingList.indexOf(bestFitEntry.getKey());
					bookingList.removeFirstOccurrence(bestFitEntry.getKey());
					bookingList.add(replaceZeroIndex, seatsInSectLeft);
					rowMap.put(firstRowSectPair.getRow(), bookingList);
					if(currentRowList.isEmpty())sectionSizeMap.remove(bestFitEntry.getKey());
					else sectionSizeMap.put(bestFitEntry.getKey(), currentRowList);
					remainingCapacity -= bestFitEntry.getKey();
				}
			}

		}
		return allocationMap;
	}

	public int getRemainingCount() {
		return this.remainingCapacity;
	}


}
