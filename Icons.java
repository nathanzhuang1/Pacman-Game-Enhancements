import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * This class creates the images and associates them to GLOBAL objects to use throughout the program
 */

public class Icons extends JLabel {
	
	// 1. Creating the objects, made them public, static, and finalized for use throughout the program without change
	// A2. Static does not rely on the run-time creation, which means that the words would be stationary,
	// set for the program
	// A3. Final is a keyword that defines a constant, and the walls is all-caps so that readers know that the value
	// is finalized
	public static final ImageIcon WALL = new ImageIcon("images/Wall.bmp");
	public static final ImageIcon FOOD = new ImageIcon("images/Food.bmp");
	public static final ImageIcon BLANK = new ImageIcon("images/Black.bmp");
	public static final ImageIcon DOOR = new ImageIcon("images/Black.bmp");
	public static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");
	public static final ImageIcon CHERRY = new ImageIcon("images/Cherry.bmp");
	public static final ImageIcon SPEED = new ImageIcon("images/speedUp.png");
	
	// A3. Creating pacman characters in a final method: 
	// 0 - Pacman left, 1 - Pacman up, 2 - Pacman right, 3 - Pacman down
	public static final ImageIcon[] PACMAN = {
			new ImageIcon("images/PacMan0.gif"),
			new ImageIcon("images/PacMan1.gif"),
			new ImageIcon("images/PacMan2.gif"),
			new ImageIcon("images/PacMan3.gif"),
			
	};
	
	
	
	// A4. Creating the three ghost creators
	public static final ImageIcon[] GHOST = {
			//test = (int) (Math.random() * 3);
			
					
			new ImageIcon("images/Ghost0.bmp"),
			new ImageIcon("images/Ghost1.bmp"),
			new ImageIcon("images/Ghost2.bmp"),
			new ImageIcon("images/Ghost3.bmp")
			
			/*
			for (int iteration; iteration <= 3; iteration ++) {
				new ImageIcon("images/Ghost" + [iteration] + ".bmp");
			},
			*/
			
	}; //The ghosts represent three different ghosts
	

}
