import java.awt.Image;

import oop.ex2.GameGUI;

/**
 * A base class for follower ships, meaning ships that should follow
 * other ships as part of their functionality.
 * This class extends SpaceShip.
 * 
 * A parent class of BasherShip and AggressiveShip that need to follow their enemies.
 * 
 * @author black_knight
 *
 */
public class FollowerShip extends SpaceShip {
	final double EPSILON = 1E-12;
	final int NO_TURN = 0;
	final int TURN_RIGHT = -1;
	final int TURN_LEFT = 1;

	public void doAction(SpaceWars game) {
		super.doAction(game);
		SpaceShip closestShip = game.getClosestShipTo(this);
		double direction = this.getPhysics().angleTo(closestShip.getPhysics());

		if (direction < -EPSILON) {
			this.getPhysics().move(true, TURN_RIGHT);
		}
		else if (direction > EPSILON) {
			this.getPhysics().move(true, TURN_LEFT);
		}
		else {
			this.getPhysics().move(true, NO_TURN);
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
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		}
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}
}
