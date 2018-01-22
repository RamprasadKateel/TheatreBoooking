package solutions.theatre.services.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import solutions.theatre.services.pojo.RowSectionPair;

/**
 * @author Ram
 * 
 */
public interface Fit {
	public Map seatAllocation(Map<String, Integer> audienceMap,
			Map<String, RowSectionPair> allocationMap, TreeMap<Integer, TreeSet<RowSectionPair>> sectionSizeMap,
			HashMap<Integer, LinkedList<Integer>> rowMap,int totalCapacity);
}
