package com.elink.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
public class MapUtils {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue1(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <K, V extends Comparable<V>> Map<K, V> sortByValue2(final Map<K, V> map) {
		Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K k1, K k2) {
				int compare = map.get(k1).compareTo(map.get(k2));
				if (compare == 0)
					return 1;
				else
					return compare;
			}
		};
		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue3(Map<K, V> map) {
		return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
	
	public static void main(String[] args) {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("A", 99.5);
        map.put("B", 67.4);
        map.put("C", 67.4);
        map.put("D", 67.3);

        System.out.println("unsorted map: " + map);
        System.out.println("results: " + sortByValue1(map));
        System.out.println("results: " + sortByValue2(map));
        System.out.println("results: " + sortByValue3(map));
    }
}
