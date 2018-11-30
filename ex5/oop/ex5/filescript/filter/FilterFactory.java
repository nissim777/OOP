package oop.ex5.filescript.filter;
/**
 * A class in charge of creating filter instances, according to a given String
 * 
 * @author black_knight
 *
 */

public class FilterFactory {

	String filtername;
	final static String SPLIT_CHAR = "#";
	final static String NEG_EXP = "NOT";
	public static Filter createFilter(String parseLine) throws FilterException{
		
		String expression[] = parseLine.split(SPLIT_CHAR);
		String filterType = expression[0];
		Filter myfilter;

		switch (filterType){
		// Properties Filters
		case "writable":
			myfilter = new WritableFilter(expression[1]);
			break;

		case "executable":
			myfilter = new ExecutableFilter(expression[1]);
			break;

		case "hidden":
			myfilter = new HiddenFilter(expression[1]);
			break;		
			
		// TextFilters
		case "contains":
			myfilter = new ContainsFilter(expression[1]);
			break;

		case "file":
			myfilter = new FileFilter(expression[1]);
			break;

		case "prefix":
			myfilter = new PrefixFilter(expression[1]);
			break;	
			
		case "suffix":
			myfilter = new SuffixFilter(expression[1]);
			break;	

		// SizeFilters
		case "greater_than":
			myfilter = new GreaterThanFilter(Double.parseDouble(expression[1]));
			break;

		case "smaller_than":
			myfilter = new SmallerThanFilter(Double.parseDouble(expression[1]));
			break;

		case "between":
			myfilter = new BetweenFilter(Double.parseDouble(expression[1]),
					Double.parseDouble(expression[2]));
			break;
			
		// AllFilter
		case "all":
			myfilter = new AllFilter();
			break;
		case "":
			
		default:
			throw new FilterException();			

		}	
		// checking for NOT command
		if (expression[expression.length - 1].equals(NEG_EXP))
			myfilter = new NegFilter(myfilter);
		return myfilter;
	}
}
