import oop.ex2.*;

import java.awt.Image;
/**
 * 	A class that represent an Human space ship. 
 *  this class extends SpaceShip.
 *  
 *  This ship is controlled by the user (using a keyboard).
 *  
 *  It's abilities are:
 *  his spaceship is controlled by the user. The keys used to control the
 *	spaceship are: left-arrow, right-arrow, up-arrow to turn left, right and accelerate. ’s’ to fire a shot.
 * ’d’ to turn on the shield. ’t’ to teleport.
 * 
 * @author black_knight
 *
 */
public class HumanShip extends SpaceShip {
	final int TURN_RIGHT = -1;
	final int TURN_LEFT = 1;
	final int NO_TURN = 0;
	protected Image image;

	// Private constructor to hide from javadoc. 
	// You may have a public constructor in this class if you wish.
	public  HumanShip() {
		super();
	}

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
		// performs actions by precedence order:
				
		// performing Acceleration (and maybe turn)
		if (game.getGUI().isUpPressed()) {
			if (game.getGUI().isLeftPressed()) {
				this.getPhysics().move(true, TURN_LEFT);
			}

			else if (game.getGUI().isRightPressed()) {	
				this.getPhysics().move(true,TURN_RIGHT);
			}
			else {
				this.getPhysics().move(true,NO_TURN);
			}
		}
		// performing only turn (or nothing)
		else {
			if (game.getGUI().isLeftPressed()) {
				this.getPhysics().move(false, TURN_LEFT);
			}

			else if (game.getGUI().isRightPressed()) {	
				this.getPhysics().move(false,TURN_RIGHT);
			}
			else {
				this.getPhysics().move(false,NO_TURN);
			}
		}	
		// performing teleport
		if (game.getGUI().isTeleportPressed()) {
			this.teleport();
		}
		// performing shield activation
		if (game.getGUI().isShieldsPressed()) {
			this.shieldOn();
		}
		// performing fire
		if (game.getGUI().isShotPressed()) {
			this.fire(game);
		}
	}

	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round.
	 * 
	 * @return the image of this ship.
	 */
	public Image getImage(){
		if (this.shieldActivated)
		{
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		}
		return GameGUI.SPACESHIP_IMAGE;
	}
}
