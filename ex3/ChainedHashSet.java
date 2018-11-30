import java.util.ListIterator;

public class ChainedHashSet extends SimpleHashSet {
	/**
	* @author black_knight
	*/
	private LinkedListWrapper bucketList[]; 

	/**
	 * A default constructor. Constructs a new, empty table with default
	 * initial capacity (16), upper load factor (0.75) and lower load factor
	 * (0.25).
	 */
	public ChainedHashSet(){
		super();
		creatNewList();
	}
	/**
	 * Constructs a new, empty table with the specified load factors, and the
	 * default initial capacity (16).
	 *
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor){
		super(upperLoadFactor, lowerLoadFactor);
		creatNewList();
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by
	 * one. Duplicate values should be ignored. The new table has the default
	 * values of initial capacity (16), upper load factor (0.75), and lower
	 * load factor (0.25).
	 * @param data - Values to add to the set.
	 */
	public ChainedHashSet(java.lang.String[] data){
		super (data);
		creatNewList();
		for (int i=0;i<data.length;i++)
			add(data[i]);
	}
	/*
	 * Creates a new ChainedHashTable and initializes its cells 
	 */
	private void creatNewList(){
		bucketList = new LinkedListWrapper[capacity()];
		initialize ();
	}
	
	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public boolean add(java.lang.String newValue){
		// checks for duplicates
		if (contains(newValue))
			return false;	
		// adds the Element
		directAdd(newValue);	
		manageSize(UP);
		return true;
	}

	// adds elements without checking for duplicates
	private void directAdd (java.lang.String newValue)
	{
		elemNum++;
		int hashValue = fixedHashValue(newValue);
		bucketList[hashValue].getList().add(newValue);
	}
	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(java.lang.String searchVal){
		int hashValue = fixedHashValue(searchVal);
		return (bucketList[hashValue].getList().contains(searchVal)); 
	}
	/*
	 * (non-Javadoc)
	 * @see SimpleHashSet#rehashTable(boolean)
	 */
	protected void rehashTable (boolean direction){
		LinkedListWrapper[] copyBucketList = bucketList;
		changeSize(direction);		
		bucketList = new LinkedListWrapper[capacity()];
		transferValues (copyBucketList);	
	}
	/*
	 * transfers all values from old list to the new-sized-list
	 */
	private void transferValues (LinkedListWrapper [] copyBucketList)
	{
		initialize ();
		for (int i=0; i<copyBucketList.length; i++)
		{
			ListIterator<String> listIterator = copyBucketList[i].getList().listIterator();
			while (listIterator.hasNext()) {
				directAdd(listIterator.next());
			}
		}		
	}
	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean delete(java.lang.String toDelete){
		int hashValue = fixedHashValue(toDelete);
		if (bucketList[hashValue].getList().remove (toDelete)== true){
			elemNum--;
			manageSize(DOWN);
			return true;
		}
		return false;
	}
	/*
	 * (non-Javadoc)
	 * @see SimpleHashSet#initialize()
	 */
	protected void initialize (){
		for (int i=0;i<bucketList.length;i++)
			bucketList[i] = new LinkedListWrapper();
		elemNum=0;
	}
}
