import java.awt.Image;
import oop.ex2.*;

/**
 * An API every ship implements. This is an abstract class (the only abstract method is getImage).
 * 
 * Allows to determine the initial states every ship starts with, the cost and activation possibilities
 * of each ability like teleport, fire and shield. also determines in which situation the ship will lose 
 * health, as like collision, and keeps other states of the game needed for playing it.
 *  
 * @author black_knight
 *
 */

abstract class SpaceShip {
	private static final int FIRE_DELAY = 8;
	private static final int SHOT_MANA_COST = 20, SHIELD_MANA_COST = 3, TELEPORT_MANA_COST = 150;
	private static final int INITIAL_ENERGY = 200, INITIAL_HEALTH = 20, COLLISION_ENERGY_BOOST = 20,
			SHOT_ENERGY_REDUCE = 10;
	private int maxEnergy = INITIAL_ENERGY;
	protected int energy, health;     
	protected SpaceShipPhysics physics;
	protected boolean isDeadShip; 
	protected boolean shieldActivated;
	private int rounds_counter;

	/**
	 * Constructs new ship by calling the method Initialize and gives it initial states and location.
	 */ 
	public SpaceShip () {
		initialize();
	}
	/**
	 * Resets the ship with the initial states values. May be activate in case of a new game beginning or 
	 * after reset of a ship after death.
	 */
	private void initialize () {
		this.health = INITIAL_HEALTH;
		this.energy = INITIAL_ENERGY;
		this.isDeadShip = false;
		this.physics = new SpaceShipPhysics();
		this.rounds_counter = 0;
	}
	/**
	 * Does the actions of this ship for this round. 
	 * This is called once per round by the SpaceWars game driver.
	 * 
	 * @param game the game object to which this ship belongs.
	 */
	public void doAction(SpaceWars game) { 
		this.shieldActivated = false;
		regenrateMana();
		
		rounds_counter++;
	}

	/**
	 * This method is called every time a collision with this ship occurs 
	 */
	public void collidedWithAnotherShip() {
		if (!shieldActivated) {
			decreaseHealth();
		}	
		else {
			increaseMana();	
		}
	}
	/** 
	 * This method is called whenever a ship has died. It resets the ship's 
	 * attributes, and starts it at a new random position.
	 */
	public void reset(){
		initialize();
	}

	/**
	 * Checks if this ship is dead.
	 * 
	 * @return true if the ship is dead. false otherwise.
	 */
	public boolean isDead() {
		return isDeadShip = (health>0) ? false : true;	
	}

	/**
	 * Gets the physics object that controls this ship.
	 * 
	 * @return the physics object that controls the ship.
	 */
	public SpaceShipPhysics getPhysics() {
		return physics;
	}

	/**
	 * This method is called by the SpaceWars game object when ever this ship
	 * gets hit by a shot.
	 */
	public void gotHit() {
		if (!shieldActivated) {
			decreaseHealth();	
			decreaseMana();
		}
	}

	/*
	 * Decrease one point of health, and indicate that the ship is dead in case of health equals to zero.
	 * Can be cause because of collision or hit by a shot.
	 */
	private void decreaseHealth() {
		if (health-1 <= 0) {
			isDeadShip = true;
			health=0;
			return;
		}
		health--;
	}
	/* 
	 * Increases current and maximal mana.
	 */
	private void increaseMana(){
		this.energy += COLLISION_ENERGY_BOOST;
		this.maxEnergy +=COLLISION_ENERGY_BOOST;		
	}
	/* 
	 * Decreases current and maximal mana.
	 */
	private void decreaseMana(){
		this.maxEnergy -= SHOT_ENERGY_REDUCE;
		if (maxEnergy<0)
			maxEnergy =0;
		if (this.energy>this.maxEnergy){
			energy = maxEnergy;
		}
	}
	
	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round.
	 * 
	 * @return the image of this ship.
	 */
	abstract public java.awt.Image getImage();

	/**
	 * Attempts to fire a shot.
	 * 
	 * @param game the game object.
	 */
	public void fire(SpaceWars game) {
		if (rounds_counter >=FIRE_DELAY) {
			if(actionActivated(SHOT_MANA_COST) ) {
				game.addShot(physics);
				rounds_counter = 0;
			}
		}
	}

	/**
	 * Attempts to turn on the shield.
	 */
	public void shieldOn() {
		if (actionActivated(SHIELD_MANA_COST)) {
			shieldActivated = true;
		}
	}

	/**
	 * Attempts to teleport.
	 */
	public void teleport() {
		if (actionActivated(TELEPORT_MANA_COST)) {
			physics = new SpaceShipPhysics();
		}

	}
	/*
	 * Regenrates Mana
	 */
	private void regenrateMana() {
		if(energy<maxEnergy)
			energy++;
	}

	/*
	 * Checks if the ship has enought energy for activating a specific action and reduces the energy 
	 * from the ship's total energy in case there is. 
	 * 
	 * Returns true if it possible to activate the action and false if not.
	 */
	private boolean actionActivated(int manaCost) {
		boolean actionActivated = false;
		if (energy - manaCost >= 0) {
			energy-= manaCost;
			actionActivated = true;
		}
		return actionActivated;
	}
}