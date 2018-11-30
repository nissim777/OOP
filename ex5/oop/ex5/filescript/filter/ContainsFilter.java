package oop.ex5.filescript.filter;

/**
 *  a file passes this filter iff its name contains the given string
 *  
 * @author black_knight
 */
public class ContainsFilter extends TextFilter {

	public ContainsFilter (String inputString){
		super(inputString);
	}

	public boolean isPass(String fileName) {
		return fileName.contains(inputString);
	}
}