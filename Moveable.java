// import required classes
import java.util.ArrayList;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-03-13
 * 
 * Promises:
 * Provides a basis for all movable objects in the game.
 * Contains methods to move up, down, left, right.
 * Checks each movement for collision into a non-movable object.
 * 
 * Requires:
 * Nothing.
 */
public class Moveable extends Entity 
{
	// boolean to indicate direction the object is facing (true = left, false = right)
	private boolean direction;
	
	/**
	 * Constructor for Movable. Takes a path for a particular image file and initializes Sprite from it.
	 * 
	 * @param path The path to a particular image file.
	 */
	public Moveable (String path)
	{
		super(path);
		direction = false;
	}
	
	/**
	 * Constructor for Movable. Takes a path for a particular image file and initializes Sprite from it.
	 * 
	 * @param x The x coordinate for a cartesian coordinate system.
     * @param y The y coordiante for a cartesian coordinate system.
	 * @param path The path to a particular image file.
	 */
	public Moveable (int x, int y, String path)
	{
		super(x, y, path);
		direction = false;
	}
	
	/**
	 * Updates object's coordinates for upward movement.
	 * Checks if the object will collide into a tree or the edge of the map. If so,
	 * does not change the object's coordinates.
	 * 
	 * @param trees Coordinates of all the trees.
	 * @param treasure The treasure and its coordinates.
	 * @param lanterns Coordinates of all lanterns.
	 * @param sign The sign and its coordinates
	 */
	public void moveUp (ArrayList<Tree> trees, ArrayList<Lantern> lanterns, Sign sign)
	{   
        // Declaration of variables xTree and yTree which will store coordinates of the trees.
        int xTree;
        int yTree;
        
        // Check is initially set to 1 which will run the end of each if statements (that == wasd) below.
		// When check == 0, the player coordinate will not update, preventing unwanted behaviors
		int check = 1;
		
		// A for loop that runs through all the trees stored in the trees array list.
		for (Tree tree : trees) 
		{
			xTree = tree.getX(); 
			yTree = tree.getY();
			
			// This handles collision with a tree and prevents that behavior
			if (xTree == xCoordinate && yTree == yCoordinate - 1)
            {
				check = 0;
            }
		}
		
		// checks if the player will move into a lantern
		for (Lantern lantern : lanterns)
		{
			if (lantern.getX() == xCoordinate && lantern.getY() == yCoordinate - 1)
            {
				check = 0;
            }
		}
			
		// This prevents the player from going outside the boundaries of our game
		if (yCoordinate - 1 < 0)
		{
			check = 0;
		}
        
        // checks if player will mvoe into a sign
        else if (sign.getX() == xCoordinate && sign.getY() == yCoordinate - 1)
        {
            check = 0;
        }
			
		// This updates the player coordinate when check == 1.
		else if (check == 1)
		{
			yCoordinate--;
		}
	}
	
	/**
	 * Updates object's coordinates for downward movement.
	 * Checks if the object will collide into a tree or the edge of the map. If so,
	 * does not change the object's coordinate.
	 * 
	 * @param trees Coordinates of all the trees.
	 * @param treasure The treasure and its coordinates.
	 * @param lanterns Coordinates of all lanterns.
	 * @param sign The sign and its coordinates.
	 * @param boardSize Height and Width of the game board in number of tiles.
	 */
	public void moveDown (ArrayList<Tree> trees, ArrayList<Lantern> lanterns, Sign sign)
	{
		int boardSize = GameParameters.getBoardSize();

		// Declaration of variables xTree and yTree which will store coordinates of the trees.
        int xTree;
        int yTree;
        
        // Check is initially set to 1 which will run the end of each if statements (that == wasd) below.
		// When check == 0, the player coordinate will not update, preventing unwanted behaviors
		int check = 1;
		
		// A for loop that runs through all the trees stored in the trees array list.
		for (Tree tree : trees) 
		{
			xTree = tree.getX(); 
			yTree = tree.getY();
			
			// This handles collision with a tree and prevents that behavior
			if (xTree == xCoordinate && yTree == yCoordinate + 1)
            {
                check = 0;
            }
		}
		
		// checks if player will move into a lantern
		for (Lantern lantern : lanterns)
		{
			if (lantern.getX() == xCoordinate && lantern.getY() == yCoordinate + 1)
            {
				check = 0;
            }
		}
			
		// This prevents the player from going outside the boundaries of our game
		if (yCoordinate + 1 > boardSize - 1)
		{
			check = 0;
		}
        
        // checks if player will move into a sign
        else if (sign.getX() == xCoordinate && sign.getY() == yCoordinate + 1)
        {
            check = 0;
        }
			
		// This updates the player coordinate when check == 1.
		else if (check == 1)
		{
			yCoordinate++;
		}
	}
	
	/**
	 * Updates object's coordinates for leftward movement.
	 * Checks if the object will collide into a tree or the edge of the map. If so,
	 * does not change the object's coordinates.
	 * 
	 * @param trees Coordinates of all the trees.
	 * @param treasure The treasure and its coordinates.
	 * @param lanterns Coordinates of all lanterns.
	 * @param sign The sign and its coordinates
	 */
	public void moveLeft (ArrayList<Tree> trees, ArrayList<Lantern> lanterns, Sign sign)
	{
		// Declaration of variables xTree and yTree which will store coordinates of the trees.
        int xTree;
        int yTree;
        
        // Check is initially set to 1 which will run the end of each if statements (that == wasd) below.
		// When check == 0, the player coordinate will not update, preventing unwanted behaviors
		int check = 1;
		
		// A for loop that runs through all the trees stored in the trees array list.
		for (Tree tree : trees) 
		{
			xTree = tree.getX(); 
			yTree = tree.getY();
			
			// This handles collision with a tree and prevents that behavior
			if (xTree == xCoordinate - 1 && yTree == yCoordinate)
            {
                check = 0;
            }
		}
		
		// checks if player will move into a lantern
		for (Lantern lantern : lanterns)
		{
			if (lantern.getX() == xCoordinate - 1 && lantern.getY() == yCoordinate)
            {
                check = 0;
            }
		}
			
		// This prevents the player from going outside the boundaries of our game
		if (xCoordinate - 1 < 0)
		{
			check = 0;
		}
        
        // checks if player will move into a sign
        else if (sign.getX() == xCoordinate - 1 && sign.getY() == yCoordinate)
        {
            check = 0;
        }
			
		// This updates the player coordinate when check == 1.
		else if (check == 1)
		{
			xCoordinate--;
			direction = true;
		}
	}
	
	/**
	 * Updates object's coordinates for rightward movement.
	 * Checks if the object will collide into a tree or the edge of the map. If so,
	 * does not change the object's coordinate.
	 * 
	 * @param trees Coordinates of all the trees.
	 * @param treasure The treasure and its coordinates.
	 * @param lanterns Coordinates of all lanterns.
	 * @param sign The sign and its coordinates.
	 * @param boardSize Height and Width of the game board in number of tiles.
	 */
	public void moveRight (ArrayList<Tree> trees, ArrayList<Lantern> lanterns, Sign sign)
	{
		int boardSize = GameParameters.getBoardSize();
		
        // Declaration of variables xTree and yTree which will store coordinates of the trees.
        int xTree;
        int yTree;
        
        // Check is initially set to 1 which will run the end of each if statements (that == wasd) below.
		// When check == 0, the player coordinate will not update, preventing unwanted behaviors
		int check = 1;
		
		// A for loop that runs through all the trees stored in the trees array list.
		for (Tree tree : trees) 
		{
			xTree = tree.getX(); 
			yTree = tree.getY();
			
			// This handles collision with a tree and prevents that behavior
			if (xTree == xCoordinate + 1 && yTree == yCoordinate)
            {
                check = 0;
            }
		}
		
		// checks if player will move into a lantern
		for (Lantern lantern : lanterns)
		{
			if (lantern.getX() == xCoordinate + 1 && lantern.getY() == yCoordinate)
            {
                check = 0;
            }
		}
			
		// This prevents the player from going outside the boundaries of our game
		if (xCoordinate + 1 > boardSize - 1)
		{
			check = 0;
		}
        
        // checks if player will move into a sign
        else if (sign.getX() == xCoordinate + 1 && sign.getY() == yCoordinate)
        {
            check = 0;
        }
			
		// This updates the player coordinate when check == 1.
		else if (check == 1)
		{
			xCoordinate++;
			direction = false;
		}
	}
	
	/**
	 * Getter method for direction the object is facing in.
	 * 
	 * @return Boolean value for if the object is facing left or right (true = left, false = right).
	 */
	public boolean getDirection ()
	{
		return direction;
	}
	
	/**
	 * Setter method for direction.
	 * 
	 * @param direction The direction the object is facing in (true = left, false = right).
	 */
	public void setDirection (boolean direction)
	{
		this.direction = direction;
	}
}

