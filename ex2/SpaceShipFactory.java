import oop.ex2.*;
/**
 * This class acts as a spaceship factory for the Space Wars game.
 * 
 * @author black_knight
 */
public class SpaceShipFactory {

	final static int START_STRING = 0;

    private SpaceShipFactory() {}

    /**
     * Creates spaceships. You should complete this method.
     * 
     * @param args an array which indicates the type of
     * spaceships to create.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
    	SpaceShip [] ships = new SpaceShip[args.length];
    	
    	for (int i = 0; i < args.length; i++) {
			char indicator = args[i].charAt(START_STRING); 
			switch (indicator) {
			case 'h':
				ships[i] = new HumanShip();
				break;
			case 'd':
				ships[i] = new Drunkard();
				break;
			case 'r':
				ships[i] = new RunnerShip();
				break;
			case 'b':
				ships[i] = new BasherShip();
				break;
			case 'a':
				ships[i] = new AggressiveShip();
				break;
			case 's':
				ships[i] = new SpecialShip();
			default:
				break;
			}
			
		}
    	return ships;
    }
}
