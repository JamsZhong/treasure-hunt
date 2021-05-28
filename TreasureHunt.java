// import required classes
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 3.0
 * @since 2018-03-16
 * 
 * Promises:
 * Serves as the main function for the game (calls the other classes to start).
 * 
 * Requires:
 * Nothing.
 */
public class TreasureHunt extends Application
{	
	/**
	 * Main function. Should be ignored but can be a fallback incase application cannot launch.
     * 
     * @param args Command line arguments
	 */
	public static void main (String[] args) 
	{
		launch(args);
	}
	
    /**
     * Runs the game:
     *    1) Displays instructions.
     *    2) Creates the displays the original game screen.
     *    3) Gets user input.
     *    4) React to user input using event handling.
     *    5) Repeats steps 2-4 until stopping point.
     *    6) Displays ending.
     * 
     * @param primaryStage Stage created from javafx application thread.
     */
    @Override
	public void start (Stage primaryStage) throws Exception
	{	
		// create gameWorld, this will also initialize all variables/instances.
		World gameWorld = new World();
		
		primaryStage.setTitle("Treasure Hunt");
		
		// objects used forthe game loop
		Group root = new Group();
		Scene scene = new Scene(root);
		
		Canvas canvas = new Canvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.loadFont("file:fff-forward.regular.ttf", 8));
		
		root.getChildren().add(canvas);

		// use event handling to manage user input and loop steps 2-4
		AnimationTimer animator = new AnimationTimer() 
		{
			@Override
			public void handle(long currentNanoTime)
			{	
				// update the screen
				Display.gameScreen(primaryStage, gameWorld, this, scene, gc, canvas);
				
				
				//Moves enemy
				gameWorld.getEnemy().move(System.nanoTime(), gameWorld);
				
				//Exits if enemy contacts player
				if(gameWorld.getEnemy().lose(gameWorld.getPlayer()))
				{
					this.stop();
					Display.gameOver(primaryStage);
				}
			}
		};
		
		//Adds Event Handler to the scene. This allows the scene to respond to user keyboard inputs.
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{			
			@Override
			public void handle(KeyEvent event) 
			{
				// stops if the player has found the treasure
				if(Input.playerInput(event, gameWorld, gc))
				{
					animator.stop();
					Display.ending(primaryStage);
				}
			}
		});
		
		// Shows start menu
		Display.start(primaryStage, gameWorld, animator);
		

	}
}

