import oop.ex2.*;
import java.util.Random;
import java.awt.Image;

/**
 * 	A class that represents a Drunkard space ship. this class extends FollowerShip.
 *  It's abilities are:
 *  
 *  The drankardShip moves like crazy in great speed all over the screen. that's is't clear to 
 *  anyone where and why, but the only thing that is sure that you better kill him as soon as possible,
 *  because every round there is a chance of 1:100 for appearnce of an powerfull invisble Ally, 
 *  which cannot be seen and just shots everyone. 
 *  
 *  The invisble Ally is indestructable and the only way to kill him is by killing the DrunkardShip itself. 
 *  The problem is.. that every shot of the Invisble Ally towards the DrunkardShip increases his life in
 *  2 points.  

 * @author black_knight
 *
 */
public class Drunkard extends FollowerShip {

	SpaceShip invisbleShip;
	Random random = new Random();
	private static final int ALLY_CHANCE = 100;
	private static final int CANNOT_DIE = 100, LIFE_BOOST = 3;
	private static boolean invisbleExist = false;
	/**
	 * Does the actions of this ship for this round. 
	 * This is called once per round by the SpaceWars game driver.
	 * 
	 * @param game the game object to which this ship belongs.
	 * 
	 * The ship is controlled by the user.
	 */
	public void doAction(SpaceWars game) {
		super.doAction(game);
		this.getPhysics().move(false,NO_TURN);
		// chance of the invisble ally appearnce
		if (random.nextInt(ALLY_CHANCE) == (ALLY_CHANCE-1) && (invisbleExist == false)){
			invisbleShip = new AggressiveShip();	
			invisbleExist = true;
		}	
		if (invisbleExist){
			invisbleAllyAction(game);
		}

	}
	/*
	 * implements the actions of the  invisble ally. is a powerfull ship, that has chance of 1:100 every 
	 * round 
	 * the invisble ally cannot die unless you kill the Drunkard ship itself. 
	 * also, the shots of the invislbe ally into the Drunkardship increases his health by 2 
	 */
	private void invisbleAllyAction (SpaceWars game)
	{
		invisbleShip.health= CANNOT_DIE;
		int drunkardCurrentHealth = health;
		invisbleShip.doAction(game);
		if (drunkardCurrentHealth != health)
			health += LIFE_BOOST;
	}
}