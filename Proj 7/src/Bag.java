import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Bag {
	HashMap< Integer,Integer> map; 
	
	public Bag() {
		map = new HashMap< Integer,Integer>();
	}
	
	public void add(int elt) { 
		if (map.containsKey(elt)) {
			map.replace(elt, map.get(elt) + 1);
		} else {
			map.put(elt, 1);
		}
	}
	
	public boolean contains(int elt) {
		return map.containsKey(elt);
	}
	
	public int getCount(int elt) {
		if(map.get(elt) != null) {
			return map.get(elt);
		} else {
			return 0;
		}
	}
	
	public int size() {
		int size = 0;
		
		for(int key : map.keySet()) {
			if(map.get(key) == 1) {
				size++;
			} else {				
				size += map.get(key);
			}
		}
		return size;
	}
	
	public Set<Integer> uniqueElements() {
		Set<Integer> set = new HashSet<Integer>();
		
		for(int key : map.keySet()) {
			set.add(key);
		}
		return set;
	}
	
	public void remove(int elt) {
		if(map.containsKey(elt)) {
			if(map.get(elt) == 1) {
				map.remove(elt, map.get(elt));
			} else {
				map.replace(elt, map.get(elt) - 1);
			}
		}
	}
	
}
