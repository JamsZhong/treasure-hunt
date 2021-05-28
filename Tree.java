// import required classes
import java.util.Random;
import java.util.ArrayList;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-09
 * 
 * Promises:
 * Contains the x and y coordinates of a tree. Ensures the tree is at least three spaces
 * apart from another object.
 * 
 * Requires:
 * Nothing.
 */
public class Tree extends NonInteractable 
{
	
	/**
	 * Default constructor for tree.
	 */
	public Tree ()
	{
		super(GameParameters.getTreeSpritePath());
	}
	
	/**
	 *  Creates a random coordinate for the tree on the game board, and
	 *  ensures that the location is at least three spaces from any existing objects.
	 * 
	 * @param trees List of coordinates of trees.
	 * @param player The player and its coordinates.
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
				
				// Prevent spawning a tree close to: player, treasure, lanterns, sign, other trees
				// If the tree is too close, randomize the location and check again
				for (Tree tree : trees)
				{
					if(Math.abs(x - tree.getX()) <= 2 && Math.abs(y - tree.getY()) <= 2)
					{
						continue here;
					}
					else if(Math.abs(x - player.getX()) <= 2 && Math.abs(y - player.getY()) <= 2)
					{
						continue here;
					}
                    else if(Math.abs(x - sign.getX()) <= 2 && Math.abs(y - sign.getY()) <= 2)
                    {
                        continue here;
                    }
					for (Lantern lantern: lanterns)
					{
						if(Math.abs(x - lantern.getX()) <= 2 && Math.abs(y - lantern.getY()) <= 2)
						{
							continue here;
						}
					}
						
				}
				
                // When coordinates are found which satisfy conditions	
				setCoordinates(x,y);	
				break here;
			}		
			
		// Add the tree to ArrayList
		trees.add(this);
	}
}
