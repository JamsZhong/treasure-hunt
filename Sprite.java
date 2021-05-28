// import required classes
import javafx.scene.image.Image;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-03-23
 * 
 * Promises:
 * Holds an image for an object in the game.
 * 
 * Requires:
 * None.
 */
public class Sprite 
{	
	private Image img;
	
	/**
	 * Constructor for Sprite. Uses a path to an image file.
	 * 
	 * @param path The path to a particular image file.
	 */
	public Sprite (String path)
	{
		img = new Image(path);
	}
	
	/**
	 * Getter method for the image.
	 * 
	 * @return the image stored in Sprite.
	 */
	public Image getImg ()
	{
		return img;
	}
}

