package oop.ex5.filescript.order;

import java.io.File;
/**
 * orders files by their name (absolute path)
 *  
 * @author black_knight
 *
 */
public class AbsOrder implements Order {

	/*
	 * (non-Javadoc)
	 * @see oop.ex5.filescript.order.Order#compare(java.io.File, java.io.File)
	 */
	public int compare(File file1, File file2) {
		return (file1.getAbsolutePath()).compareTo(file2.getAbsolutePath());
	}

}
