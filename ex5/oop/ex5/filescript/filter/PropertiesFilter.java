package oop.ex5.filescript.filter;
/**
 * An abstract class for: executableFilter,HiddenFilter, WritableFilter
 * its inheriting sub-filters only check for the existence of property, while the 
 * helper method assists the filtering process 
 *   
 * @author black_knight
 *
 */
public abstract class PropertiesFilter implements Filter {

	private static final String POSITIVE = "YES", NEGATIVE = "NO";
	private static final boolean YES = true, NO = false;
	private boolean permission = NO;

	public PropertiesFilter(String str)throws FilterException{

		if (!str.equals(POSITIVE) && !str.equals(NEGATIVE))
			throw new FilterException();
		if (str.equals(POSITIVE))
			permission = YES;
	}
	/**
	 * method invoked by the corresponsing sub-filter, in order to determine the required permission asked
	 * @param hasProperty - if the file has the property
	 * @return true iff pass filter - according to requirements defined
	 */
	public boolean permissionCheck(boolean hasProperty ){
		return (permission == YES? hasProperty : !hasProperty);

	}

}
