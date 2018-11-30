package oop.ex5.filescript.filter;

import java.io.File;
/**
 * a file pass this filter iff its size in KB is between 2 given limits
 * 
 * @author black_knight
 *
 */
public class BetweenFilter extends SizeFilter {

	BetweenFilter(double lowerLimit, double upperLimit) throws FilterException{
		if (lowerLimit<0 || upperLimit<0 || lowerLimit>upperLimit)
			throw new FilterException();
		this.lowerLimit = lowerLimit*CONVERT_INTO_BYTES;
		this.upperLimit = upperLimit*CONVERT_INTO_BYTES;
	}
	public boolean isPass(File file){
		return (file.length()>=lowerLimit && file.length()<=upperLimit);
	}

}
