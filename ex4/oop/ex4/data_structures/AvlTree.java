package oop.ex4.data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* an implement of an AVL tree, supporting main operations of add, contains, delete and iteration.
*
* @author black_knight
* 
*/

public class AvlTree implements Iterable<Integer> {
	static final boolean RIGHT = true, LEFT = false, PLACED = true; 
	static final int VIOLATION_ON_LEFT = 2, VIOLATION_ON_RIGHT = -2, LEFT_INCLINATION = 1, 
			RIGHT_INCLINATION= -1,RR = 4, RL = 3, LL = 2, LR = 1, NOT_FOUND = -1;

	private Node root;
	private int elemNum;

	/**
	 * The default constructor.
	 */
	public AvlTree() {
		root = null;
		elemNum=0;
	}

	/**
	 * A constructor that builds the tree by adding the elements in the input array one by
	 * one. If a value appears more than once in the list, only the first appearance is added.
	 *
	 * @param data the values to add to tree.
	 */
	public AvlTree(int[] data) {
		for (int current : data)
			add(current);
	}

	/**
	 * A copy constructor that creates a deep copy of the given AvlTree. This means that for
	 * every node or any other internal object of the given tree, a new, identical object, is
	 * instantiated for the new tree (the internal object is not simply referenced from it). The
	 * new tree must contain all the values of the given tree, but not necessarily in the same
	 * structure.
	 *
	 * @param avlTree an AVL tree.
	 */
	public AvlTree(AvlTree avlTree) {
		Iterator<Integer> treeIterator = avlTree.iterator();
		while (treeIterator.hasNext()) {
			add(treeIterator.next());
		}		
	}

	/**
	 * Calculates the minimum number of nodes in an AVL tree of height h.
	 *
	 * @param h the height of the tree (a non−negative number) in question.
	 * @return the minimum number of nodes in an AVL tree of the given height.
	 */
	public static int findMinNodes(int h) {
		if (h==0)
			return 1;
		if (h==-1)
			return 0;
		return 1+ findMinNodes(h-1)+ findMinNodes(h-2);	
	}

	/**
	 * Add a new node with the given key to the tree.
	 *
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully added,
	 * false otherwise.
	 */
	public boolean add(int newValue) {

		Node newNode = new Node (newValue);
		// case tree is empty
		if (root == null){
			root = newNode;
			elemNum++;
			return true;
		}	
		else
			return recursiveAdd(newNode,root);			
	}

	/*
	 * Recursive method who finds place to add the new Value according to BST insertion algoritem, after
	 * Validating value doesn't already exists.
	 * Also checks for violation to AVL structures after a successful insertion, and activates corresponding 
	 * helper methods in order to solve it.
	 * 
	 * returns: true if the value to add is not already in the tree and it was successfully added,
	 * false otherwise.
	 */
	private boolean recursiveAdd (Node newNode,Node parentNode)
	{
		//If value exists
		if (newNode.value == parentNode.value)
			return false;
		// If greater than current node
		if (newNode.value > parentNode.value){
			// actual insertion in case found suitable place
			if (parentNode.right == null){
				actualAdd (newNode, parentNode, RIGHT);
				return true;
			}
			// recursive call to deeper height of the tree
			else{
				boolean returnValue;
				returnValue = recursiveAdd (newNode, parentNode.right);
				if (returnValue == PLACED){
					// checking for violation (AFTER THE PLACEMENT)
					int balanceFactor = (checkBalance(parentNode));
					if (balanceFactor == VIOLATION_ON_LEFT || balanceFactor == VIOLATION_ON_RIGHT){
						violationAlert(balanceFactor, parentNode);
					}
				}
				return returnValue;
			}
		}
		// If smaller than current node 
		else{
			// actual insertion in case found suitable place
			if (parentNode.left == null){
				actualAdd (newNode, parentNode, LEFT);
				return true;
			}
			// recursive call to deeper height of the tree
			else{
				boolean returnValue;
				returnValue = recursiveAdd (newNode, parentNode.left);
				if (returnValue == PLACED){
					// checking for violation (AFTER THE PLACEMENT)
					int balanceFactor = (checkBalance(parentNode));
					if (balanceFactor == VIOLATION_ON_LEFT || balanceFactor == VIOLATION_ON_RIGHT){
						violationAlert(balanceFactor, parentNode);
					}
				}
				return returnValue;
			}
		}
	}
	
	/*
	 * performs actual operations required in order to add a new value
	 */	
	private void actualAdd (Node newNode, Node parentNode, boolean direction){
	if (direction == RIGHT)
		parentNode.right = newNode;
	else
		parentNode.left = newNode;
	newNode.parent = parentNode;
	elemNum ++;
	}

	/*
	 * Checks which type of violation was exactly occurred
	 */
	private void violationAlert(int balanceFactor, Node parentNode) {
		if (balanceFactor == VIOLATION_ON_RIGHT){
			if (checkBalance(parentNode.right) == LEFT_INCLINATION)
				manageViolation (RL, parentNode);
			else 
				manageViolation (RR, parentNode);
		}
		else {
			if (checkBalance(parentNode.left) == RIGHT_INCLINATION)
				manageViolation (LR, parentNode);
			else
				manageViolation (LL, parentNode);
		}

	}

	/*
	 * Performs suitable actions in order to solve violation
	 */
	private void manageViolation (int violationType, Node parentNode ){
		switch (violationType) {
		case RR:
			rotateLeft (parentNode.right);
			break;	
		case RL:
			rotateRight (parentNode.right.left);
			rotateLeft (parentNode.right);
			break;
		case LR:
			rotateLeft (parentNode.left.right);
			rotateRight(parentNode.left);
			break;			
		case LL: 
			rotateRight (parentNode.left);
		}
	}

	/*
	 * Performs rotation to the left
	 * pivotNode - the node to be ascend during rotation proccess
	 */
	private void rotateLeft (Node pivotNode)
	{
		if (pivotNode.parent == root)
			root = pivotNode;			
		// case pivot has left son
		if (pivotNode.left != null){
			pivotNode.parent.right = pivotNode.left;
			pivotNode.left.parent = pivotNode.parent;
		}
		else 
			pivotNode.parent.right = null;
		// changing left of pivot to be his current parent
		pivotNode.left = pivotNode.parent;
		// updating parent of Pivot, in case there supposed to be any  
		if (pivotNode.parent.parent != null){
			pivotNode.parent = pivotNode.parent.parent;
			// update also values for new parent
			if (pivotNode.parent.left != null){
				if (pivotNode.parent.left == pivotNode.left)
					pivotNode.parent.left = pivotNode;
				else
					pivotNode.parent.right = pivotNode;	
			}		
		}
		else
			pivotNode.parent = null;
		// changing the values for old parent of Pivot 
		pivotNode.left.parent = pivotNode;
	}

	/*
	 * Performs rotation to the right
	 * pivotNode - the node to be ascend during rotation proccess
	 */
	private void rotateRight (Node pivotNode)
	{
		if (pivotNode.parent == root)
			root = pivotNode;			
		// case pivot has left son
		if (pivotNode.right != null){
			pivotNode.parent.left = pivotNode.right;
			pivotNode.right.parent = pivotNode.parent;
		}
		else 
			pivotNode.parent.left = null;
		// changing left of pivot to be his current parent
		pivotNode.right = pivotNode.parent;
		// updating parent of Pivot, in case there supposed to be any  
		if (pivotNode.parent.parent != null){
			pivotNode.parent = pivotNode.parent.parent;
			// update also values for new parent
			if (pivotNode.parent.right == pivotNode.right)
				pivotNode.parent.right = pivotNode;
			else
				pivotNode.parent.left = pivotNode;	
		}		
		else
			pivotNode.parent = null;
		// changing the values for old parent of Pivot 
		pivotNode.right.parent = pivotNode;
	}

	/*
	 * Checking balance in a given Node, according to AVL tree rules, by comparing the heights of its 
	 * two sons. 
	 * returns: value between 2 to -2, when the number indicates the gap of depths between sons 
	 * relatively to left. 
	 */
	private int checkBalance(Node node){
		if (node.right == null)
			return 1+ getNodeDepth(node.left);
		if (node.left == null)
			return - (1+ getNodeDepth(node.right));
		return getNodeDepth(node.left)- getNodeDepth (node.right);
	}

	/*
	 * returns the depth of a given node - the maximum distance between a node to a leaf in the tree  
	 */
	private int getNodeDepth (Node node){
		if (node.right == null && node.left == null)
			return 0;
		else if (node.right == null)
			return 1 + getNodeDepth (node.left);
		else if (node.left == null)
			return 1 +getNodeDepth(node.right);
		return 1 + Math.max(getNodeDepth(node.right), getNodeDepth(node.left));
	}

	/**
	 * Check whether the tree contains the given input value.
	 *
	 * @param searchVal the value to search for.
	 * @return the depth of the node (0 for the root) with the given value if it was found in
	 * the tree, −1 otherwise.
	 */
	public int contains(int searchVal) {
		Node currentNode = root;
		for (int depth=0; currentNode != null; depth ++){
			if (currentNode.value == searchVal)
				return depth;
			else if (currentNode.value < searchVal){
				currentNode = currentNode.right;
			}	
			else				
				currentNode = currentNode.left;
		}
		return NOT_FOUND;	
	}

	/**
	 * Removes the node with the given value from the tree, if it exists.
	 *
	 * @param toDelete the value to remove from the tree.
	 * @return true if the given value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete) {
		Node searchNode = searchNode(toDelete);
		if (searchNode == null)
			return false;
		manageDelete(searchNode);
		return true;
	}

	/*
	 * Searches for a node with a given value.
	 * Returns the node with the value in case it exists, or Null in case not.
	 */
	private Node searchNode (int searchVal){
		Node currentNode = root;
		while (currentNode != null){
			if (currentNode.value == searchVal)
				return currentNode;
			else if (currentNode.value < searchVal){
				currentNode = currentNode.right;
			}	
			else				
				currentNode = currentNode.left;
		}
		return null;
	}
	
	/*
	 * Activates suitable method in order to delete the given node: 
	 * the method complexDelete in case the node has two sons, or simpleDeleteotherwise
	 */
	private void manageDelete (Node n)
	{
		// storing the new Node, was created in the same place of the old node, for later violation check
		Node balanceLoaction;
		// if deleted Node is a leaf or has only one son
		if (n.left == null || n.right == null)
			balanceLoaction = simpleDelete(n);
		// if deleted Node has two sons 
		else
			balanceLoaction = complexDelete(n);
		// check for balance violations in Tree after deletion operation
		if (balanceLoaction!=null){
			recursiveBalanceCheck(balanceLoaction);
		}
		elemNum--;
	}
	
	/*
	 * recursive balance checking for violations in tree, ascending from given location until reaching root
	 */
	private void recursiveBalanceCheck (Node location){
		int balanceFactor = (checkBalance(location));
		if (balanceFactor == VIOLATION_ON_LEFT || balanceFactor == VIOLATION_ON_RIGHT)
			violationAlert(balanceFactor, location);
		if (location.parent!=null){
			recursiveBalanceCheck(location.parent);	
		}
	}
	
	/*
	 * performing delete operation in case the node has no/only one son 
	 */
	private Node simpleDelete (Node n){
		// case deleted value is a leaf
		if (n.left == null && n.right == null){
			// case root
			if (n == root){
				root = null;
				return null;
			}	
			// case is left son
			else if (n.parent.left == n)
				n.parent.left = null;
			// case is right son
			else
				n.parent.right = null;	
		}
		// case deleted value has only right son
		else if (n.right != null ){
			// case root
			if (n == root){
				root = n.right;
				n.right.parent = null;
				return null;
			}	
			// case is left son
			else if (n.parent.left == n){
				n.parent.left = n.right;
				n.right.parent = n.parent;
			}
			// case is right son
			else{
				n.parent.right = n.right;	
				n.right.parent = n.parent;
			}
		}
		// case deleted value has only left son
		else{ 
			// case root
			if (n == root){
				root = n.left;
				n.left.parent = null;
				return null;
			}
			// case is left son
			else if (n.parent.left == n){
				n.parent.left = n.left;
				n.left.parent = n.parent;
			}
			// case is right son 
			else{
				n.parent.right = n.left;	
				n.left.parent = n.parent;
			}
		}
		return n.parent;
	}

	/*
	 * performing delete operation in case the node has two sons
	 */
	private Node complexDelete (Node n){
		Node successor = findsuccessor(n);
		return switchSuccessor (n,successor);
	}
	
	/*
	 * finds successor in the sub tree on deleted node, in order to replace him 
	 */
	private Node findsuccessor (Node n){
		Node successor = n.right;
		while (successor.left!=null)
			successor = successor.left;
		return successor;
	}
	
	/*
	 * performs the process of switching deleted node by its successor. 
	 */
	private Node switchSuccessor(Node n, Node successor) {
		// case successor is right after deleted Node
		if (successor == n.right){
			successor.left = n.left;
			n.left.parent = successor;
		}
		// case successor is not immediately on right to deleted Node( also means he is a lefty son)
		else {
			// case successor has (right) son
			if (successor.right!=null){
				successor.parent.left = successor.right;
				successor.right.parent = successor.parent;
			}
			else{
				successor.parent.left = null;
			}
			successor.right = n.right;
			successor.left = n.left;
			n.right.parent = successor;
			n.left.parent = successor;
		}
		// case deleted value was root
		if (n == root){
			root = successor;
			successor.parent = null;
		}
		// changing parent values in case deleted value was not root 
		else{
			// case deleted value was right son
			if(n.parent.right == n)
				n.parent.right = successor;
			// case deleted value was left son 
			else
				n.parent.left = successor;
			successor.parent = n.parent;
		}
		return successor;
	}

	/**
	 *
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		return elemNum;
	}
	
	/*
	 * nested class represents the nodes which the tree is consisted of
	 */
	private class Node {
		public int value;
		public Node right, left, parent;

		public Node(){
			this.right = null;
			this.left = null;
			this.parent = null;
		}
		public Node(int value){
			this();
			this.value = value;
		}		
	}	

	/**
	 * @return an iterator on the Avl Tree. The returned iterator iterates over the tree nodes
	 * in an ascending order, and does NOT implement the remove() method.
	 * comment: using a local class
	 */
	@Override
	public Iterator<Integer> iterator() {
		// local class for the Iterator 
		class TreeIterator implements Iterator<Integer>{

			Node currentNode;
			// indicator for the inital case, when next should return the inital value
			boolean initialFlag;
			static final boolean ON = true, OFF = false;

			public TreeIterator() {
				currentNode = findMin(root);
				initialFlag = ON;
			}
			
			/*
			 * (non-Javadoc)
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				if (initialFlag == ON && currentNode!= null)
					return true;
				if (successor(currentNode) != null)
					return true;
				return false;
			}
			
			/*
			 * (non-Javadoc)
			 * @see java.util.Iterator#next()
			 */
			@Override
			public Integer next() throws NoSuchElementException{
				if (!hasNext())
					throw new NoSuchElementException();
				if (initialFlag == ON ){
					initialFlag = OFF;
					return currentNode.value;
				}	
				currentNode = successor(currentNode);
				return currentNode.value;
			}
			
			/*
			 * (non-Javadoc)
			 * @see java.util.Iterator#remove()
			 */
			@Override
			public void remove() throws UnsupportedOperationException{
				throw new UnsupportedOperationException();
			}
			
			/*
			 * finds minimal value in a given subtree, which currentNode is its root
			 */
			private Node findMin(Node currentNode) {
				if (root==null)
					return null;
				while (currentNode.left!= null)
					currentNode = currentNode.left;
				return currentNode;	
			}
			
			/*
			 * finds successor of a given node, in case exists
			 * returns: successor Node, or null in case doesn't exist
			 */
			private Node successor(Node n) {
				// case Empty tree or finished already all iterations
				if (n == null)
					return null;
				Node successor;
				// case has right son
				if (n.right != null)
					successor = findMin (n.right);			
				else{
					successor = n.parent;
					// climbing up until encountering parent which current is his left son 
					while (successor != null && successor.right == n){
						n = successor;
						successor = successor.parent;
						if (successor == null)
							return null;
					}
				}	
				return successor;
			}
		}
		return new TreeIterator(); 

	}
}	
