// Import required classes
import javafx.scene.canvas.GraphicsContext;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 2.0
 * @since 2018-03-12
 * 
 * Promises:
 * Stores messages to be displayed to the player.
 * 
 * Requires:
 * Classes: Interactable
 */
public class Sign extends Interactable 
{
	// variable store the message which is displayed on the sign.
	private String message;
	
	/**
	 * Constructor for sign, initializes location and message to be displayed on the sign.
	 * 
	 * @param message The message to be displayed on the sign.
	 * @param boardSize Height and Width of the game board in number of tiles.
	 */
	public Sign (String message)
	{
		// make the location of the sign at the map center
		super(GameParameters.getBoardSize() / 2, GameParameters.getBoardSize() / 2, GameParameters.getSignSpritePath());
		this.message = message;
	}
	
	/**
     * Performs Logic to determine if the player is close enough to read the sign.
     * 
     * @param gc Stores parameters to render on the canvas.
     */
    public static void readSign (GraphicsContext gc, Player player, Sign sign)
    {
        if(Math.abs(player.getX() - sign.getX()) == 0 && Math.abs(player.getY() - sign.getY()) == 1 || Math.abs(player.getX() - sign.getX()) == 1 && Math.abs(player.getY() - sign.getY()) == 0)
        {
            Display.readSign(sign, gc);
        }
    }
    
    /**
     * Getter method for the message on the sign.
     * 
     * @return Message on the sign.
     */
    public String getMessage ()
    {
        return message;
    }
}

