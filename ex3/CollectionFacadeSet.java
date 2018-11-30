import java.util.Collection;
public class CollectionFacadeSet implements SimpleSet {

	/**
	 * Created by black_knight
	 */
	private Collection<String> collection;
	/**
	 * Creates a new facade wrapping the specified collection.
	 * @param collection - The Collection to wrap.
	 */
	public CollectionFacadeSet(Collection<String> collection) {
		this.collection = collection;
	}
	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "class: "+ collection.getClass() + " size: " +
				collection.size()+" @"+collection.hashCode();
	}
	/*
	 * @see SimpleSet#add(java.lang.String)
	 */
	@Override
	public boolean add(String newValue) {
		if (!contains(newValue))
			return collection.add(newValue);
		return false;
	}
	/*
	 * @see SimpleSet#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String searchVal) {
		return collection.contains(searchVal);
	}
	/*
	 * @see SimpleSet#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String toDelete) {
		return collection.remove(toDelete);
	}
	/*
	 * @see SimpleSet#size()
	 */	
	@Override
	public int size() {
		return collection.size();
	}
}
