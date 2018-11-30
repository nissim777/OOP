
abstract public class SimpleHashSet implements SimpleSet {
	/**
	* @author black_knight
	*/
	protected static final boolean UP = true, DOWN = false;
	protected static final int RESIZE_FACTOR = 1;
	private static final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f, DEFAULT_LOWER_LOAD_FACTOR = 0.25f;
	private static final int DEAFULT_CAPACITY = 16;	

	protected int elemNum, capacityMinusOne; 
	private int capacity;
	private float lowerLoadFactor, upperLoadFactor;
	/**
	 * A default constructor. Constructs a new, empty table with default
	 * initial capacity (16), upper load factor (0.75) and lower load factor
	 * (0.25).
	 */
	public SimpleHashSet(){
		this (DEFAULT_UPPER_LOAD_FACTOR, DEFAULT_LOWER_LOAD_FACTOR);
	} 
	/**
	 * Constructs a new, empty table with the specified load factors, and the
	 * default initial capacity (16).
	 *
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
		this.capacity = DEAFULT_CAPACITY;	
		this.capacityMinusOne = capacity -1;
		this.elemNum = 0;
	}
	/**
	 * Data constructor - builds the hash set by adding the elements one by
	 * one. Duplicate values should be ignored. The new table has the default
	 * values of initial capacity (16), upper load factor (0.75), and lower
	 * load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public SimpleHashSet(java.lang.String[] data){
		this (DEFAULT_UPPER_LOAD_FACTOR, DEFAULT_LOWER_LOAD_FACTOR );
	}
	/**
	 * checks if it necessary to perform changes in the hash table size 
	 * @param direction - if resize the table up or down 
	 */
	protected void manageSize(boolean direction){
		float currentLoadFactor = (float)(elemNum)/(capacity);
		if (direction == UP){
			if (currentLoadFactor > upperLoadFactor)
				rehashTable(UP);
		}
		else{
			if (currentLoadFactor < lowerLoadFactor)

				rehashTable (DOWN);		
		}
	}
	/**
	 * manages all necessary changes involved in table resizing
	 * @param direction - if resize the table up or down 
	 */
	abstract protected void rehashTable (boolean direction);
	/**
	 * increases or decreases the size of the Hash table,according
	 * to the defined resize factor
	 * @param  direction - if resize the table up or down 
	 */
	protected void changeSize (boolean direction){
		int newCapacity = capacity();
		if (direction == UP)	
			newCapacity<<= RESIZE_FACTOR;
		else 
			newCapacity>>= RESIZE_FACTOR;
		changeCapacityValue (newCapacity);
	}

	/**
	 * updates the hash code of a given value to be suitable for the table hash  
	 * @param value - the value should be rehashed
	 * @return the updated hash value suitable to the Hash table
	 */
	protected int fixedHashValue(String value)
	{
		return value.hashCode()&capacityMinusOne;
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	abstract public boolean add(String newValue);

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	abstract public boolean contains(String searchVal);
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	abstract public boolean  delete(String toDelete);

	/**
	 * Changes the capacity values during the resizing process 
	 * @param newCapacity - the updated capacity value
	 */
	protected void changeCapacityValue(int newCapacity){
		this.capacity = newCapacity;
		this.capacityMinusOne = capacity -1;
	}
	/**
	 * Creates and initializes the new-sized table 
	 */	
	abstract protected void initialize ();
	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	public int capacity(){
		return capacity;
	}
	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	public int size(){
		return elemNum;
	}	
}