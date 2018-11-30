package oop.ex5.filescript.filter;
/**
 * super class for: Between, GreaterThan and SmallerThan filters
 * 
 * @author black_knight
 *
 */
public abstract class SizeFilter implements Filter{
		protected static final double CONVERT_INTO_BYTES = 1024;
		protected double lowerLimit, upperLimit;
		
	public SizeFilter() throws FilterException{
		
	};	
	
}