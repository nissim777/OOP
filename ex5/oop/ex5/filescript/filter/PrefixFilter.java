package oop.ex5.filescript.filter;

/**
 * a file passes this filter iff its prefix is the given string
 * 
 * @author black_knight
 */
public class PrefixFilter extends TextFilter {
	public PrefixFilter (String inputString) throws FilterException{
		super(inputString);
	}

	public boolean isPass(String fileName) {
		return fileName.startsWith(inputString);
	}
}