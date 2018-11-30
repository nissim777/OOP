package oop.ex5.filescript.filter;

import java.io.File;
/**
 * a file pass this filter iff its size in KB is smaller than a given limit
 * 
 * @author black_knight
 *
 */
public class SmallerThanFilter extends SizeFilter {

	public SmallerThanFilter(double lowerLimit) throws FilterException{
		if (lowerLimit<0)
			throw new FilterException();
		this.lowerLimit = lowerLimit*CONVERT_INTO_BYTES;
	}
	public boolean isPass(File file){
		return (file.length()<lowerLimit);
	}
}
