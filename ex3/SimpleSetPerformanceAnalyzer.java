import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer{
	/**
	 * Created by black_knight
	 */
	public static String[] data1 = Ex3Utils.file2array("src/data1.txt");
	public static String[] data2 = Ex3Utils.file2array("src/data2.txt");
	public static SimpleSet[] collections1 = new SimpleSet[5], collections2 = new SimpleSet[5];
	private static final long  MILI_TIME = 1000000;
	private static final String FILE1_NAME = "data1", FILE2_NAME = "data2";
	private static final String WORD1 = "hi", WORD2 = "-13170890158", WORD3 = "23";

	public static void main(String[] args) {

		Initialize(collections1);
		Initialize(collections2);
		// performs checks for collection1
		for(SimpleSet set : collections1){
			insertData(set,data1, FILE1_NAME);
			containsWord(set, WORD1,FILE1_NAME);
			containsWord(set, WORD2,FILE1_NAME);
		}

		// performs checks for collection2
		for(SimpleSet set : collections2){
			insertData(set,data2, FILE2_NAME);
			containsWord(set, WORD1,FILE1_NAME);
			containsWord(set, WORD3,FILE1_NAME);
		}
	}

	/* Initializes the collectionSet with the 5 different collections types: 
	ChainedHashSet, OpenHashSet, TreeSet, LinkedList and HashSet */
	private static void Initialize(SimpleSet[] collectionSet) {
		collectionSet[0] = new ChainedHashSet();
		collectionSet[1] = new OpenHashSet();
		collectionSet[2] = new CollectionFacadeSet(new TreeSet<String>());
		collectionSet[3] = new CollectionFacadeSet(new LinkedList<String>());
		collectionSet[4] = new CollectionFacadeSet(new HashSet<String>());	
	}
	/*
	 * measures the time for insertion of elements in a given data file, in milliseconds
	 */
	private static void insertData(SimpleSet set, String[] dataFile, String dataFileName) {

		long beginningTime = System.nanoTime();
		for (int i=0; i<dataFile.length; i++){
			set.add(dataFile[i]);
		}
		long timeDifference = (System.nanoTime() - beginningTime);
		long millisec = timeDifference / MILI_TIME;

		System.out.println("insertion process for " + dataFileName +" into "+set+" took: "+millisec);

	}
	/*
	 * measures the time needed for performing a check in order to determine if
	 * a specified set contains a given word
	 */
	private static void containsWord(SimpleSet set, String word, String dataFileName) {

		long beginningTime = System.nanoTime();
		boolean isContain = set.contains(word);
		long timeDifference = (System.nanoTime() - beginningTime);
		System.out.println(set+": " +dataFileName +"contains " +  word +" is "+isContain+". Messaure time: "+ 
				timeDifference);
	}
}
