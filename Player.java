// Import required classes
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 2.0
 * @since 2018-02-20
 * 
 * Promises:
 * Contains the x and y coordinates of the player.
 * Contains the x and y coordinates of the closest dig location to the treasure.
 * Performs the logic to check if player coordinates should be updated and updates the coordinates of the player.
 * Allows the player to check if the treasure is in the player's current location.
 * Ensures that the player does not collide with trees or go off the grid map.
 * Checks if the player has found the treasure.
 * 
 * Requires:
 * user input.
 */
public class Player extends Moveable
{
	Entity lastDig;
	
	/**
	 * Default constructor for Player.
	 */
	public Player ()
	{
		super(GameParameters.getPlayerSpritePath());
		lastDig = new Entity(0, 0, GameParameters.getLastDigSpritePath());
	}
	
	/**
	 *  Checks whether the player has found the treasure
	 *  If the player has not found the treasure, displays a message indicating
	 *  whether the player is getting closer or father from the treasure.
	 * 
	 *  @param treasure: the treasue and its coordinates
	 * 
	 *  @return true if the player has found the treasure. False otherwise
	 */
	public boolean dig (Treasure treasure, GraphicsContext gc)  
	{
		// Check if player and treasure are in the same location
		if (xCoordinate == treasure.getX() && yCoordinate == treasure.getY())
		{
			return true;
		}
		else 
		{
			// Calculate distance between player and treasure as well as the distance between the last time dig was used and the treasure
			double distance = Math.pow(Math.pow((treasure.getY() - this.getY()), 2) + Math.pow((treasure.getX() - this.getX()), 2), 0.5);
			double oldDistance = Math.pow(Math.pow((treasure.getY() - lastDig.getY()), 2) + Math.pow((treasure.getX() - lastDig.getX()), 2), 0.5);
			
			// Notifies player he is getting closer to the treasure if the current distance is smaller than the distance between the treasure and the last time dig was used
			// Updates the location of the closest "dig" to the treasure
			if (distance < oldDistance)
            {
				Display.closer(gc);
                lastDig.setCoordinates(xCoordinate, yCoordinate);
            }
            
            // Notifies the player he is exactly the same distance away from the treasure as the last time dig was used
            else if (distance == oldDistance)
            {
				Display.sameDistance(gc);
			}
			
			// Notifies the player he is getting further from the treasure if the current distance is greater than the distance between the treasure and the last time dig was used
			else
            {
				Display.further(gc);
            }
       
			return false;
		}
	}
	
	/**
	 * Getter method for lastDig.
	 * 
	 * @param returns coordinates and sprite of the lastDig in an entity object.
	 */
	public Entity getLastDig ()
	{
		return lastDig;
	}
}

