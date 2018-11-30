import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The special Ship drains energy from the closest ship by forcing it to activate its shield and stoping its
 * energy regeneration. If the energy draining continues over 300 ticks after its reached to zero, the special
 * Ship became some kind of a leech and activating its parasite ability, killing the ship slowly and converts
 * each life point it has into 2 life points for itself. 
 * 
 * @author black_knights
 *
 */
public class SpecialShip extends FollowerShip {
	private final int STOP_MANA_REGENERATION = 1;
	private final static Image SHIP = new ImageIcon("/cs/stud/black_knight/image/spaceship.gif","")
		.getImage();
	private int rounds_counter = 300;
	
	public void doAction(SpaceWars game) {
		super.doAction(game);

		SpaceShip closestShip = game.getClosestShipTo(this);
		closestShip.shieldOn();
		if(closestShip.energy>=1) // Sucks out enemy's energy.
			closestShip.energy -= STOP_MANA_REGENERATION;

		// parasite
		if(closestShip.energy <=1)
		{
			rounds_counter--;
		}
		else
			rounds_counter=300; // Continues the leeching.
		
		if(rounds_counter<=0)
			parasite(closestShip);
	}

	private void parasite(SpaceShip closestShip) {
		this.physics =closestShip.getPhysics();
		if(rounds_counter %15 == 0) {
			closestShip.gotHit();
			this.health+=2;
		}

	}

	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round.
	 * this method overrides the followerShip getImage method
	 * @return the image of this ship.
	 */
	public Image getImage(){
		return SHIP;
	}
}
