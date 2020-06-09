
import java.util.List;

public abstract class Tree<K extends Comparable<K>, V> {

  abstract public NonEmptyTree<K, V> addKeyAndValue(K keyIn, V valueIn);
  abstract public int size();
  abstract public V getValueForKey(K keyIn);
  abstract public int treeHeight();
  abstract public boolean pathFromRoot(K keyIn, List<K> keyPath);
  abstract public K max() throws EmptyTreeException;
  abstract public K min() throws EmptyTreeException;
  abstract public Tree<K, V> removeKeyAndValue(K keyIn);
}
