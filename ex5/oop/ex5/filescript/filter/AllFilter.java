package oop.ex5.filescript.filter;

import java.io.File;
/**
 * a filter which pass all files
 * @author black_knight
 *
 */
public class AllFilter implements Filter {

	@Override
	public boolean isPass(File file) {

		return true;
	}
}
