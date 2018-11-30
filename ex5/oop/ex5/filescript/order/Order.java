package oop.ex5.filescript.order;

import java.io.File;
import java.util.Comparator;
/**
 * an interface for Order instances to implement, also forcing them to implement Comparator, required in the
 * ordering process
 * 
 * @author black_knight
 *
 */
public interface Order extends Comparator<File>{
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(File file1, File file2);
}


