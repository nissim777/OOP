package oop.ex5.filescript.order;
/**
 * A class which creates an Order instance from a given input line in the command file
 * 
 * @author black_knight
 *
 */
public class OrderFactory {
	private static final String SPLIT_CHAR = "#", REVERSE_EXP = "REVERSE";
	private static final int NAME_LOACTION = 0;
	public static Order createOrder (String parseLine) throws OrderException{

		Order myOrder;
		String[] expression = parseLine.split(SPLIT_CHAR);
		String orderType  = expression[NAME_LOACTION];

		switch (orderType){
		case "abs":
			myOrder = new AbsOrder();
			break;
		case "type":
			myOrder = new TypeOrder();
			break;
		case "size":
			myOrder = new SizeOrder();
			break;
		default:
			throw new OrderException();
		}
		// checking for REVERSE command
		if (expression[expression.length-1].equals(REVERSE_EXP))
			myOrder = new ReverseOrder(myOrder);
		return myOrder;
	}
}
