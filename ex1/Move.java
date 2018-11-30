public class Move {

	private int moveRow;
	private int moveLeftIndex;
	private int moveRightIndex;

	public Move(int inRow, int inLeft, int inRight){
		this.moveRow = inRow;
		this.moveLeftIndex = inLeft;
		this.moveRightIndex = inRight;
	}

	public java.lang.String toString(){
		return (moveRow + ":" + moveLeftIndex + "-" + moveRightIndex);
	}

	public int getRow(){
		return moveRow;
	}

	public int getLeftBound(){
		return moveLeftIndex;
	}

	public int getRightBound(){
		return moveRightIndex;
	}
}