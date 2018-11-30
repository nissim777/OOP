package oop.ex5.filescript.filter;

/**
 * a file passes this filter iff its name matches the given string
 * 
 * @author black_knight
 */
public class FileFilter extends TextFilter {
	public FileFilter (String inputString) throws FilterException{
		super(inputString);
	}

	public boolean isPass(String fileName) {
		return fileName.equals(inputString);
	}

}