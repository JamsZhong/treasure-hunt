// import required classes
import java.util.Random;
import java.util.ArrayList;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 3.0
 * @since 2018-02-24
 * 
 * Promises:
 * Contains x and y coordinates of an enemy object
 * Enemy moves closer to the player once every second
 * Ensures the enemy does not collide with trees
 * Game ends if the enemy contacts the player
 * 
 * Requires:
 * Nothing.
 */
public class Enemy extends Moveable
{
	
	private double creationTime = System.nanoTime();
	
	/**
	 * Default constructor for enemy. Initializes the sprite.
	 */
	public Enemy ()
	{
		super(GameParameters.getEnemySpritePath());
	}
	
	/**
	 * Creates a random location for the enemy on the game board and checks it
	 * does not overlap with a tree or is too close to the player.
     * 
     * @param trees: List of tree objects
     * @param player: Player object
	 */
	public void initializeEnemy(ArrayList<Tree> trees, Player player, ArrayList<Lantern> lanterns, Sign sign) 
	{
		int boardSize = GameParameters.getBoardSize();
		
		Random rand = new Random();
		
		here: while(true)
			{
				// Ensures coordinates are within a 20 x 20 grid
				int x = rand.nextInt(boardSize);
				int y = rand.nextInt(boardSize);
				
				// Prevent spawning the enemy on a tree or within 6 units of the player
				// If the conditions are not met, randomize the location and check again
				for (Tree tree : trees)
				{
					if(x == tree.getX() && y == tree.getY())
					{
						continue here;
					}
					else if(Math.abs(x - player.getX()) <= 6 && Math.abs(y - player.getY()) <= 6)
					{
						continue here;
					}
					else if(x == sign.getX() && y == sign.getY())
					{
						continue here;
					}
					for (Lantern lantern: lanterns)
					{
						if(x == lantern.getX() && y == lantern.getY())
						{
							continue here;
						}
					}
						
				}
				
				this.setCoordinates(x,y);	
				break here;
			}		
	}
	
	/**
	 * Moves the enemy closer to the player at a set time interval.
     * 
     * @param time: current time in nanoseconds
     * @param player: Player object
     * @param trees: list of Trees
	 */
	public void move(double time, World gameWorld)
	{
		// stores direction enemy wants to move in (u == up, d == down, r == right, l == left)
		char direction = 'n';
		
		//Move enemy if a second has passed since the last time this method was called
		if(time - creationTime >= 1E9)
		{
			//Temporary storage of new enemy location which is closer to the player
			Entity closest = new Entity(this.getX(), this.getY());
			
			//Check moving is which direction would move the enemy closer to the player
			// check for moving up
			if ( Math.pow(Math.pow((this.getY() - 1 - gameWorld.getPlayer().getY()), 2) + Math.pow((this.getX() - gameWorld.getPlayer().getX()), 2), 0.5) < 
			     Math.pow(Math.pow((closest.getY() - gameWorld.getPlayer().getY()), 2) + Math.pow((closest.getX() - gameWorld.getPlayer().getX()), 2), 0.5))
			{
				direction = 'u';
				closest.setCoordinates(this.getX(), this.getY() - 1);
			}
			
			// check for moving down
			if ( Math.pow(Math.pow((this.getY() + 1 - gameWorld.getPlayer().getY()), 2) + Math.pow((this.getX() - gameWorld.getPlayer().getX()), 2), 0.5) < 
			     Math.pow(Math.pow((closest.getY() - gameWorld.getPlayer().getY()), 2) + Math.pow((closest.getX() - gameWorld.getPlayer().getX()), 2), 0.5))
			{
				direction = 'd';
				closest.setCoordinates(this.getX(), this.getY() + 1);
			}
			
			// check for moving left
			if ( Math.pow(Math.pow((this.getY() - gameWorld.getPlayer().getY()), 2) + Math.pow((this.getX() - 1 - gameWorld.getPlayer().getX()), 2), 0.5) < 
			     Math.pow(Math.pow((closest.getY() - gameWorld.getPlayer().getY()), 2) + Math.pow((closest.getX() - gameWorld.getPlayer().getX()), 2), 0.5))
			{
				direction = 'l';
				closest.setCoordinates(this.getX() - 1, this.getY());
			}
			
			// check for moving right
			if ( Math.pow(Math.pow((this.getY() - gameWorld.getPlayer().getY()), 2) + Math.pow((this.getX() + 1 - gameWorld.getPlayer().getX()), 2), 0.5) < 
			     Math.pow(Math.pow((closest.getY() - gameWorld.getPlayer().getY()), 2) + Math.pow((closest.getX() - gameWorld.getPlayer().getX()), 2), 0.5))
			{
				direction = 'r';
				closest.setCoordinates(this.getX() + 1, this.getY());
			}
			
			// check for which direction the enemy should move in and call the respective method
			if (direction == 'u')
			{
				moveUp(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
			}
			else if (direction == 'd')
			{
				moveDown(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
			}
			else if (direction == 'l')
			{
				moveLeft(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
			}
			else if (direction == 'r')
			{
				moveRight(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
			}
			
			//Update time since last movement	
			creationTime = time;
		}
	}
	
	/**
	 * Check if the enemy has contacted the player, signaling the loss
	 * of the game.
     * 
     * @param player: Player object
	 */
	public boolean lose (Player player)
	{
		if(this.getX() == player.getX() && this.getY() == player.getY())
			return true;
			
		return false;
	}
}
