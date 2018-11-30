import java.util.LinkedList;

public class LinkedListWrapper {
	/**
	* @author black_knight
	*/
	private LinkedList<String> linkedList; 
	/**
	 * A default constructor. Constructs a new, empty linkedList to be wrapped
	 */
	public LinkedListWrapper(){
		this.linkedList = new LinkedList<String>();
	}
	/**
	 * 
	 * @return the linkedList which wrapped
	 */
	public LinkedList<String> getList (){
		return linkedList;
	}
	/**
	 * set a new linkedList to be wrapped
	 */
	public void setList (LinkedList<String> newLinkedList){
		linkedList = newLinkedList;
	}
}
