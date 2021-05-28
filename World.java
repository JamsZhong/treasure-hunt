// import required classes
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-22
 * 
 * Promises:
 * Serves as the interface between player input and objects in the game.
 * Stores constants.
 * Declares and initializes instances.
 * Methods to perform logic for the game.
 * 
 * Requires:
 * Classes: Player, Lantern, Sign, Tree, Puzzle.
 */
public class World 
{    
	// create instances of our objects
    private Player player = new Player();
	private ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
	private Sign sign = new Sign("NWSE");
    private Treasure treasure = new Treasure();
    private ArrayList<Tree> trees = new ArrayList<Tree>();
    private Puzzle puzzle = new Puzzle();
	private	Enemy enemy = new Enemy();
	private Sprite grass = new Sprite(GameParameters.getGrassSpritePath());
    
    /**
     * Default constructor for Interface.
     */
    public World ()
    {
        // initialize lantern locations
        declareLanterns();
        
        // declare trees
        declareTrees();
        
        // initialize random treasure location
        treasure.initialize(trees, player, lanterns, sign);
        
        //initialize enemy
		enemy.initializeEnemy(trees, player, lanterns, sign);
    }
        
	/**
	 * Initialize lantern locations.
	 */
	private void declareLanterns ()
	{	
		// remove existing lanterns
		lanterns.clear();
		
		int boardSize = GameParameters.getBoardSize();

        // create and initialize lantern locations
		lanterns.add(new Lantern(boardSize / 2, 0, 1));
		lanterns.add(new Lantern(0, boardSize / 2, 2));
		lanterns.add(new Lantern(boardSize / 2, boardSize - 1, 3));
		lanterns.add(new Lantern(boardSize - 1, boardSize / 2, 4));
	}
    
    /**
     * Declare trees based on the constant NUMBERTREES and store in ArrayList.
     */
    private void declareTrees ()
    {
		// remove existing trees
		trees.clear();
		
		int numberTrees = GameParameters.getNumberTrees();
		
		// declare the amount of trees as outlined by the constant NUMBERTREES
        for(int i = 0; i < numberTrees; i ++)
        {
            Tree tree = new Tree();
            tree.initialize(trees, player, lanterns, sign);
            trees.add(tree);
        }
    }
    
    /**
     * Getter method for ArrayList of trees.
     * 
     * @return The ArrayList of trees.
     */
    public ArrayList<Tree> getTrees ()
    {
        return trees;
    }
    
    /**
     * Getter method for treasure.
     * 
     * @return The Treasure object.
     */
    public Treasure getTreasure()
    {
        return treasure;
    }
    
    /**
     * Getter method for player.
     * 
     * @return The Player object.
     */
    public Player getPlayer ()
    {
        return player;
    }
    
    /**
     * Getter method for enemy.
     * 
     * @return The Enemy object.
     */
    public Enemy getEnemy ()
    {
		return enemy;
	}
	
	/**
     * Getter method for ArrayList of lanterns.
     * 
     * @return The ArrayList of lanterns.
     */
	public ArrayList<Lantern> getLanterns ()
	{
		return lanterns;
	}
    
    /**
     * Getter method for sign.
     * 
     * @return The Sign object.
     */
    public Sign getSign ()
    {
        return sign;
    }
    
    /**
     * Getter method for puzzle.
     * 
     * @return The puzzle object.
     */
    public Puzzle getPuzzle ()
    {
		return puzzle;
	}
	
	/**
	 * Removes a tree from the game. Re-randomizes tree locations.
	 */
	public void removeTree ()
    {
		GameParameters.setNumberTrees(GameParameters.getNumberTrees() - 1);
		declareTrees();
        treasure.initialize(trees, player, lanterns, sign);
		enemy.initializeEnemy(trees, player, lanterns, sign);
	}
	
	/**
	 * Change the boardSize (decrease) of the game and adjust locations of lanterns, sign, treasure, and enemy accordingly.
	 */
	public void decreaseBoardSize()
	{
		// ensures that board size will always be an odd number, due to the placement of the lanterns and sign
		// roughly increments in multiples of 5
		if (GameParameters.getBoardSize() % 10 == 5)
		{
			GameParameters.setBoardSize(GameParameters.getBoardSize() - 4);
		}
		else if (GameParameters.getBoardSize() % 10 == 1)
		{
			GameParameters.setBoardSize(GameParameters.getBoardSize() - 6);
		}
		
		// initialize all objects for the new board
        declareLanterns();
        sign = new Sign("NESW");
        treasure.initialize(trees, player, lanterns, sign);
        declareTrees();
		enemy.initializeEnemy(trees, player, lanterns, sign);
	}
	
	/**
	 * Adds an additional tree to the game and re-randomizes tree locations.
	 */
	public void addTree()
	{
		GameParameters.setNumberTrees(GameParameters.getNumberTrees() + 1);
		declareTrees();
        treasure.initialize(trees, player, lanterns, sign);
		enemy.initializeEnemy(trees, player, lanterns, sign);
	}
	
	/**
	 * Change the boardSize (increase) of the game and adjust locations of lanterns, sign, treasure, and enemy accordingly.
	 */
	public void increaseBoardSize()
	{
		
		// ensures that board size will always be an odd number, due to the placement of the lanterns and sign
		// roughly increments in multiples of 5
		if (GameParameters.getBoardSize() % 10 == 5)
		{
			GameParameters.setBoardSize(GameParameters.getBoardSize() + 6);
		}
		else if (GameParameters.getBoardSize() % 10 == 1)
		{
			GameParameters.setBoardSize(GameParameters.getBoardSize() + 4);
		}
		
		// initialize all objects for the new board
        declareLanterns();
        sign = new Sign("NESW");
        treasure.initialize(trees, player, lanterns, sign);
        declareTrees();
		enemy.initializeEnemy(trees, player, lanterns, sign);
	}
	
	/**
	 * Getter method for the grass sprite object.
	 * 
	 * @return The grass sprite object.
	 */
	public Sprite getGrass ()
	{
		return grass;
	}
}

