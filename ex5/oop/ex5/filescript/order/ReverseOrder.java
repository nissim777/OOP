package oop.ex5.filescript.order;

import java.io.File;
/**
 * an order which order files in the opposite direction to a given order, using delegation
 * @author black_knight
 *
 */
public class ReverseOrder implements Order {
	
	private static int REVERSE = -1;
	private Order orderToReverse;
	/**
	 * a constructor using object delegation in order to print file in reverse direction from what would 
	 * be printed by original order
	 * @param ordertoReverse - order to be reveresed
	 */
	public ReverseOrder (Order ordertoReverse){
		this.orderToReverse = ordertoReverse;
	}
	@Override
	public int compare(File file1, File file2) {
		return REVERSE*(orderToReverse.compare(file1, file2));
	}
}
