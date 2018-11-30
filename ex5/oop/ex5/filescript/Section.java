package oop.ex5.filescript;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import oop.ex5.filescript.filter.*;
import oop.ex5.filescript.order.*;
/**
 * 
 * A class for creating a section object, consists of pair of filter and order.
 * Also used for printing suitable files by this section
 * 
 * @author black_knight
 *
 */

public class Section {

	private static final int NONE = -1;
	private static final String DEFAULT_FILTER_STRING = "all", DEFAULT_ORDER_STRING = "abs";
	private static final Filter DEAFULT_FILTER = new AllFilter();
	private static final Order DEAFULT_ORDER = new AbsOrder();
	
	private Filter filter = null;
	private Order order = null;
	private int filterWarningLine, orderWarningLine;
	
	/**
	 * Constructor which gets filter and order as input
	 * @param filter 
	 * @param order 
	 */
	public Section(Filter filter, Order order){
		this.filter = filter;
		this.order = order;
		this.filterWarningLine = NONE;
		this.orderWarningLine = NONE;
	}
	/**
	 * Receives the filter line, in case a warning was detected. activated by Parser if required
	 * @param filterWarningLine
	 */
	void setFilterWarning(int filterWarningLine){
		this.filterWarningLine = filterWarningLine;
	}
	/**
	 * Receives the order line, in case a warning was detected. activated by Parser if required
	 * @param filterWarningLine
	 */
	void setOrderWarning(int orderWarningLine){
		this.orderWarningLine = orderWarningLine;
	}
	/**
	 * Prints warnings and suitable files according to filter-order exists in this section
	 * @param sectionNum - number of this section in the command file, begins with 1
	 * @param files - files to filter and print by order
	 */
	void printSection (int sectionNum,ArrayList<File> files){
		printWarnings (sectionNum);
		ArrayList<File> filteredFiles = filterFiles(files);
		printByOrder(filteredFiles);
	}
	
	// Prints warnings for this section, and updating illegal filters to the default filter
	private void printWarnings (int sectionNum){

		if (filter == null){
			System.out.println("Warning in line " + filterWarningLine);
			filter = DEAFULT_FILTER;
		}
		if (order == null){
			System.out.println("Warning in line " + orderWarningLine);
			order = DEAFULT_ORDER;
		}
	}
	
	// Filters the files according to the filter
	private ArrayList<File> filterFiles (ArrayList<File> files){
		ArrayList<File> filteredFiles = new ArrayList<>();
		for (File thisFile : files){
			if (filter.isPass(thisFile))
				filteredFiles.add(thisFile);
		}
		return filteredFiles;			
	}
	
	// Prints the files according to the order
	private void printByOrder(ArrayList<File> filteredFiles){
		Collections.sort(filteredFiles,order);
		for(File thisFile: filteredFiles)
			System.out.println(thisFile.getName());
	}
	
	/**
	 * Gives the suitable string required in order to create a default filter instance.
	 * activated by Parser in case of an empty line
	 * @return
	 */
	static String getDefaultFilterString(){
		return DEFAULT_FILTER_STRING;
	}
	
	/**
	 * Gives the suitable string required in order to create a default order instance.
	 * activated by Parser in case of an empty line
	 * @return
	 */
	static String getDefaultOrderString(){
		return DEFAULT_ORDER_STRING;
	}	
}

