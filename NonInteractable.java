// Import required classes
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-23
 * 
 * Promises:
 * Provides a basis for all non-interactable objects in the game.
 * Contains a method declaration for initialization of the object's location.
 * 
 * Requires:
 * Classes: Coordinates
 */
public abstract class NonInteractable extends Entity 
{		
	/**
	 * Default constructor for NonInteractable.
	 */
	public NonInteractable ()
	{
		super();
	}
	
	/**
	 * Constructor for NonInteractable. Takes a path for a particular image file and initializes Sprite from it.
	 * 
	 * @param path The path to a particular image file.
	 */
	public NonInteractable (String path)
	{
		super(path);
	}
	
	/**
	 * To initialize the object location and ensures no overlap with existing objects.
	 *
	 * @param trees A List of the coordinates of each tree object
	 * @param player The player and its coordinates
	 * @param lanterns Coordinates of all lanterns.
	 * @param sign The sign and its coordinates.
	 * @param boardSize Height and Width of the game board in number of tiles.
	 */
	public abstract void initialize (ArrayList<Tree> trees, Player player, ArrayList<Lantern> lanterns, Sign sign);
}

