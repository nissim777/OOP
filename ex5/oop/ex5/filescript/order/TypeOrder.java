package oop.ex5.filescript.order;
import java.io.File;
/**
 * orders files by their type - their suffix (.txt  i.e) 
 *  
 * @author black_knight
 *
 */
public class TypeOrder implements Order {
	private static final String SPLIT_CHAR = ".";
	private static final int EQUAL = 0;
	
	/*
	 * returns suffix of a file name, which presents its type
	 */
	private String getType(String fileName){
		int splitIndex = fileName.lastIndexOf(SPLIT_CHAR);
		return fileName.substring(splitIndex+1);
	
	}
	
	/*
	 * (non-Javadoc)
	 * @see oop.ex5.filescript.order.Order#compare(java.io.File, java.io.File)
	 */
	public int compare(File file1, File file2) {
		
		String file1Type = getType(file1.getName());
		String file2Type = getType(file2.getName());
		// case both files are in the same type
		if (file1Type.compareTo(file2Type)==EQUAL){
			return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
		}
		return file1Type.compareTo(file2Type);
	}	
}
