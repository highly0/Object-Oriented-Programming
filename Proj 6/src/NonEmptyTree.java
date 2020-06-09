import java.util.List;

//@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> extends Tree<K, V> {
	private K key;
	private V value;
	private Tree<K, V> left, right;
	
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right ) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public NonEmptyTree<K, V> addKeyAndValue(K keyIn, V valueIn) {
		if (keyIn == null || valueIn == null)
			throw new IllegalArgumentException("no bueno");

		if (keyIn.compareTo(key) == 0) {
			value = valueIn;
		} else if (keyIn.compareTo(key) < 0) {
			left = left.addKeyAndValue(keyIn, valueIn);
		} else {
			right = right.addKeyAndValue(keyIn, valueIn);
		}
		return this;
	}

	@Override
	public int size() {
		return 1 + left.size() + right.size();
	}

	@Override
	public V getValueForKey(K keyIn) {
		if(keyIn == null) 
			throw new IllegalArgumentException("no bueno");
		
		if(keyIn.compareTo(key) == 0) {
			return value;
		} else if(keyIn.compareTo(key) < 0) {
			return left.getValueForKey(keyIn);
		} else {
			return right.getValueForKey(keyIn);
		}
	}

	@Override
	public int treeHeight() {
		if(left instanceof EmptyTree && right instanceof EmptyTree) {
			return 0;
		} else if(left instanceof EmptyTree && !(right instanceof EmptyTree)) {
			return 1 + right.treeHeight();
		} else if(!(left instanceof EmptyTree) && right instanceof EmptyTree) {
			return 1 + left.treeHeight();
		} else { // at the leafs
			return 1 + Math.max(left.treeHeight(), right.treeHeight());
		}
	}

	@Override
	public boolean pathFromRoot(K keyIn, List<K> keyPath) {
		if(keyIn == null || keyPath == null) 
			throw new IllegalArgumentException("no bueno");
		
		boolean result = true;
		if(keyIn.compareTo(key) == 0) {
			keyPath.add(key);
			return true;
		} else if(keyIn.compareTo(key) < 0) {
			if(left instanceof EmptyTree) {
				result = false;
				keyPath.clear();
			} else {
				keyPath.add(key);
				left.pathFromRoot(keyIn, keyPath);
			}
		} else {
			if(right instanceof EmptyTree) {
				result = false;
				keyPath.clear();
			} else {
				keyPath.add(key);
				right.pathFromRoot(keyIn, keyPath);
			}	
		}
		return result;
	}

	@Override
	public K max() throws EmptyTreeException {
		if(right instanceof EmptyTree) {
			return key;
		}
		return right.max();
	}

	@Override
	public K min() throws EmptyTreeException {
		if(left instanceof EmptyTree) {
			return key;
		}
		return left.min();
	}

	@Override
	public Tree<K, V> removeKeyAndValue(K keyIn) {
		if(keyIn == null) 
			throw new IllegalArgumentException("no bueno");	

		if(keyIn.compareTo(key) == 0) {
			// if keyIn is a leaf or only have 1 child
			if(left instanceof EmptyTree) {
				return right;
			} else if (right instanceof EmptyTree) {
				return left;
			} else {
				// node with 2 children
				try {
					value = getValueForKey(right.min());
					key = right.min();
				} catch (EmptyTreeException e) {
					e.printStackTrace();
				}
				right = right.removeKeyAndValue(key);
			}	
		} else if(keyIn.compareTo(key) < 0) {
			if(left instanceof EmptyTree) {
				return this;
			}
			left = left.removeKeyAndValue(keyIn);
		} else {
			if(right instanceof EmptyTree) {
				return this;
			}
			right = right.removeKeyAndValue(keyIn);
		}
		return this;
	}

	public String toString() {
		String result = "";
		result += left.toString();
		result += key + "=>" + value + " ";
		result += right.toString();
		return result;
	}
	
}
