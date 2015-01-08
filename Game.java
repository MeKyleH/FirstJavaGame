package com.whoiskyleholt;

import java.awt.Canvas; //blank rectangular area
import java.awt.Color; //allows to use colors
import java.awt.Dimension; //imports dimension class for the screen size
import java.awt.Font;
import java.awt.Graphics; //allows for buffering to show to the screen
import java.awt.image.BufferStrategy; // allows for buffering on rendering
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame; //for use in the window
import com.whoiskyleholt.entity.mob.Player;
import com.whoiskyleholt.graphics.Screen; //allows us to use the screen class which is a separate file
import com.whoiskyleholt.graphics.Sprite;
import com.whoiskyleholt.graphics.SpriteSheet;
import com.whoiskyleholt.input.KeyBoard;
import com.whoiskyleholt.input.Mouse;
import com.whoiskyleholt.level.Level;
import com.whoiskyleholt.level.TileCoordinate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	// creates three variables for height, width and scaling for later use
	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	public static String Title = "Rain";

	// creates variables for the game to use
	private Thread thread; // used for new threads
	private boolean running = false; // says whether the game is running or not
	private JFrame frame; // creates a JFrame object (window)
	private KeyBoard key;
	private Screen screen; // refers to the screen object
	private Level level;
	private Player player;

	// creates variables for us to create and then access the image
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // creates an image with an accessible buffer
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // getRaster gets the pixels in the image and their color and allows us to modify the colors

	public Game() {// constructor so doesn't need return type (i.e. void)
		Dimension size = new Dimension(width * scale, height * scale); // creates value for size of the window
		setPreferredSize(size); // sets preferred size to the window

		screen = new Screen(width, height); // sets width/height in screen class equal to our width/height in the Game class
		frame = new JFrame(); // sets frame = to JFrame to be an instance

		// adds information for keyboard and mouse
		key = new KeyBoard();
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(19, 62);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key); // creates a player with the desired spawn location - can do (width/2 or height/2)
		player.init(level);

	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	// creates method for creating new threads for multiple processes
	public synchronized void start() { // allows multiple threads to run simultaneously without interfering with each other
		running = true; // used to start the program run method
		thread = new Thread(this, "Display"); // makes it so the new thread contains the Game class, and sets the name as "Display"
		thread.start(); // creates the new thread

	}

	// creates a stop method for the game
	public synchronized void stop() {
		running = false; // makes the game stop running and stops the run method
		try {
			thread.join(); // joins all threads together when the game is closed
		} catch (InterruptedException e) { // if not it creates an exception to close it
			e.printStackTrace();
		}
	}

	// called by the start method, and says what the game will do
	public void run() { // is called because we have implemented Runnable and as soon as we create a thread it is running
		// create variables for the program to use upon launch
		long lastTime = System.nanoTime();// big number uses long initially stores as the current time of the system
		final double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis(); // to be used as a 1 second timer
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus(); // may be frame.requestFocus() for higher java platforms
		while (running) { // continues everything below while it is running
			// calculates time variables for frame rate calculations
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++; // counts how often we are rendering per second
				delta--; // returns delta to 0
			}
			render(); // displays the images to the screen
			frames++; // adds 1 to frame each time it renders
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(Title + " " + updates + " ups, " + frames
						+ " fps");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	// handles game logic
	public void update() {
		// map movement
		key.update();
		player.update(); // allows the player to move
		level.update();
	}

	// displays images on the screen
	public void render() {
		BufferStrategy bs = getBufferStrategy(); // gets a buffer strategy object to allow us to buffer
		if (bs == null) { // allows us to only create the buffer strategy the first time and not every time the game is run
			createBufferStrategy(3); // creates triple buffering
			return;
		}

		// clears the old screen before the new screen is displayed
		screen.clear();
		// centers the player on the screen
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		// screen.renderSheet(40, 40, SpriteSheet.player_down, false);

		// allows us to set non-uniform sprites or moving graphics (i.e. menu or portals)
		Sprite sprite = new Sprite(10, 10, 0x2200ff);
		Random random = new Random();
		for (int i = 0; i < 35; i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			screen.renderSprite(300 + x, 825 + y, sprite, true);

		}

		// uses the screen render method to change the pixel color
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i]; // sets each pixel to the new value in the screen render color
		}

		// begins accepting data for rendering
		Graphics g = bs.getDrawGraphics(); // creates a link between the buffer and drawing to the screen
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // draws the new image from screen to the screen.
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		// g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64); // sets rectangle at the mouse on the selected tile.
		// g.drawString("PLAYER: X: " + player.x + ", Y: " + player.y + ", Mouse: " + Mouse.getB(), 25, 400);
		g.dispose(); // deletes the current graphic to free up system resources
		bs.show(); // buffer swapping 3rd buffer becomes 2nd buffer so a new screen can be rendered
	}

	public static void main(String[] args) { // where the program starts running
		// sets needed parameters for the window to open and appear
		Game game = new Game(); // creates a game object
		game.frame.setResizable(false); // prevents window from resizing due to graphical errors **MUST BE THE FIRST THING YOU DO IN THE MAIN METHOD
		game.frame.setTitle(Game.Title); // sets window title
		game.frame.add(game); // fills the window with an instance of our game class as a subtype of the canvas
		game.frame.pack(); // sizes the window resolution to be the window dimensions(size) that we listed earlier
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the application when you click the X at the top of the window ** stops the game from running after the X is clicked
		game.frame.setLocationRelativeTo(null); // centers our window upon opening
		game.frame.setVisible(true); // makes the window visible on the screen

		// begins running the program in the window
		game.start();

	}

}
