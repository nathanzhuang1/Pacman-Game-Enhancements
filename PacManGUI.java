import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")

/*
 * A(2) - This class creates the frame that would hold the game (board).
 */

// ~ A2.1 - This class creates a frame, where all objects (labels) would be stored
public class PacManGUI extends JFrame {
	
	private ScorePanel scorePanel = new ScorePanel();
	
	// A2.2 - This creates a private instance variable that contains the board
	private Board board = new Board(); // This private instance variable (field, property) leads to class - new Board(0

	// ~ A3 - This method customizes the screen size, title, and adds the board to
	// the frame
	public PacManGUI() {

		// A3.1 - This sets the size of the screen and the title of the screen
		setSize(600,  750);
		setTitle("Nathan Zhuang's PacMan Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This code ensures that the window closes when the "x" button
		setLayout(null); // to allow for manual placing of objects										// is clicked

		
        
		scorePanel.setBounds(0, 0, 600, 100);
		add(scorePanel);
		
		board.setBounds(0, 100, 600, 600);
		// A3.2 - This adds the board to the overall frame
		add(board);

		// A3.3 - This adds a key listener that has the program to react to the
		// user-press keys
		addKeyListener(board);

		// A3.4 - This code ensures that the frame will be visible
		setVisible(true);

	}

}
