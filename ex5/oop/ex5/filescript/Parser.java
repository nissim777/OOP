package oop.ex5.filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import oop.ex5.filescript.filter.*;
import oop.ex5.filescript.order.*;
/**
 * 
 * This class is responsible for producing the list of Sections objects from a given file.
 * The class first validates that the lines in file are in the correct format, then creates the 
 * sections one by one and returns a section list. 
 * 
 * class is only consist of static methods, and its main method invoking the rest is the readFile method 
 * @author black_knight
 *
 */
public class Parser {

	private static final String ORDER_COMMAND = "ORDER", FILTER_COMMAND = "FILTER";
	static final int INITIALIZED = 0, FIRST = 0;
	private static final boolean ON = true, OFF = false;
	/**
	 * Parses a file into section objects, after validating correct format for file lines
	 * 
	 * @param commandFile file to parse
	 * @return an ArrayList of Section objects
	 * @throws FileNotFoundException 
	 * @throws ParserException
	 * @throws IndexOutOfBoundsException
	 */
	public static ArrayList<Section> readFile(File commandFile) throws FileNotFoundException,ParserException,
	IndexOutOfBoundsException{

		ArrayList<String> commandLineArray = parseIntoCollection (commandFile);
		validateFormat (commandLineArray);
		ArrayList<Section> myList = generateAllSections(commandLineArray);
		return myList;
	}
	
	// Turns a File into ArrayList of its lines
	private static ArrayList<String> parseIntoCollection(File commandFile) throws FileNotFoundException{

		ArrayList<String> commandFileList = new ArrayList<String>();
		try(Scanner sc = new Scanner(commandFile)){
			while (sc.hasNext()){
				commandFileList.add(sc.nextLine());
			}
		}
		return commandFileList;
	}

	// Validates command file lines are in the correct form - means consist of pairs of FILTER-ORDER
	private static void validateFormat(ArrayList<String> commandLineArray) throws ParserException, 
	IndexOutOfBoundsException {

		int sectionLine = FIRST;
		while (sectionLine < commandLineArray.size())
			sectionLine = validateSingleSection(sectionLine, commandLineArray);
	}
	
	// Validates a single Section is in the right form
	private static int validateSingleSection (int currentLine, ArrayList<String> commandLineArray) throws 
	ParserException, IndexOutOfBoundsException{
		currentLine = validateFilterSubSection(currentLine, commandLineArray);
		currentLine = validateOrderSubSection(currentLine, commandLineArray);
		return currentLine;
	}
	
	// Validates a single Filter sub-section is in the right form
	private static int validateFilterSubSection (int currentLine, ArrayList<String> commandLineArray)
			throws ParserException, IndexOutOfBoundsException{
		if (!commandLineArray.get(currentLine).equals(FILTER_COMMAND))
			throw new ParserException();
		currentLine++;
		if (!commandLineArray.get(currentLine).equals(ORDER_COMMAND))
			currentLine++;	
		return currentLine;
	}
	
	// Validates a single Order sub-section is in the right form
	private static int validateOrderSubSection (int currentLine, ArrayList<String> commandLineArray)
			throws ParserException, IndexOutOfBoundsException{
		if (!commandLineArray.get(currentLine).equals(ORDER_COMMAND))
			throw new ParserException();
		currentLine++;
		if(currentLine == commandLineArray.size())
			return currentLine;
		if (!commandLineArray.get(currentLine).equals(FILTER_COMMAND))
			currentLine++;	
		return currentLine;
	}	
	
	// Generates all the sections object from the file lines
	private static ArrayList<Section> generateAllSections(ArrayList<String> commandLineArray){

		ArrayList<Section> sectionList = new ArrayList<Section>();
		String filterLine,orderLine;

		int lineNum = 1;
		while (lineNum < commandLineArray.size())
		{	
			int filterLineNum = INITIALIZED, orderLineNum = INITIALIZED;
			// Get filter line and number of line 
			if(commandLineArray.get(lineNum).equals(ORDER_COMMAND))
				filterLine = Section.getDefaultFilterString();
			else {
				filterLine = commandLineArray.get(lineNum);
				filterLineNum = lineNum + 1;
			}	
			lineNum++;
			// Get order line and number of line
			if (commandLineArray.get(lineNum).equals(ORDER_COMMAND))
				lineNum++;
			if(lineNum == commandLineArray.size() || commandLineArray.get(lineNum).equals(FILTER_COMMAND) )
				orderLine = Section.getDefaultOrderString();
			else{ 
				orderLine = commandLineArray.get(lineNum);
				orderLineNum = lineNum + 1;
			}	
			// create section
			generateSingleSection(filterLine, orderLine,filterLineNum,orderLineNum);
			lineNum++;
		}
		return sectionList;
	}		
	
	/* Generates a single Section from a pair of filter, order lines. 
	 * also updates warnings in case illegal filter/order line
	 */
	private static Section generateSingleSection (String filterLine, String orderLine, int filterLineNum,
			int orderLineNum){
		Filter filter;
		Order order;
		boolean filterWarningFlag = OFF, orderWarningFlag = OFF;
		// generates filter
		try{
			filter = FilterFactory.createFilter(filterLine);
		}
		catch (FilterException e){
			filter = null;		
			filterWarningFlag = ON;
		}
		// generates order
		try{
			order = OrderFactory.createOrder(orderLine);
		}
		catch (OrderException e){
			order = null;
			orderWarningFlag = ON;
		}
		Section mySection = new Section(filter,order);
		// adding warnings
		if (filterWarningFlag)
			mySection.setFilterWarning(filterLineNum);
		if (orderWarningFlag)
			mySection.setOrderWarning(orderLineNum);
		return mySection;
	}

}
