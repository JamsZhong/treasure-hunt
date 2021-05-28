/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-22
 * 
 * Promises:
 * Provides a basis for all interactable objects in the game.
 * 
 * Requires:
 * Classes: Coordinates
 */
public class Interactable extends Entity 
{	
	
	/**
	 * Constructor for Interactable. Takes a path for an image and initializes Sprite with it.
	 * 
	 * @param x The x coordinate for a cartesian coordinate system.
     * @param y The y coordiante for a cartesian coordinate system.
	 * @param path The path for a particular image file.
	 */
	public Interactable (int x, int y, String path)
	{
		super(x, y, path);
	}
}

