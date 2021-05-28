// Import required classes
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-22
 * 
 * Promises:
 * Forms basis of lantern object.
 * Turns on and off based on player interaction.
 * To be used for the puzzle.
 * 
 * Requires:
 * Classes: Interactable
 */
public class Lantern extends Interactable
{
	// an additional Sprite for when the lantern is turned on
	private Sprite onSprite;
	// instance variable to check the lighting of the lantern 
	private String onOff;
    // instance variable to check the order of the lantern
    private int order;
	
	/**
	 * Constructor for Lantern.
	 * 
	 * @param x The x coordinate for a cartesian coordinate system.
     * @param y The y coordiante for a cartesian coordinate system.
     * @param order The order of the lantern for the puzzle.
     */
	public Lantern (int x, int y, int order)
	{
		super(x, y, GameParameters.getLanternOffSpritePath());
		onSprite = new Sprite(GameParameters.getLanternOnSpritePath());
		onOff = "off";
        this.order = order;
	}
	
	/**
     * Getter method for status of the lantern (on or off).
     * 
     * @return The status of the lantern.
     */
	public String getStatus ()
	{
		return onOff;
	}
    
    /**
     * Getter method for the order of the lantern for the puzzle.
     * 
     * @return The order of the lantern.
     */
    public int getOrder ()
    {
        return order;
    }
	
	/**
     * Checks if the lantern is off. If lantern is off turns the lantern on.
     * 
     * @return The height and width of the game board.
     */
	public boolean turnOn ()
	{
		if (onOff == "off")
		{
			onOff = "on";
            return true;
		}
        // if the lantern was already off, do nothing.
        else
        {
            return false;
        }
	}
    
    /**
     * Resets the lantern to the off status.
     */
    public void resetLantern ()
    {
        onOff = "off";
    }
     
    /**
	 * Performs Logic to determine:
	 *    1) Which lantern the player is interacting with.
	 *    2) If the lantern should light up.
	 *    3) If the player has performed the puzzle correctly.
	 * Interacts with puzzle class to perform actions.
	 * 
	 * @param gc Stores parameters to render on the canvas.
	 * @param treasure The treasure and its coordinates.
	 * @param lanterns All lanterns, their positions, and orders.
	 * @param player The player and their coordinates.
	 * @param puzzle The overarching lantern puzzle, contains the order of how the lanterns were lit up.
	 * @param boardSize Number of tiles for height and width for the board grid.
     * @param tileSize Height and width in pixels for each tile of the board.
	 */
	public static void lightLantern (GraphicsContext gc, Treasure treasure, ArrayList<Lantern> lanterns, Player player, Puzzle puzzle)
	{
		// check for all lanterns
		for(Lantern lantern : lanterns)
		{
			// check if the player is beside a lantern
			if((Math.abs(player.getX() - lantern.getX()) == 0 && Math.abs(player.getY() - lantern.getY()) == 1) || (Math.abs(player.getX() - lantern.getX()) == 1 && Math.abs(player.getY() - lantern.getY()) == 0))
			{
                // true means the lantern was turned on
				if(lantern.turnOn())
                {
                    char check = puzzle.litLantern(lantern.getOrder());
                    // add lanten to puzzle and perform a check
                    if(check == 'H')
                    {
                        // give hint
                        puzzle.giveHint(gc, treasure);
                    }
                    else if(check == 'R')
                    {
                        // reset the lanterns
                        puzzle.resetPuzzle(lanterns);
                    }
                }
			}
		}
	}
	
	/**
	 * Getter method for the Sprite object.
	 * Returns the correct sprite (when the lantern is off versus on).
	 * 
	 * @return The sprite object.
	 */
	public Sprite getSprite ()
	{
		if (onOff == "off")
		{
			return sprite;
		}
		else
		{
			return onSprite;
		}
	}
}

