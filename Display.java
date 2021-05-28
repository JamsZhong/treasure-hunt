// import required classes
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;

/**
 * @authors Edward Song, James Zhong, John Dagsa, Riley Sawyer 
 * @version 5.0
 * @since 2018-03-15
 * 
 * Promises:
 * Serves as a utility class for Display related tasks:
 * 1) Prints start menu for the game
 * 2) Prints an instructions menu for the game
 * 3) Prints a settings menu for the game
 * 2) Prints out a grid (board) for the game.
 * 3) Prints message to the game board
 * 4) Prints ending screens for the game.
 * 
 * Requires:
 * Class to be ran by the main function.
 * User Input.
 */
public class Display 
{
	// variable used to determine which menu items the user is presently selecting
	// initialized at 300 so that the modulus operator will return a positive result
	private static int selection = 300;
	
	/**
     * Displays the start menu for the game.
     * 
     * @param primaryStage Stage created from javafx application thread.
     * @param gameWorld Contains all objects in the game.
     * @param animator A timer which allows for animation in java.
     */
	public static void start (Stage primaryStage, World gameWorld, AnimationTimer animator)
	{	
		// Treasure Hunt title and effects
		Label title = new Label("Treasure Hunt");
		title.setFont(Font.loadFont("file:fff-forward.regular.ttf", 48));
		
		// start title and effects
		Label start = new Label("Start");
		// set blue for initial selection
		start.setTextFill(Color.BLUE);
		start.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		
		// instructions title and effects
		Label instructions = new Label("Instructions");
		instructions.setTextFill(Color.BLACK);
		instructions.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		
		// settings title and effects
		Label settings = new Label("Settings");
		settings.setTextFill(Color.BLACK);
		settings.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		
		// HBox containing menu items
		HBox textOptions = new HBox(30);
		textOptions.setAlignment(Pos.CENTER);
		textOptions.getChildren().addAll(start, instructions, settings);
		
		// navigation instructions and related formatting
		Label select = new Label("Press ENTER to select an option\nMove with WASD");
		select.setFont(Font.loadFont("file:fff-forward.regular.ttf", 10));	
		select.setTextAlignment(TextAlignment.CENTER);
		HBox selectBox = new HBox();
		selectBox.setPadding(new Insets(100, 0, 0, 0));
		selectBox.setAlignment(Pos.CENTER);
		selectBox.getChildren().add(select);	
		
		// VBox containing all text items
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(130, 0, 0, 0));
		layout.getChildren().addAll(title, textOptions, selectBox);
		layout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		Scene startS = new Scene(layout, 500, 500, Color.GRAY);
		// event handler for keyboard input
		startS.setOnKeyPressed(event ->
			{
				// move selection left, wraps around due to modulus
				if (event.getCode() == KeyCode.A)
				{
					// ensure selection variable is always positive
					if(selection == 0)
						selection = 3 * 100;
					selection--;
				}
				// move selection right, wraps around due to modulus
				else if(event.getCode() == KeyCode.D)
				{
					// ensure selection variable does not get too large
					if(selection == 900)
						selection = 3 * 100;
					selection++;
				}
				// makes selection
				else if(event.getCode() == KeyCode.ENTER)
				{
					// %3 as there are three menu buttons
					switch(Math.abs(selection) % 3) {
						// start is selected, start the game loop
						case 0: animator.start();
								break;
						// instructions is selected, move to the instructions menu
						// selection = 200 as there are two menu options in that window
						case 1: selection = 2 * 100;
								Display.instructions(primaryStage, gameWorld, animator);
								break;
						// settings is selected, move to the settings menu
						// selection = 300 as there are three menu options in that window
						case 2: selection = 3 * 100;
								Display.settings(primaryStage, gameWorld, animator);
								break;
						default: break;
					}
				}
				// highlights menus button based on selection variable
				// %3 due to 3 menu buttons
				switch(Math.abs(selection) % 3) {
					// highlight start button
					case 0: start.setTextFill(Color.BLUE);
							instructions.setTextFill(Color.BLACK);
							settings.setTextFill(Color.BLACK);
							break;
					// highlight instructions button
					case 1: start.setTextFill(Color.BLACK);
							instructions.setTextFill(Color.BLUE);
							settings.setTextFill(Color.BLACK);
							break;
					// highlight Settings button
					case 2: start.setTextFill(Color.BLACK);
							instructions.setTextFill(Color.BLACK);
							settings.setTextFill(Color.BLUE);
							break;
					default: break;
				}
			});
		
		// sets scene and opens the GUI window
		primaryStage.setScene(startS);		
		primaryStage.show();
	}
	
	/**
	 * Displays instructions to the user.
	 * 
	 * @param primaryStage Stage created from javafx application thread.
     * @param gameWorld Contains all objects in the game.
     * @param animator A timer which allows for animation in java.
     */
	public static void instructions (Stage primaryStage, World gameWorld, AnimationTimer animator)
	{
		// instruction title and effects
		Label title = new Label("Instructions");
		title.setTextFill(Color.BLACK);
		title.setFont(Font.loadFont("file:fff-forward.regular.ttf", 40));
		
		// goal title and effects
		Label goal = new Label("Your goal is to find the treasure hidden in the map by digging, while avoiding the enemy");
		goal.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		goal.setPrefWidth(475);
		goal.setWrapText(true);
		
		// legend title and effects
		Label legend = new Label("Legend");
		legend.setTextFill(Color.BLACK);
		legend.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		
		// you title, associated sprite, and layout
		ImageView playerSprite = new ImageView(gameWorld.getPlayer().getSprite().getImg());
		playerSprite.setFitWidth(25);
		playerSprite.setPreserveRatio(true);
        playerSprite.setSmooth(true);
        playerSprite.setCache(true);
		Rectangle red = new Rectangle(20, 20, Color.RED);
		Label you = new Label("You");
		you.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		HBox youBox = new HBox(10);
		youBox.getChildren().addAll(playerSprite, you);
		
		// enemy title, associated sprite, and layout
		ImageView enemySprite = new ImageView(gameWorld.getEnemy().getSprite().getImg());
		enemySprite.setFitWidth(25);
		enemySprite.setPreserveRatio(true);
        enemySprite.setSmooth(true);
        enemySprite.setCache(true);
		Label enemy = new Label("Enemy");
		enemy.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		HBox enemyBox = new HBox(10);	
		enemyBox.getChildren().addAll(enemySprite, enemy);	
		
		// tree title, associated sprite, and layout
		ImageView treeSprite = new ImageView(gameWorld.getTrees().get(0).getSprite().getImg());
		treeSprite.setFitWidth(25);
		treeSprite.setPreserveRatio(true);
        treeSprite.setSmooth(true);
        treeSprite.setCache(true);
		Label tree = new Label("Tree");
		tree.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		HBox treeBox = new HBox(10);	
		treeBox.getChildren().addAll(treeSprite, tree);	
		
		// sign title, associated sprite, and layout
		ImageView signSprite = new ImageView(gameWorld.getSign().getSprite().getImg());
		signSprite.setFitWidth(25);
		signSprite.setPreserveRatio(true);
        signSprite.setSmooth(true);
        signSprite.setCache(true);
		Label sign = new Label("Sign");
		sign.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		HBox signBox = new HBox(10);	
		signBox.getChildren().addAll(signSprite, sign);	
		
		// lantern title, associated sprite, and layout
		ImageView lanternSprite = new ImageView(gameWorld.getLanterns().get(0).getSprite().getImg());
		lanternSprite.setFitWidth(25);
		lanternSprite.setPreserveRatio(true);
        lanternSprite.setSmooth(true);
        lanternSprite.setCache(true);
		Label lantern = new Label("Lantern");
		lantern.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		HBox lanternBox = new HBox(10);	
		lanternBox.getChildren().addAll(lanternSprite, lantern);	
		
		// last dig title, associated sprite, and layout
		ImageView lastDigSprite = new ImageView(gameWorld.getPlayer().getLastDig().getSprite().getImg());
		lastDigSprite.setFitWidth(25);
		lastDigSprite.setPreserveRatio(true);
        lastDigSprite.setSmooth(true);
        lastDigSprite.setCache(true);
		Label lastDig = new Label ("Closest Dig");
		lastDig.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		HBox lastDigBox = new HBox(10);	
		lastDigBox.getChildren().addAll(lastDigSprite, lastDig);	
		
		// layouts containing all objects in the legend
		HBox objectsLine1 = new HBox(25);
		objectsLine1.setPadding(new Insets(20, 0, 0, 0));
		objectsLine1.setAlignment(Pos.CENTER_LEFT);
		objectsLine1.getChildren().addAll(youBox, enemyBox, lastDigBox);
		HBox objectsLine2 = new HBox(25);
		objectsLine2.setPadding(new Insets(15, 0, 15, 0));
		objectsLine2.setAlignment(Pos.CENTER_LEFT);
		objectsLine2.getChildren().addAll(treeBox, signBox, lanternBox);
		VBox legendAndObjects = new VBox();
		legendAndObjects.getChildren().addAll(legend, objectsLine1, objectsLine2);
		legendAndObjects.setPadding(new Insets(15, 0, 0, 0));
		legendAndObjects.setAlignment(Pos.CENTER_LEFT);
		
		// controls title and effects
		Label controls = new Label("Controls");
		controls.setTextFill(Color.BLACK);
		controls.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		
		// move title and associated image
		Label move = new Label("Move   ");
		move.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		ImageView wasd = new ImageView(new Image("wasd.png"));
        wasd.setFitWidth(95);
		wasd.setPreserveRatio(true);
        wasd.setSmooth(true);
        wasd.setCache(true);
        
        // dig title and associated image
		Label dig = new Label("Dig   ");
		dig.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		ImageView t = new ImageView(new Image("t.png"));
        t.setFitWidth(30);
		t.setPreserveRatio(true);
        t.setSmooth(true);
        t.setCache(true);
        
        // interact title and associated image
		Label interact = new Label("Interact");
		interact.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		ImageView e = new ImageView(new Image("e.png"));
        e.setFitWidth(30);
		e.setPreserveRatio(true);
        e.setSmooth(true);
        e.setCache(true);
        
        // layouts containing all the controls
		HBox keybinds = new HBox(10);
		keybinds.setAlignment(Pos.CENTER_LEFT);
		keybinds.getChildren().addAll(wasd, move, t, dig, e, interact);
		VBox controlsAndKeybinds = new VBox(15);
		controlsAndKeybinds.getChildren().addAll(controls, keybinds);
		controlsAndKeybinds.setAlignment(Pos.CENTER_LEFT);		
		
		// start menu button and effects
		Label start = new Label ("Start");
		start.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		// blue due to initial selection
		start.setTextFill(Color.BLUE);
		
		// back menu button and effects
		Label back = new Label("Back");
		back.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		back.setTextFill(Color.BLACK);
		
		// layout for menu buttons
		HBox options = new HBox(30);
		options.setAlignment(Pos.CENTER);
		options.setPadding(new Insets(25, 0, 0, 0));
		options.getChildren().addAll(start, back);
		
		// layout for all items on screen
		VBox layout = new VBox();
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(20, 15, 0, 15));
		layout.getChildren().addAll(title, goal, legendAndObjects, controlsAndKeybinds, options);
		layout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		Scene instructionsS = new Scene(layout, 500, 500, Color.GRAY);
		//event handler for keyboard input
		instructionsS.setOnKeyPressed(event ->
			{
				// move selection left, wraps around due to modulus
				if (event.getCode() == KeyCode.A)
				{
					// ensure selection variable is always positive
					if(selection == 0)
						selection = 2 * 100;
					selection--;
				}
				// move selection right, wraps around due to modulus
				else if(event.getCode() == KeyCode.D)
				{
					// ensure selection variable does not get too large
					if(selection == 600)
						selection = 2 * 100;
					selection++;
				}
				// makes selection
				else if(event.getCode() == KeyCode.ENTER)
				{
					switch(Math.abs(selection) % 2) {
						// start is selected, start the game loop
						case 0: animator.start();
						// back is selected, move to the start menu
						// selection = 300 as there are three menu options in that window
						case 1: selection = 3 * 100;
								Display.start(primaryStage, gameWorld, animator);
								break;
						default: break;
					}
				}
				// highlights menus button based on selection variable
				// %2 due to 2 menu buttons
				switch(Math.abs(selection) % 2) {
					// highlight start button
					case 0: start.setTextFill(Color.BLUE);
							back.setTextFill(Color.BLACK);
							break;
					// highlight back button
					case 1: start.setTextFill(Color.BLACK);
							back.setTextFill(Color.BLUE);
							break;
					default: break;
				}
			});
			
			primaryStage.setScene(instructionsS);
	}
	
	/**
	 * Displays the settings menu to the user and allows for the user to change settings.
	 * 
	 * @param primaryStage Stage created from javafx application thread.
     * @param gameWorld Contains all objects in the game.
     * @param animator A timer which allows for animation in java.
     */
	public static void settings (Stage primaryStage, World gameWorld, AnimationTimer animator)
	{
		// settings title and effects
		Label title = new Label("Settings");
		title.setFont(Font.loadFont("file:fff-forward.regular.ttf", 40));
		title.setTextFill(Color.BLACK);
		
		
		// titles and layouts for trees options
		Label trees = new Label("Number of Trees  ");
		// blue for initial selection
		trees.setTextFill(Color.BLUE);
		trees.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		Label numTrees = new Label(Integer.toString(GameParameters.getNumberTrees()));
		numTrees.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		Label leftArrowTrees = new Label("<");
		leftArrowTrees.setTextFill(Color.BLACK);
		leftArrowTrees.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		Label rightArrowTrees = new Label(">");
		rightArrowTrees.setTextFill(Color.BLACK);
		rightArrowTrees.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		// ensure that arrows are invisible if settings are maximum or minimum
		if(GameParameters.getNumberTrees() == 0)
		{
			leftArrowTrees.setTextFill(Color.TRANSPARENT);
		}
		else if(GameParameters.getNumberTrees() >= GameParameters.getBoardSize() / 2)
		{
			rightArrowTrees.setTextFill(Color.TRANSPARENT);
		}
		HBox treeBox = new HBox(15);
		treeBox.getChildren().addAll(trees, leftArrowTrees, numTrees, rightArrowTrees);		
				
		// titles and layouts for map size
		Label size = new Label("Map size  ");
		size.setTextFill(Color.BLACK);
		size.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		Label numTiles = new Label(Integer.toString(GameParameters.getBoardSize()));
		numTiles.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		Label leftArrowTiles = new Label("<");
		leftArrowTiles.setTextFill(Color.BLACK);
		leftArrowTiles.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		Label rightArrowTiles = new Label(">");
		rightArrowTiles.setTextFill(Color.BLACK);
		rightArrowTiles.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		// ensure that arrows are invisible if settings are maximum or minimum
		if(GameParameters.getBoardSize() <= GameParameters.getNumberTrees() * 2 || GameParameters.getBoardSize() <= 15)
		{
			leftArrowTiles.setTextFill(Color.TRANSPARENT);
		}
		else if(GameParameters.getBoardSize() == 51)
		{
			rightArrowTiles.setTextFill(Color.TRANSPARENT);
		}
		HBox tileBox = new HBox(15);
		tileBox.getChildren().addAll(size, leftArrowTiles, numTiles, rightArrowTiles);
		
		// back menu button title and effects
		Label back = new Label("Back");
		back.setTextFill(Color.BLACK);
		back.setFont(Font.loadFont("file:fff-forward.regular.ttf", 20));
		
		// VBox containing all text on screen
		VBox layout = new VBox(25);
		layout.setPadding(new Insets(20, 15, 0, 15));
		layout.setAlignment(Pos.TOP_CENTER);
		layout.getChildren().addAll(title, treeBox, tileBox, back);
		layout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		Scene settingsS = new Scene(layout, 500, 500);
		// event handler for keyboard
		settingsS.setOnKeyPressed(event ->
			{
				// moves selection up, wraps around
				if (event.getCode() == KeyCode.W)
				{
					// ensure selection variable is always positive
					if(selection == 0)
						selection = 3 * 100;
					selection--;
				}
				// moves selection down, wraps around
				else if(event.getCode() == KeyCode.S)
				{
					// ensure selection variable does not get too large
					if(selection == 600)
						selection = 3 * 100;
					selection++;
				}
				// decreases number of trees or board size depending on selection
				else if(event.getCode() == KeyCode.A)
				{
					switch(Math.abs(selection) % 3) {
						// trees are selected
						// remove a tree unless there are no trees left
						case 0: if(GameParameters.getNumberTrees() == 0)
								{
									break;
								}
								else
								{
									gameWorld.removeTree();
									// update title
									numTrees.setText(Integer.toString(GameParameters.getNumberTrees()));
									// ensure left arrow is visible since the number of trees can be increased if the command goes through
									rightArrowTrees.setTextFill(Color.BLACK);
									break;
								}
						// board size is selected
						// minimum board size of 15, or twice the number of trees
						case 1: if(GameParameters.getBoardSize() <= GameParameters.getNumberTrees() * 2 || GameParameters.getBoardSize() <= 15)
								{
									break;
								}
								else
								{
									gameWorld.decreaseBoardSize();
									// update title
									numTiles.setText(Integer.toString(GameParameters.getBoardSize()));
									// ensure right arrow is visible since the board size can be increased if the command goes through
									rightArrowTiles.setTextFill(Color.BLACK);									
									break;
								} 
						default: break;
					}
				}
				// increases number of trees or board size depending on selection
				else if(event.getCode() == KeyCode.D)
				{
					switch(Math.abs(selection) % 3) {
						// trees are selected
						// add a tree, maximum number of trees is half the board size
						case 0:	if(GameParameters.getNumberTrees() >= GameParameters.getBoardSize() / 2)
								{
									break;
								}
								else
								{
									gameWorld.addTree();
									// update title
									numTrees.setText(Integer.toString(GameParameters.getNumberTrees()));
									// ensure right arrow is visible since the number of trees can be increased if the command goes through
									leftArrowTrees.setTextFill(Color.BLACK);
									break;
								}
						// board size is selected
						// increase board size, maximum size is 51 x 51
						case 1: if(GameParameters.getBoardSize() == 51)
								{
									break;
								}
								else
								{
									gameWorld.increaseBoardSize();
									// update title
									numTiles.setText(Integer.toString(GameParameters.getBoardSize()));
									// ensure right arrow is visible since the number of trees can be increased if the command goes through
									leftArrowTiles.setTextFill(Color.BLACK);
									break;
								} 
						default: break;
					}
				}
				// makes a selection
				else if(event.getCode() == KeyCode.ENTER)
				{
					// back is selected
					if(Math.abs(selection) % 3 == 2)
					{
						// selection = 300 as there are 3 menu options in the window
						selection = 3 * 100;
						Display.start(primaryStage, gameWorld, animator);
					}
				}
				switch(Math.abs(selection) % 3) {
					// trees are selected
					case 0: trees.setTextFill(Color.BLUE);
							size.setTextFill(Color.BLACK);
							back.setTextFill(Color.BLACK);
							// make left arrow invisible for minimum number of trees
							if(GameParameters.getNumberTrees() == 0)
							{
								leftArrowTrees.setTextFill(Color.TRANSPARENT);
							}
							// make right arrow invisible for maximum number of trees
							else if(GameParameters.getNumberTrees() >= GameParameters.getBoardSize() / 2)
							{
								rightArrowTrees.setTextFill(Color.TRANSPARENT);
							}
							// make both arrows visible
							else
							{
								leftArrowTrees.setTextFill(Color.BLACK);				
								rightArrowTrees.setTextFill(Color.BLACK);					
							}
							// make left tile arrow visible if number of trees is decreased so that the board size can be decreased
							if(GameParameters.getBoardSize() > GameParameters.getNumberTrees() * 2 && GameParameters.getBoardSize() > 15)
								leftArrowTiles.setTextFill(Color.BLACK);
							break;
					// tiles are selected
					case 1: trees.setTextFill(Color.BLACK);
							size.setTextFill(Color.BLUE);
							back.setTextFill(Color.BLACK);
							// make left tile arrow visible for minimum board size
							if(GameParameters.getBoardSize() <= GameParameters.getNumberTrees() * 2 || GameParameters.getBoardSize() <= 15)
							{
								leftArrowTiles.setTextFill(Color.TRANSPARENT);
							}
							// make right tile arrow visible for maximum board size
							else if(GameParameters.getBoardSize() == 51)
							{
								rightArrowTiles.setTextFill(Color.TRANSPARENT);
							}
							// make both arrows visible
							else
							{
								leftArrowTiles.setTextFill(Color.BLACK);				
								rightArrowTiles.setTextFill(Color.BLACK);		
							}
							// make right tree arrow visible if the board size is increased so that the number of trees can be increased
							if(GameParameters.getNumberTrees() < GameParameters.getBoardSize() / 2)
								rightArrowTrees.setTextFill(Color.BLACK);
							break;
					// back is selected
					case 2: trees.setTextFill(Color.BLACK);
							size.setTextFill(Color.BLACK);
							back.setTextFill(Color.BLUE);
							break;
					default: break;
				}
			});
		primaryStage.setScene(settingsS);
	}
	
	/**
     * Outputs the gameboard to the screen.
     * 
     * @param primaryStage Stage created from javafx application thread.
     * @param gameWorld Contains all objects in the game.
     * @param animator A timer which allows for animation in java.
     * @param scene Game board scene
     * @param gc GraphicsContext variable from JavaFX which will be used to draw the game board
     * @param canvas Canvas variable from JavaFX
     */
    public static void gameScreen (Stage primaryStage, World gameWorld, AnimationTimer animator, Scene scene, GraphicsContext gc, Canvas canvas)
    {
		// create local variables/references for ease of reading
		int boardSize = GameParameters.getBoardSize();
		int tileSize = GameParameters.getTileSize();
		ArrayList<Tree> trees = gameWorld.getTrees();
		Player player = gameWorld.getPlayer();
		Enemy enemy = gameWorld.getEnemy();
		ArrayList<Lantern> lanterns = gameWorld.getLanterns();
		Sign sign = gameWorld.getSign();
		Sprite grass = gameWorld.getGrass();
		
		// Outer loop prints row by row (every line)
		for(int row = 0; row < boardSize; row++)  
		{
		
			//Designate point for continue statement to return to to skip printing of blank tiles (ground tiles)
			innerloop:
		
			//Inner loop prints column by column (every character)
			for(int column = 0; column < boardSize; column++)  
			{

				// Checks if the current position is occupied by the player
				if (column == player.getX() && row == player.getY())  
				{
					// if the player is facing left
					if (player.getDirection())
					{
						gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
						gc.drawImage(player.getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
						continue innerloop;
					}
					else
					{
						gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
						gc.drawImage(player.getSprite().getImg(), (column + 1) * tileSize, row * tileSize, -tileSize, tileSize);
						continue innerloop;
					}
				}
				
				// Checks if current position is occupied by the enemy
				else if (column == enemy.getX() && row == enemy.getY())  
				{
					// if enemy is facing left
					if (enemy.getDirection())
					{
						gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
						gc.drawImage(enemy.getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
						continue innerloop;
					}
					else
					{
						gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
						gc.drawImage(enemy.getSprite().getImg(), (column + 1) * tileSize, row * tileSize, -tileSize, tileSize);
						continue innerloop;
					}
				}
				
				// Checks if the current position is occupied by the closest dig location
				else if (column == player.getLastDig().getX() && row == player.getLastDig().getY())  
				{
					gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
					gc.drawImage(player.getLastDig().getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
					continue innerloop;
				}
                
                // Checks if current position is occupied by the sign
                else if (column == sign.getX() && row == sign.getY())
                {
                    gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
                    gc.drawImage(sign.getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
                    continue innerloop;
                }
				
				// Checks if the current position is occupied by a tree or lantern
				else 
				{
					// Check for tree
					for(Tree tree : trees)  
					{

						if (column == tree.getX() && row == tree.getY())  
						{
							gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
							gc.drawImage(tree.getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
							continue innerloop;
						}

					}
                    
                    // Checks for lantern
                    for(Lantern lantern : lanterns)
                    {
                        if (column == lantern.getX() && row == lantern.getY())
                        {
                            if(lantern.getStatus() == "off")
                            {
                                gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
                                gc.drawImage(lantern.getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
                                continue innerloop;
                            }
                            else
                            {
                                gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
                                gc.drawImage(lantern.getSprite().getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
                                continue innerloop;
                            }
                        }
                    }
                }
                
				// Spaces with no visible objects
				gc.drawImage(grass.getImg(), column * tileSize, row * tileSize, tileSize, tileSize);
			}
		}
		
		// set the window size for the game, include additional space to output text
		canvas.setWidth(GameParameters.getBoardSize() * GameParameters.getTileSize());
		canvas.setHeight(GameParameters.getBoardSize() * GameParameters.getTileSize() + GameParameters.getFontSize() * 5 + 5);
		
		primaryStage.setScene(scene);
	}
    
    /**
     * Outputs the sign text to GraphicsContext.
     * 
     * @param sign Stores message of the sign.
     * @param gc Stores parameters to render on the canvas.
     * @param boardSize Number of tiles for height and width for the board grid.
     * @param tileSize Height and width in pixels for each tile of the board.
     */
    public static void readSign (Sign sign, GraphicsContext gc)
    {
		int boardSize = GameParameters.getBoardSize();
		int tileSize = GameParameters.getTileSize();
		int fontSize = GameParameters.getFontSize();
		
		// calculate distance to display text from
		int yDistance = boardSize * tileSize + fontSize;
		
        gc.setFill(Color.BLACK);
        // fontSize added to space away from game board
        gc.fillText(sign.getMessage(), 5, yDistance);
    }
    
    /**
     * Outputs the hint to GraphicsContext.
     * 
     * @param quadrant A string of the quadrant which the treasure is located in.
     * @param gc Stores parameters to render on the canvas.
     * @param boardSize Number of tiles for height and width for the board grid.
     * @param tileSize Height and width in pixels for each tile of the board.
     */
    public static void hint (String quadrant, GraphicsContext gc)
    {
		int boardSize = GameParameters.getBoardSize();
		int tileSize = GameParameters.getTileSize();
		int fontSize = GameParameters.getFontSize();
		
        // calculate distance to display text from
		int yDistance = boardSize * tileSize + fontSize * 3;
        
        gc.setFill(Color.BLACK);
        // 2*fontSize added to space away from game board and sign text
        gc.fillText("Look in: " + quadrant, 5, yDistance);
    }
    
    /**
	 * Prints a message saying the player has finished the game
	 */
	public static void ending (Stage primaryStage) 
	{
		// congratulations messages
		Label title = new Label("CONGRATULATIONS");
		title.setFont(Font.loadFont("file:fff-forward.regular.ttf", 32));
		title.setTextFill(Color.BLACK);
		Label message = new Label("You have found the treasure");
		message.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		Label exit = new Label("Press any key to exit");
		exit.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		
		// VBox containing all text
		VBox layout = new VBox(10);
		layout.setPrefSize(500, 500);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(title, message, exit);
		layout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		Scene end = new Scene(layout, 500, 500);
		primaryStage.setScene(end);
		// exit game upon any key press
		end.setOnKeyPressed(event ->
			System.exit(1));
	}
	
	/**
	 * Prints a message saying the enemy has caught the player.
	 */
	public static void gameOver (Stage primaryStage)
	{
		// game over messages
		Label title = new Label("GAME OVER");
		title.setFont(Font.loadFont("file:fff-forward.regular.ttf", 32));
		title.setTextFill(Color.BLACK);
		Label message= new Label("You died");
		message.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		Label exit = new Label("Press any key to exit");
		exit.setFont(Font.loadFont("file:fff-forward.regular.ttf", 14));
		
		// VBox containing all text
		VBox layout = new VBox(10);
		layout.setPrefSize(500, 500);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(title, message, exit);
		layout.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		Scene gameOver = new Scene(layout, 500, 500);
		primaryStage.setScene(gameOver);
		// exit game upon any key press
		gameOver.setOnKeyPressed(event ->
			System.exit(1));
	}
	
	/**
	 * Prints a message saying the player has moved closer to the treasure.
	 */
	public static void closer (GraphicsContext gc)
	{
		int boardSize = GameParameters.getBoardSize();
		int tileSize = GameParameters.getTileSize();
		int fontSize = GameParameters.getFontSize();
		
		// calculate distance to display text from
		int yDistance = boardSize * tileSize + fontSize * 5;
		
        // clear feedback area
        gc.clearRect(0, yDistance - fontSize, boardSize * tileSize, fontSize);
        gc.setFill(Color.BLACK);
        // 3*fontSize added to space away from game board, sign text, and hint text
        gc.fillText("You're getting closer to the treasure!", 5, yDistance);
	}
	
	/**
	 * Prints a mesage saying the player has moved further away from the treasure.
	 */
	public static void further (GraphicsContext gc)
	{
		int boardSize = GameParameters.getBoardSize();
		int tileSize = GameParameters.getTileSize();
		int fontSize = GameParameters.getFontSize();
		
		// calculate distance to display text from
		int yDistance = boardSize * tileSize + fontSize * 5;
		
        // clear feedback area
        gc.clearRect(0, yDistance - fontSize, boardSize * tileSize, fontSize);
        gc.setFill(Color.BLACK);
        // 3*fontSize added to space away from game board, sign text, and hint text
        gc.fillText("You're getting further from the treasure.", 5, yDistance);
	}
	
	/**
	 * Prints a message saying the player is the same distance away from the treasure.
	 */
	public static void sameDistance (GraphicsContext gc)
	{
		int boardSize = GameParameters.getBoardSize();
		int tileSize = GameParameters.getTileSize();
		int fontSize = GameParameters.getFontSize();
		
		// calculate distance to display text from
		int yDistance = boardSize * tileSize + fontSize * 5;
		
        // clear feedback area
        gc.clearRect(0, yDistance - fontSize, boardSize * tileSize, fontSize);
        gc.setFill(Color.BLACK);
        // 3*fontSize added to space away from game board, sign text, and hint text
        gc.fillText("You're exactly the same distance away.", 5, yDistance);
	}
}
