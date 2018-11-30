package oop.ex5.filescript.filter;

import java.io.File;
/**
 * An abstract class for: file,contains,prefix,suffix Filters
 * Stores the given input and implements isPass by sending the input to the correspond inheriting class 
 *   
 * @author black_knight
 *
 */
public abstract class TextFilter implements Filter {
	protected String inputString;
	/**
	 * constructor stores the given input needed to be matched
	 * @param inputString - String to compare with file name
	 */
	public TextFilter (String inputString){
		this.inputString = inputString;
	}	
	/*
	 * (non-Javadoc)
	 * @see oop.ex5.filescript.filter.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File file){
		return isPass(file.getName());
	}
	/**
	 * an abstract method used in order to send input to correspond inheriting class
	 * and check for a required match
	 * 
	 * @param inputString - string to compare with file name
	 * @return true if passed filter, false otherwise	 * 
	 */
	
	abstract public boolean isPass(String inputString);

}