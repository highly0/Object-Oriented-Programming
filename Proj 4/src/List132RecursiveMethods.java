
public class List132RecursiveMethods {
	
	public static <T extends Comparable<T>> boolean allLarger(List132<T> list, 
			T element) {
		if(list == null || element == null) {
			throw new IllegalArgumentException("no bueno");
		}
		if(list.size() == 0 && element != null) {
			return true;
		}
		boolean result = allLarger(list, element, 0);
		return result;
	}
	
	public static <T extends Comparable<T>> boolean allLarger(List132<T> list, 
			T element, int index) {
		if(list.get(index) == null) {
			return true;
		} else {
			if(list.get(index).compareTo(element) <= 0) {
				return false;
			} else {
				return allLarger(list, element, index + 1);
			}
		}
	}
	
	public static <T extends Comparable<T>> T elementBefore(List132<T> list, 
			T element) {
		if(list == null || element == null) {
			throw new IllegalArgumentException("no bueno");
		}
		return elementBefore(list, element, 0);
	}

	public static <T extends Comparable<T>> T elementBefore(List132<T> list, 
			T element, int index) {
		if(list.get(index) == null) {
			return null;
		} else {
			if(list.get(index).compareTo(element) == 0) {
				return list.get(index - 1);
			} else {
				return elementBefore(list, element, index + 1);
			}
		}	
	}

	public static <T extends Comparable<T>> void replaceElement(List132<T> list,
			T element, List132<T> otherList) {
		if(list == null || element == null || otherList == null) {
			throw new IllegalArgumentException("no bueno");
		}
		replaceElement(list, element, otherList, 0, 0);
	}
	
	public static <T extends Comparable<T>> void replaceElement(List132<T> list,
			T element, List132<T> otherList, int index, int otherIdx) {
		if(list.get(index) == null) {
			return;
		} else {
			if(list.get(index).compareTo(element) == 0) {
				if(otherList.get(otherIdx) != null) {
					list.set(index, otherList.get(otherIdx));
					replaceElement(list, element, otherList, index + 1, otherIdx + 1);
				} else {
					replaceElement(list, element, otherList, index + 1, otherIdx);
				}
			} else {
				replaceElement(list, element, otherList, index + 1, otherIdx);
			}
		}
	}

	public static <T> List132<T> 
	getListWithoutRange(List132<T> list, int fromPos, int toPos) {
		if(list == null) {
			throw new IllegalArgumentException("no bueno");
		}
		if(fromPos < 0 || toPos < 0 || fromPos > list.size() 
				|| toPos > list.size()) {
			throw new IndexOutOfBoundsException("no bueno");
		}
		if(fromPos > toPos) return list;
		List132<T> result = new List132<T>();
		return getListWithoutRange(list, result, fromPos, toPos, 0);
	}
	
	public static <T> List132<T> 
	getListWithoutRange(List132<T> list, List132<T> result, 
			int fromPos, int toPos, int index) {
		if(list.get(index) == null) {
			return result;
		} else {
			if(index < fromPos || index > toPos) {
				result.add(list.get(index));
			}
			return getListWithoutRange(list, result, fromPos, toPos, index + 1);
		}
	}
}
