import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyTree<K extends Comparable<K>, V> extends Tree<K, V> {
	private static EmptyTree emptyInstance = null;	
	
	
	public static EmptyTree getInstance() {
		if(emptyInstance == null)
			emptyInstance = new EmptyTree();
		return emptyInstance;
	}
	
	public String toString() {
		return "";
	}
	
	public String toString(String result) {
		return "";
	}
	
	@Override
	public NonEmptyTree<K, V> addKeyAndValue(K keyIn, V valueIn) {
		if(keyIn == null || valueIn == null) 
			throw new IllegalArgumentException("no bueno");
		
		NonEmptyTree<K,V> newTree = 
				new NonEmptyTree(keyIn, valueIn, EmptyTree.getInstance(),
						EmptyTree.getInstance());
		return newTree;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public V getValueForKey(K keyIn) {
		if(keyIn == null) 
			throw new IllegalArgumentException("no bueno");
		return null;
	}

	@Override
	public int treeHeight() {
		return -1;
	}

	@Override
	public boolean pathFromRoot(K keyIn, List<K> keyPath) {
		if(keyIn == null || keyPath == null) 
			throw new IllegalArgumentException("no bueno");
		keyPath.clear();
		return false;
	}

	@Override
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	@Override
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	@Override
	public Tree<K, V> removeKeyAndValue(K keyIn) {
		if(keyIn == null) 
			throw new IllegalArgumentException("no bueno");
		return null;
	}
	
}
