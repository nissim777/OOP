package oop.ex5.filescript.filter;

import java.io.File;
/**
 * file pass this filter iff it is hidden
 * 
 * @author black_knight
 *
 */
public class HiddenFilter extends PropertiesFilter {

	public HiddenFilter(String str) throws FilterException {
		super(str);
	}

	/*
	 * (non-Javadoc)
	 * @see oop.ex5.filescript.filter.Filter#isPass(java.io.File)
	 */
	public boolean isPass(File file) {
		return (permissionCheck (file.isHidden()) );
	}
}