
public class NondecreasingOrderLinkedList<T 
extends Comparable<T>> extends RegularLinkedList<T> {
	
	@Override
	public void add(T newValue) {
		if(newValue == null) {
			throw new IllegalArgumentException("no bueno");
		}
		Node newNode = new Node(newValue);		
		Node curr; 
		  
        /* Special case for head node */
        if (head == null || head.data.compareTo(newValue) >=0 ) { 
           newNode.next = head; 
           head = newNode; 
        } else { 
           /* Locate the node before point of insertion. */
           curr = head; 
 
           while (curr.next != null && 
                  curr.next.data.compareTo(newValue) < 0) {
        	   curr = curr.next; 
           }
           newNode.next = curr.next; 
           newNode.prev = curr;
           curr.next = newNode;          
        } 	
	}
	
	@Override
	public int numTimesValueIsPresent(T value) {
		if(value == null) {
			throw new IllegalArgumentException("no bueno");
		}	
		Node curr = head;
		int result = 0;
		
		if(value.compareTo(head.data) < 0)
			return 0;
		
		while(curr != null && curr.data.compareTo(value) <= 0) {
			if(curr.data.compareTo(value) == 0) {
				result++;
			} 
			curr = curr.next;
		}
		return result;
	}
	
	@Override
	public int indexOfValue(T value) {
		if(value == null) {
			throw new IllegalArgumentException("no bueno");
		}
		Node curr = head;
		int idx = 0;
		
		while(curr != null && curr.data.compareTo(value) <= 0) {
			if(curr.data.compareTo(value) == 0) {
				return idx;
			}
			idx++;
			curr = curr.next;
		}
		return -1;
	}
	
}
