package oop.ex5.filescript.filter;

import java.io.File;
/**
 * file pass this filter iff it is executable
 * 
 * @author black_knight
 */

public class ExecutableFilter extends PropertiesFilter {

	public ExecutableFilter(String str) throws FilterException {
		super(str);
	}

	/*
	 * (non-Javadoc)
	 * @see oop.ex5.filescript.filter.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File file) {
		return (permissionCheck (file.canExecute()) );
	}
}
