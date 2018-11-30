
public class OpenHashSet extends SimpleHashSet {
	/**
	* @author black_knight
	*/	
	
	/**
	 * Constructs a new, empty table with the specified load factors, and the
	 * default initial capacity (16).
	 *
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	private String[] openHashList;
	private static final int DELETE=1, CONTAINS=2, ADD=3, DIRECT_ADD = 4;
	private static final int NOT_FOUND = -1;
	private static final String DELETED_REF = "";
	public OpenHashSet(){
		super();
		openHashList = new String[capacity()];			
	}
	/**
	 * A default constructor. Constructs a new, empty table with default
	 * initial capacity (16), upper load factor (0.75) and lower load factor
	 * (0.25).
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
		openHashList = new String[capacity()];
	}
	/**
	 * Data constructor - builds the hash set by adding the elements one by
	 * one. Duplicate values should be ignored. The new table has the default
	 * values of initial capacity (16), upper load factor (0.75), and lower
	 * load factor (0.25).
	 * @param data - Values to add to the set.
	 */	 
	public OpenHashSet(java.lang.String[] data){
		super (data);
		openHashList = new String[capacity()];
		for (int i=0;i<data.length;i++)
			add(data[i]);
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public boolean add(String newValue){
		return PerformAction(fixedHashValue(newValue), newValue, ADD);
	}
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal){
		return PerformAction(fixedHashValue(searchVal), searchVal, CONTAINS);
	}
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean  delete(String toDelete){
		return PerformAction(fixedHashValue(toDelete), toDelete, DELETE);
	}
	/**
	 * 
	 * @param fixedhashValue updated hashcode of the given value according to table size  
	 * @param value value to be performed the asked operation on
	 * @param Action action to be performed on the given value
	 * @return true if the action succeeded (as defined in the suitable Add,Delete and Contains methods)
	 * false if the action wasn't succeeded
	 */	
	private boolean PerformAction(int fixedhashValue, String value, int Action){
		int currentLocation = fixedhashValue;
		switch (Action){

		case DELETE:
			currentLocation = deleteCheck(currentLocation, value, fixedhashValue);
			if (currentLocation != NOT_FOUND){
				openHashList[currentLocation]= DELETED_REF;
				elemNum--;
				manageSize(DOWN);
				return true;
			}
			break;

		case CONTAINS:
			return containsCheck(currentLocation, value, fixedhashValue);

		case ADD:	
			if (PerformAction(fixedHashValue(value), value, CONTAINS) == false){
				currentLocation = addCheck(currentLocation, value,fixedhashValue);
				elemNum++;
				openHashList[currentLocation] = value;
				manageSize(UP);
				return true;
			}
			break;

		case DIRECT_ADD:
			currentLocation = addCheck(currentLocation, value,fixedhashValue);
			elemNum++;
			openHashList[currentLocation] = value;
			manageSize(UP);
			return true;			
		}
		return false;
	}
	/*
	 * performs the probing operation in order to find a suitable index place 
	 * according to the hashing table structure
	 */
	private int probe(String value, int i, int fixedHashValue){
		return (int) ((fixedHashValue + (i + (long)i * i) / 2)& capacityMinusOne);
	}	
	/*
	 * returns the first empty suitable index in the List, according to probe operation, in 
	 * order to place there a new value
	 */
	private int addCheck(int currentLocation, String value, int fixedhashValue){
		for(int i=0; i<capacity() ;i++){
			currentLocation = probe(value,i, fixedhashValue);
			// checks if cell is empty
			if (openHashList[currentLocation]==null || openHashList[currentLocation] == DELETED_REF )
				break;
		}
		return currentLocation;
	}
	/*
	 * returns the location of an value needed to be deleted in case it exists, or an indicator
	 * which indicates the value is not exist in case it is not  
	 */
	private int deleteCheck(int currentLocation, String value, int fixedhashValue){
		for(int i=0;openHashList[currentLocation] != null;i++){
			currentLocation = probe(value,i, fixedhashValue);
			// checks if cell is equal to asked value
			if (openHashList[currentLocation]!=null)
				if (openHashList[currentLocation].equals(value) && 
						openHashList[currentLocation]!=DELETED_REF )
					return currentLocation;
		}
		return NOT_FOUND;		
	}

	/*
	 * returns True iff the value exists in the table 
	 */
	private boolean containsCheck(int currentLocation, String value, int fixedhashValue){
		for(int i=0;openHashList[currentLocation] != null;i++){
			currentLocation = probe(value,i, fixedhashValue);
			// checks if cell is equal to asked value
			if (openHashList[currentLocation]!=null && openHashList[currentLocation]!=DELETED_REF)
				if (openHashList[currentLocation].equals(value))
					return true;
		}
		return false;
	}
	/*
	 * (non-Javadoc)
	 * @see SimpleHashSet#rehashTable(boolean)
	 */
	protected void rehashTable(boolean direction) {
		String[] copyStr = openHashList;
		changeSize(direction);				
		initialize ();
		transferValues(copyStr);		
	}
	/*
	 * transfers all values from old list to the new-sized-list
	 */
	private void transferValues (String[] oldList){
		for(int i=0;i<oldList.length;i++){
			if(oldList[i]!=null && oldList[i]!=DELETED_REF)
				PerformAction(fixedHashValue(oldList[i]), oldList[i], DIRECT_ADD);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see SimpleHashSet#initialize()
	 */
	protected void initialize (){
		openHashList = new  String[capacity()];
		elemNum=0;
	}		
}