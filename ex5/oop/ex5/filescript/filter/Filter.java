package oop.ex5.filescript.filter;

import java.io.File;
/**
 * An interface to be implemented by all filters, enforcing them to implement the isPass method
 * @author black_knight
 *
 */

public interface Filter {

	/**
	 * 
	 * @param file - file to filter
	 * @return true iff file passes filter
	 */
	public boolean isPass(File file);

}


