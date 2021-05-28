/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-03-13
 * 
 * Promises:
 * Serves as a utility class for Display related tasks:
 * 1) Prints instructions for the game.
 * 2) Prints out a grid (board) for the game.
 * 3) Prints message to indicate if the player is getting closer or further from the treasure.
 * 4) Prints ending screen for the game.
 * 
 * Requires:
 * Class to be ran by the main function.
 * User Input.
 */
public class GameParameters 
{
	// number of tiles which make up the board width and height
    private static int BOARDSIZE = 21;
    // TILESIZE is in pixels
    private static final int TILESIZE = 40;
    // FONTSize is max vertical height in pixels
    private static final int FONTSIZE = 15;
    
    // instance variables for the number of randomly generated game objects
    private static int NUMBERTREES = 4;
    
    // names/locations of assets
    private static final String playerSpritePath = "player.gif";
    private static final String enemySpritePath = "enemy.gif";
    private static final String treeSpritePath = "tree.png";
    private static final String lanternOffSpritePath = "lanternOff.png";
    private static final String lanternOnSpritePath = "lanternOn.png";
    private static final String signSpritePath = "sign.png";
    private static final String grassSpritePath = "grass.png";
    private static final String lastDigSpritePath = "lastDig.png";
    
    /**
     * Getter method for BOARDSIZE.
     * 
     * @return The height and width of the game board.
     */
    public static int getBoardSize ()
    {
        return BOARDSIZE;
    }
    
    /**
     * Getter method for TILESIZE.
     * 
     * @return The height and width of each tile in pixels.
     */
    public static int getTileSize ()
    {
        return TILESIZE;
    }
    
    /**
	 * Getter method for FONTSIZE.
	 * 
	 * @return The font size in pixels.
	 */
	public static int getFontSize()
	{
		return FONTSIZE;
	}
	
	/**
	 * Getter method for NUMBERTREES.
	 * 
	 * @return The number of trees.
	 */
	public static int getNumberTrees()
	{
		return NUMBERTREES;
	}
	
	/**
	 * Setter method for NUMBERTREES.
	 * 
	 * @param amount The amount of trees for the game.
	 */
	public static void setNumberTrees (int amount)
    {
		NUMBERTREES = amount;
	}
	
	/**
	 * Setter method for BOARDSIZE.
	 * 
	 * @param size The board size for the game.
	 */
	public static void setBoardSize (int size)
	{
		BOARDSIZE = size;
	}
	
	/**
	 * Getter method for playerSpritePath.
	 * 
	 * @return the path of the player sprite in a string format.
	 */
	public static String getPlayerSpritePath ()
	{
		return playerSpritePath;
	}
	
	/**
	 * Getter method for enemySpritePath.
	 * 
	 * @return the path of the enemy sprite in a string format.
	 */
	public static String getEnemySpritePath ()
	{
		return enemySpritePath;
	}
	
	/**
	 * Getter method for treeSpritePath.
	 * 
	 * @return the path of the tree sprite in a string format.
	 */
	public static String getTreeSpritePath ()
	{
		return treeSpritePath;
	}
	
	/**
	 * Getter method for lanternOffSpritePath.
	 * 
	 * @return the path of the lantern (turned off) sprite in a string format.
	 */
	public static String getLanternOffSpritePath ()
	{
		return lanternOffSpritePath;
	}
	
	/**
	 * Getter method for lanternOnSpritePath.
	 * 
	 * @return the path of the lantern (turned on) sprite in a string format.
	 */
	public static String getLanternOnSpritePath ()
	{
		return lanternOnSpritePath;
	}
	
	/**
	 * Getter method for signSpritePath.
	 * 
	 * @return the path of the sign sprite in a string format.
	 */
	public static String getSignSpritePath ()
	{
		return signSpritePath;
	}
	
	/**
	 * Getter method for grassSpritePath.
	 * 
	 * @return the path of the sign sprite in a string format.
	 */
	public static String getGrassSpritePath ()
	{
		return grassSpritePath;
	}
	
	/**
	 * Getter method for lastDigSpritePath.
	 * 
	 * @return the path of the last dig sprite in a string format.
	 */
	public static String getLastDigSpritePath ()
	{
		return lastDigSpritePath;
	}
}

