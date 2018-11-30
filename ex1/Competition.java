import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition {
	private Player player1, player2;
	private boolean displayMessage;
	private static int player1score = 0, player2score = 0;
	public Competition (Player player1, Player player2, boolean displayMessage)
	{
		this.player1 = player1;
		this.player2 = player2;
		this.displayMessage = displayMessage;
	}
	public int getPlayerScore(int playerPosition){
		if (playerPosition == 1)
			return player1score;
		if (playerPosition == 2)
			return player2score;
		return -1;
	}
	public void playMultipleRounds(int numberOfRounds){
	}
	/*
	 * Returns the integer representing the type of the player; returns -1 on bad
	 * input.
	 */
	private static int parsePlayerType(String[] args,int index){
		try{
			return Integer.parseInt(args[index]);
		} catch (Exception E){
			return -1;
		}
	}
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args){
		try{
			return Integer.parseInt(args[2]);
		} catch (Exception E){
			return -1;
		}
	}

	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {

		int p1Type = parsePlayerType(args,0);
		int p2Type = parsePlayerType(args,1);
		int numGames = parseNumberOfGames(args);
		int roundCounter = 0;
		boolean displayMessage = false;

		Scanner scanner = new Scanner(System.in);
		Player player1 = new Player (p1Type,1,scanner);
		Player player2 = new Player (p2Type,2,scanner);

		if (player1.getPlayerType() == Player.HUMAN || player2.getPlayerType() == Player.HUMAN )
			displayMessage = true;
		Competition nimCompetition = new Competition(player1, player2, displayMessage);
		System.out.println("Starting a Nim competition of " + numGames + " rounds between a " + player1.getTypeName()+ 
				" player and a " + player2.getTypeName() + " player.");

		// performs a sequence of Nim rounds
		while (roundCounter<numGames){
			Board board = new Board ();
			Player currentPlayer = player1;
			if (displayMessage == true)
				System.out.println("Welcome to the sticks game!");
			currentPlayer = player1;
			// performs a single round
			while (board.getNumberOfUnmarkedSticks()>0){
				if (displayMessage == true)
					System.out.println("Player " + currentPlayer.getPlayerId() + ", it is now your turn!");
				// performs a legal move by current player
				Move playerMove;
				int moveAttempt;
				do {
					playerMove = currentPlayer.produceMove(board);	
					moveAttempt = board.markStickSequence(playerMove);
					// checks if the move choosen by player is legal
					if (moveAttempt != 1 && displayMessage == true)
						System.out.println("Invalid move.  Enter another:");
				}
				while (moveAttempt != 1);	

				if (displayMessage == true)
					System.out.println("Player " + currentPlayer.getPlayerId() + " made the move:  " + 
							playerMove.toString());				
				if (currentPlayer.getPlayerId() == 1)
					currentPlayer = player2;
				else
					currentPlayer = player1;
			}
			if (displayMessage == true)
				System.out.println("Player " + currentPlayer.getPlayerId() + " won!");
			if (currentPlayer.getPlayerId() == 1)
				player1score ++;
			else 
				player2score ++;
			roundCounter ++;
		}	
		System.out.println("The results are " + player1score + ":" + player2score);
	}
}
