package oop.ex5.filescript.filter;

import java.io.File;
/**
 * a dercorating class used to return all the opposite files filtered by certain filter 
 * @author black_knight
 *
 */
public class NegFilter implements Filter {
	Filter filter;
	/**
	 * a constructor using delegation in order to perform the Neg operation 
	 * @param inputFilter
	 */
	public  NegFilter(Filter inputFilter){
		this.filter = inputFilter;
	}
	/**
	 * return true iff the input filter will return false for this file 
	 */
	public boolean isPass(File file) {
		return !(filter.isPass(file));
	}

}
