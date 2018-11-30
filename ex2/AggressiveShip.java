import oop.ex2.*;
import java.awt.Image;

/**
 *  A class that represent an Aggressive space ship. 
 *  this class extends FollowerShip, which is also the parent class for BasherShip.
 *  It's abilities are:
 *  
 *  This ship pursues other ships and tries to fire at them. It will always accelerate, and
 *  turn towards the nearest ship. If its angle from the nearest ship is 0.2 radians or less (in any
 *  direction) then it will open fire.
 * @author black_knight
 *
 */
public class AggressiveShip extends FollowerShip {
	final double MINIMAL_DISTANCE = 0.2;
	
	public void doAction(SpaceWars game) {
		super.doAction(game);
		SpaceShip closestShip = game.getClosestShipTo(this);
		double direction = this.getPhysics().angleTo(closestShip.getPhysics());
		
		// If the closest ship is around, fire.
		if (Math.abs(direction) <= MINIMAL_DISTANCE) {
			this.fire(game);
		}
	}
}