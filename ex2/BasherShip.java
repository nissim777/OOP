import oop.ex2.*;
import java.awt.Image;

/**
 * 	A class that represents an Basher space ship. 
 *  this class extends FollowerShip, which is also the parent class for AggressiveShip.
 *  It's abilities are:
 *  
 *  This ship attempts to collide with other ships. It will always accelerate, and turn towards
 *	the closest ship. If it gets within a distance of 0.2 units from another ship, it will turn 
 *  on its shield.

 * @author black_knight
 *
 */
public class BasherShip extends FollowerShip {
	final double MINIMAL_DISTANCE = 0.2;

	public void doAction(SpaceWars game) {
		super.doAction(game);
		SpaceShip closestShip = game.getClosestShipTo(this);
		double distance = this.getPhysics().distanceFrom(closestShip.getPhysics());
		
		if (distance <= MINIMAL_DISTANCE) {
			this.shieldOn();
		}
	}
}