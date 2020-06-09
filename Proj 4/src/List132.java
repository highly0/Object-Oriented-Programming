
import java.util.ArrayList;

public class List132<T> {

  private ArrayList<T> elements= new ArrayList<>();

  // Appends newElement to its current object Lists132.  Note that elements
  // of Lists132s are not necessarily stored in any particular order.
  public void add(T newElement) {
    if (newElement == null)
      throw new IllegalArgumentException("Please don't try to add null.");
    else elements.add(newElement);
  }

  // Changes the element stored at position index of elements (where the
  // first element's position is 0) to be element.  Has no effect if the
  // value of index is invalid for the list.
  public void set(int index, T element) {
    if (index >= 0 && index < elements.size())
      elements.set(index, element);
  }

  // Returns the element stored at position index (where the first
  // element's position is 0) of its current object Lists132.
  public T get(int index) {
    T result= null;

    if (index >= 0 && index < elements.size())
      result= elements.get(index);

    return result;
  }

  // Returns the number of elements being stored in a Lists132 (in its
  // ArrayList).
  public int size() {
    return elements.size();
  }

  // Returns a string representation of the elements being stored in a
  // Lists132 (in its ArrayList), with a blank space between each pair of
  // elements.
  @Override
  public String toString() {
    String result= "";
    int i;

    for (i= 0; i < size(); i++)
      result += get(i) + (i < (size() - 1) ? " " : "");

    return result;
  }

}
