package oop.ex5.filescript.order;

import java.io.File;
/**
 * orders files by their size
 *  
 * @author black_knight
 *
 */
public class SizeOrder implements Order {

	private static final int FIRST_GREATER = 1, SECOND_GREATER = -1;

	/*
	 * (non-Javadoc)
	 * @see oop.ex5.filescript.order.Order#compare(java.io.File, java.io.File)
	 */
	public int compare(File file1, File file2) {
		// case both files are in the same size
		if (file1.length() == file2.length()){
			return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
		}
		if (file1.length()>file2.length())
			return FIRST_GREATER;
		else
			return SECOND_GREATER;
	}
	
}
