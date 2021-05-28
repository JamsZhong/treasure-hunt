// import required classes
import java.util.ArrayList;
import java.util.Random;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-09
 * 
 * Promises:
 * Contains the x and y coordinates of the treasure. And a method to initialize treasure location.
 * 
 * Requires:
 * Nothing.
 */
public class Treasure extends NonInteractable 
{	
	/**
	 *  Creates a random coordinate for the tree on the game board, and
	 *  ensures that the location is not overlapping with existing objects.
	 * 
	 * @param trees A List of the coordinates of each tree object
	 * @param player The player and its coordinates
	 * @param lanterns Coordinates of all lanterns.
	 * @param sign The sign and its coordinates.
	 * @param boardSize Height and Width of the game board in number of tiles.
	 */
	public void initialize (ArrayList<Tree> trees, Player player, ArrayList<Lantern> lanterns, Sign sign) 
    {
		int boardSize = GameParameters.getBoardSize();
		
		Random rand = new Random();
	
		here: while(true)
        {
            // Ensures coordinates are within a specified board size
            int x = rand.nextInt(boardSize);
            int y = rand.nextInt(boardSize);
				
            // Prevent spawning a treasure on top of the player, sign, trees, or lanterns
            // If the treasure spawns on top of an object, randomize the location and check again
            for (Tree tree : trees)
            {
                if(x == tree.getX() && y == tree.getY())
                {
                    continue here;
                }
                else if(x == player.getX() && y == player.getY())
                {
                    continue here;
                }
                else if(x == sign.getX() && y == sign.getY())
                {
                    continue here;
                }	
            }
            
            for (Lantern lantern : lanterns)
            {
                if(x == lantern.getX() && y == lantern.getY())
                {
                    continue here;
                }
            }
            setCoordinates(x, y);	
            break here;
        }				
	}
}
