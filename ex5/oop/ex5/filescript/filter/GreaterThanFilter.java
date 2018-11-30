package oop.ex5.filescript.filter;

import java.io.File;
/**
 * a file pass this filter iff its size in KB is greater than a given limit
 * 
 * @author black_knight
 *
 */
public class GreaterThanFilter extends SizeFilter {

	public GreaterThanFilter(double upperLimit) throws FilterException{
		if (upperLimit<0)
			throw new FilterException();
		this.upperLimit = upperLimit*CONVERT_INTO_BYTES;
	}
	public boolean isPass(File file){
		return (file.length()>upperLimit);		
	}
}
