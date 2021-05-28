// Import required classes
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 1.0
 * @since 2018-02-23
 * 
 * Promises:
 * Stores the order of which the lanterns are turned on.
 * Checks the order of which the lanterns are turned on and gives the hint.
 * 
 * Requires:
 * Classes: Interactable
 */
public class Puzzle 
{
    // create an array and index which stores the order of turning on the lanterns
    private int[] lanternOrder = new int[4];
    private int index = 0;
    
    /**
     * Stores the order of which lanterns are lit up and performs a check if the order is correct.
     * 
     * @param order The order of the lantern which the player has lit up.
     * 
     * @return 'H' if a hint should be given.
     *         'R' if the lanterns should be reset.
     *         'N' if no action should be taken.
     */
    public char litLantern (int order)
    {
        lanternOrder[index] = order;
        
        // check if all indices are filled up
        if(index == 3)
        {
            // run a check on the order
            if(checkOrder())
            {
                // if order is correct, give a hint
                return 'H';
            }
            else
            {
                // if order is incorrect, reset all the lanterns
                return 'R';
            }
        }
        else
        {
            index++;
            // when not all lanterns are lit up
            return 'N';
        }
    }
    
    /**
     * Performs logic to check if the lanterns are lit up in the proper order.
     * 
     * @return True if order is correct. False otherwise.
     */
    public boolean checkOrder ()
    {
        for(int i = 0; i < 3; i++)
        {
			// lanterns should be lit up in order 1 -> 2 -> 3 -> 4
            if(lanternOrder[i] > lanternOrder[i + 1])
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Setter method for index.
     * 
     * @param i Value to set index.
     */
    public void setIndex (int i)
    {
        index = i;
    }
    
    /**
     * Performs logic to determine which hint should be given to the player.
     * 
     * @param gc Stores parameters to render on the canvas.
     */
    public void giveHint (GraphicsContext gc, Treasure treasure)
    {
		int boardSize = GameParameters.getBoardSize();

        // generate hint based on treasure location in the quadrants
        if(treasure.getX() > boardSize / 2 && treasure.getY() < boardSize / 2)
        {
            Display.hint("NE quadrant", gc);
        }
        else if(treasure.getX() < boardSize / 2 && treasure.getY() < boardSize / 2)
        {
            Display.hint("NW quadrant", gc);
        }
        else if(treasure.getX() < boardSize / 2 && treasure.getY() > boardSize / 2)
        {
            Display.hint("SW quadrant", gc);
        }
        else if(treasure.getX() > boardSize / 2 && treasure.getY() > boardSize / 2)
        {
            Display.hint("SE quadrant", gc);
        }
        else
        {
            Display.hint("between quadrants", gc);
        } 
    }
    
    /**
     * Reset the state of the puzzle. When the player has performed the puzzle incorrectly.
     */
    public void resetPuzzle (ArrayList<Lantern> lanterns)
    {
        for(Lantern lantern : lanterns)
        {
            lantern.resetLantern();
        }
        setIndex(0);
    }
}

