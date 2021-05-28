/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 3.0
 * @since 2018-02-08
 * 
 * Promises:
 * Holds the coordinates of an element in the game.
 * 
 * Requires:
 * None.
 */
public class Entity 
{
	// x and y coordinates for the object
	protected int xCoordinate;
	protected int yCoordinate;
	protected Sprite sprite;
	
	/**
     * Default constructor for Coordinates.
     */
	public Entity()
	{
		xCoordinate = 0;
		yCoordinate = 0;
		sprite = null;
	}
	
	/**
     * Constructor for Coordinates.
     * 
     * @param path The path to a particular image file.
     */
	public Entity(String path)
	{
		setCoordinates(0, 0);
		sprite = new Sprite(path);
	}
	
	/**
     * Constructor for Coordinates.
     * 
     * @param x The x coordinate for a cartesian coordinate system.
     * @param y The y coordiante for a cartesian coordinate system.
     */
	public Entity(int x, int y)
	{
		setCoordinates(x, y);
		sprite = null;
	}
	
	/**
     * Constructor for Coordinates.
     * 
     * @param x The x coordinate for a cartesian coordinate system.
     * @param y The y coordinate for a cartesian coordinate system.
     * @param path The path to a particular image file.
     */
	public Entity(int x, int y, String path)
	{
		setCoordinates(x, y);
		sprite = new Sprite(path);
	}
	
	/**
	 * Setter method for x and y coordiantes to be used for updating.
     * 
     * @param x The x coordinate for a cartesian coordinate system.
     * @param y The y coordinate for a cartesian coordinate system.
	 */
	public void setCoordinates(int x, int y)
	{
		xCoordinate = x;
		yCoordinate = y;
	}
	 
	/**
	 * Getter method for x coordinate.
     * 
     * @return The x coordinate of the object.
	 */
	public int getX()
	{
		return xCoordinate;
	}
	
	/**
	 * Getter method for y coordinate.
     * 
     * @return The y coordinate of the object.
	 */
	public int getY()
	{
		return yCoordinate;
	}
	
	/**
	 * Getter method for the sprite object.
	 * 
	 * @return The sprite object.
	 */
	public Sprite getSprite ()
	{
		return sprite;
	}
}

