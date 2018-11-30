import oop.ex2.*;

import java.awt.Image;

/**
 * A class that represent an Runner space ship. 
 *  this class extends SpaceShip.
 *  It's abilities are:
 *  
 *  This spaceship attempts to run away from the fight. It will constantly accelerate, and turn
 *	away from the nearest ship. The runner has the spying ability and will attempt spying on each
 *  round. It will try to get the nearest ship cannon angle from himself. If that angle is smaller than 0.2
 *  radians (in any direction) and the distance from the nearest ship is smaller than 0.2 units, the
 *  runner will try to teleport.
 * 
 * @author black_knight
 *
 */
public class RunnerShip extends SpaceShip {
	final double MINIMAL_DISTANCE = 0.2;
	final double MINIMAL_ANGLE = 0.2;
	final int TURN_RIGHT = -1;
	final int TURN_LEFT = 1;
	final int NO_TURN = 0;
	final double EPSILON = 1E-12;

	public void doAction(SpaceWars game) {
		super.doAction(game);

		// Runs away from the closest ship.
		SpaceShip closestShip = game.getClosestShipTo(this);
		double direction = this.getPhysics().angleTo(closestShip.getPhysics());
		if (direction < -EPSILON) {
			this.getPhysics().move(true, TURN_LEFT);
		}
		else if (direction > EPSILON) {
			this.getPhysics().move(true, TURN_RIGHT);
		}
		else {
			this.getPhysics().move(true, NO_TURN);
		}

		double distance = this.getPhysics().distanceFrom(closestShip.getPhysics());
		if (distance <= MINIMAL_DISTANCE && 
				Math.abs(closestShip.getPhysics().angleTo(this.getPhysics())) <= MINIMAL_ANGLE) {
			this.teleport();
		}
	}

	public Image getImage(){
		if (this.shieldActivated)
		{
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		}
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
	}
}
