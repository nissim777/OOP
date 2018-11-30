package oop.ex5.filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * A class which serves as the "manager" of the entire program, receiving a source directory which
 * contains files, and command file contains section of (filter-order) in order to filter and print 
 * the files in given directory.
 * the class ensures a valid inputs and prints "ERROR" otherwise
 * 
 * main classes invoked by this class: Parser, in order to read from command file and divide into sections
 *                                     Section,in order to print all files according to sections
 * 
 * @author black_knight
 *
 */
public class MyFileScript {

	/**
	 * @param args - two arguments represnting paths of:
	 * (1) 'sourcedir' (directory name) 
	 * (2) 'commandfile' (file contains the difference sections)
	 */

	private static final int SOURCE_DIRECTORY = 0, COMMAND_FILE = 1, NUM_OF_ARGUMENTS = 2;
	public static void main(String[] args) {

		validateArgNum (args);
		String sourceDirectoryPath = args[SOURCE_DIRECTORY];
		String commandFilePath = args[COMMAND_FILE];

		File commandFile = new File (commandFilePath);
		vaildateCommandFile(commandFile);
		File[] allFiles = new File(sourceDirectoryPath).listFiles();
		ArrayList<File> files = excludeDirectories(allFiles);
		
		try{
			ArrayList<Section> myList= new ArrayList<>();
			myList = Parser.readFile(commandFile);
			printAllSections(myList, files);			 
		}
		catch (FileNotFoundException e){
			System.err.print("ERROR");
		}
		catch (ParserException e){
			System.err.print("ERROR");
		}
		catch (IndexOutOfBoundsException e){
			System.err.print("ERROR");
		}		
	}

	// Validates correct number of arguments
	private static void validateArgNum (String[] args) {
		if (args.length!=NUM_OF_ARGUMENTS)
			System.err.print("Error");
	}
	// Validates command file is exists and in correct form 
	private static void vaildateCommandFile (File commandFile){

		if (!commandFile.exists() || !commandFile.isFile() || !commandFile.canRead())
			System.err.print("ERROR");

	}	
	// Excluding directories in order to leave only files to filter
	private static ArrayList<File> excludeDirectories(File[] allFiles){		

		ArrayList<File> files = new ArrayList<>();
		for(File file : allFiles){
			if(file.isFile())
				files.add(file);
		}
		return files;
	}	

	/**
	 * Print files according to sections
	 * @param sectionList - list of sections
	 * @param files - all the files in the source folder
	 */
	private static void printAllSections(ArrayList<Section> sectionList, ArrayList<File> files){
		int sectionNum = 0;
		for(Section mySection : sectionList){
			sectionNum++;
			mySection.printSection(sectionNum, files);
		}
	}
}
