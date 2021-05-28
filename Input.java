// Import required classes
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-24
 * 
 * Promises:
 * Serves as a utility class for input related tasks:
 * 1) Checks if the user has given valid input.
 * 3) Interact with gameWorld to perform the desired action.
 * 
 * Requires:
 * Class to be ran by the main function.
 * User Input.
 */
public class Input
{      
    private static boolean foundTreasure = false;
    
    /**
	 * Takes user input relating to movement, and interactions.
	 * 
	 * @param event KeyEvent which contains information of any button the player has pressed.
	 * @param gameWorld Stores Information about the game and the interface layer between classes.
	 * @param gc Stores parameters to render on the canvas.
	 * @param Scene Container for all content in a scene graph.
	 * 
     * @return true if treasure is found by the dig method. False otherwise.
     */
    public static boolean playerInput (KeyEvent event, World gameWorld, GraphicsContext gc)
	{
		if (event.getCode() == KeyCode.W) 
		{
			gameWorld.getPlayer().moveUp(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
		}
		//If statement for downward movement of the player designated to key S
		else if (event.getCode() == KeyCode.S) 
		{
			gameWorld.getPlayer().moveDown(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
		}
		//If statement for leftward movement of the player designated to key A.
		else if (event.getCode() == KeyCode.A) 
		{
			gameWorld.getPlayer().moveLeft(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
		}
		//If statement for rightward movement of the player designated to key D.
		else if (event.getCode() == KeyCode.D) 
		{
			gameWorld.getPlayer().moveRight(gameWorld.getTrees(), gameWorld.getLanterns(), gameWorld.getSign());
		}
		//If statement for the Dig action of the player designated to key T.
		else if (event.getCode() == KeyCode.T) 
		{
			// perform check for finding the treasure
			if(gameWorld.getPlayer().dig(gameWorld.getTreasure(), gc))
			{
				foundTreasure = true;
			}
		}
		else if (event.getCode() == KeyCode.E)
		{
			// check if the player is near any lanterns
			Lantern.lightLantern(gc, gameWorld.getTreasure(), gameWorld.getLanterns(), gameWorld.getPlayer(), gameWorld.getPuzzle());
			// check if the player is near the sign
			Sign.readSign(gc, gameWorld.getPlayer(), gameWorld.getSign());			
		}
		return foundTreasure;
	}
}

