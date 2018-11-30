package oop.ex5.filescript.filter;

/**
 * a file passes this filter iff its suffix is the given string
 * 
 * @author black_knight
 */
public class SuffixFilter extends TextFilter {
	public SuffixFilter (String inputString) throws FilterException{
		super(inputString);
	}

	public boolean isPass(String fileName) {
		return fileName.endsWith(inputString);
	}
}