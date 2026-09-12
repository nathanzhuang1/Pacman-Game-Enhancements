import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/*
 * This class holds the game board on the panel and we are putting the panel onto the frame
 */

// Setting the objects on the game board through a panel with keyListener and actionListener 
// to bond our code with the creator's code
// (I) Key Listener requires three different methods have to be in the program 
// (I) Action Listener needs one action performed method
public class Board extends JPanel implements KeyListener, ActionListener {

	// For every 250 mili-seconds, the ghosts and Pac-Man (via keyboard) will move (something will occur). 
	private Timer gameTimer = new Timer(250, this); // timer (java class) needs two arguments: # of mili-seconds and the class in which it calls
	private Timer resumeGameTimer = new Timer (2000, this);

	// Represents the size of the board which is 25 rows, 27 columns (the number of cells)
	//(I) The mazeArray is a NULL array that uses the Cell constructor method
	private Cell[][] mazeArray = new Cell[25][27]; //every cell is a JLabel

	// Associating an object (PacMan) to the class Mover.java
	private Mover PacMan; // the class codes define the movements of Pac-Man

	// Creating the ghost array in Mover.java
	private Mover[] ghostArray = new Mover[3];

	// Setting the initial number (private instance variable) of pellets to be zero
	private int pellets = 0;
	private int pelletsConsumed = 0;
	

	// Setting the initial scores (private instance variable) to zero
	private int score = 0;
	
	private int lives = 3;

	private int level = 1;
	
	// obtains the clip by creating a private instance variable to be accessible throughout this class
	private Clip pacManChomp;
	
	private Clip pacManKilled;
	
	private Clip speedUpSound;
	
	private int totalPellets;
	
	// This is a hashmap that stores player names (Strings) and high scores (integers)
	private Map<String, Integer> nameScore = new HashMap();
	
	private String playerName;

	// This method sets the number of rows and columns and modifies the background color and walls
	public Board() {

		String gameTheme = "sounds/GAMEBEGINNING.wav"; //Lakshna
		
		String pacchomp = "sounds/pacchomp.wav";
	
		String killed = "sounds/killed.wav";
		
		String speed = "sounds/interm.wav";
		
		soundResume(gameTheme, pacchomp, killed, speed);
		
		// This code sets the layout, (25 rows, 27 columns)
		setLayout(new GridLayout(25, 28));
		setBackground(Color.BLACK); // changing background color to black
		
		loadNameScore();
		playerName = JOptionPane.showInputDialog(null, "Please enter your name:", 
                "Input Name", JOptionPane.QUESTION_MESSAGE);
		// End of our constructor method
		loadBoard(1);

	}

	// Calling the method that reads the soundclips
	private void soundResume(String gameTheme, String pacchomp, String killed, String speed) { //change this
		
		
		try {
			
			//Creating the file
			File soundFile = new File(gameTheme); //calling the name of the file (.wav) inside 
			File soundFileTwo = new File(pacchomp);
			File soundFileThree = new File(killed);
			File soundFileFour = new File(speed);
			
			if (soundFile.exists()) {
			
				// obtains the sound of the clips via audioInputStream
				AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
				AudioInputStream pacchompSound = AudioSystem.getAudioInputStream(soundFileTwo);
				AudioInputStream pacKilled = AudioSystem.getAudioInputStream(soundFileThree);
				AudioInputStream speedUp = AudioSystem.getAudioInputStream(soundFileFour);
				
				// obtains the clip
				Clip clipStartStop = AudioSystem.getClip();
			    pacManChomp = AudioSystem.getClip();
			    pacManKilled = AudioSystem.getClip();
			    speedUpSound = AudioSystem.getClip();
				
				clipStartStop.open(sound);
				pacManChomp.open(pacchompSound);
				pacManKilled.open(pacKilled);
				speedUpSound.open(speedUp);
				
				
				
				clipStartStop.loop(Clip.LOOP_CONTINUOUSLY); //https://stackoverflow.com/questions/4875080/music-loop-in-java
				
				clipStartStop.start();
				
				
					
			}
			
			else {
				
				System.out.print("File not Found");
			}
				
		} // end of try method
		
		catch (Exception error) {

			System.out.println("File not found");
		}
	}
		
	
		

		

	// ~ B2 - This method loads the game board
	private void loadBoard(int level) {
		
		

		// B2.1 - Keeps track of the current row, start counting at zero from code to keep
		// track
		int row = 0;

		// B2.2 - Creating a scanner but not actually creating a file
		Scanner input;

		// B2.3 - The try statement is used to define a block of code to be tested for errors prior execution
		try {

			// B2.4 - Calling the file 'maze.txt' to scanner
			input = new Scanner(new File("maze-level"+level+".txt"));

			// B2.5 - If there is another line to read, this code will keep looping. Instead of
			// having a fixed value like a for-loop, using while-loop offers a DYNAMIC value where 
			// it can iterate as long as there is another line in that file as they are added into the folder
			while (input.hasNext()) { //this is checking for each row in the maze.txt

				// B2.6 - This code reads the ENTIRE ROW (--) and interprets it
				char[] lineArray = input.nextLine().toCharArray(); //{'x', 'x', 'x', ...} - x is the character

				// This for-loop reads EVERY COLUMN in maze.txt for every ROW it PASSES 
				for (int column = 0; column < lineArray.length; column++) {

					// Assigns cell to the array based on the coordinates (row, column)
					// (I) The mazeArray is now an object that uses the Cell constructor method.
					/* (I2) The below code is same as saying:
					 * Cell c = new Cell(lineArray[column]);
					 * mazeArray[row][column] = c;
					 */
					
					mazeArray[row][column] = new Cell(lineArray[column]);

		
					// The 'F' calls on the food. This method counts the # of pellets and will accumulate once spotted
					if (lineArray[column] == 'F')
						pellets++;
					
					//CHERRY ADDITION
					//else if (lineArray[column] == 'C')
					//	pellets += 10;
					
					

					// If reader comes across "p" in maze.txt, this SETS UP the STARTING POSITION for Pac-man
					else if (lineArray[column] == 'P') { //mark 
						PacMan = new Mover(row, column);
						PacMan.setIcon(Icons.PACMAN[0]); // sets the pac-man image facing left
						
						/*
						//SPEEDUP ADDITION
						 if (lineArray[column] == 'R') {
							gameTimer.setDelay(100);
							
							// Schedule a change in the timer delay after 2 seconds
					        Timer delayTimer = new Timer(2000, new ActionListener() {
					            @Override
					            public void actionPerformed(ActionEvent e) {
					                gameTimer.setDelay(250); // Update the delay to 250 ms
					                ((Timer) e.getSource()).stop(); // Stop the delay changer
					            }
					        });

					        delayTimer.setRepeats(false); // Only execute once
					        delayTimer.start();
							
						}*/

					}
					

					// These numbers customize the ghosts (as displayed in maze.txt)
					else if (lineArray[column] == '0' || lineArray[column] == '1' || lineArray[column] == '2') {

						// This sets the positions of the ghostss
						int gNum = Character.getNumericValue(mazeArray[row][column].getItem()); // gNum obtains the coordinates of 
																								// the ghost (0, 1, 2)
						ghostArray[gNum] = new Mover(row, column); // lets the Mover object know the positions of the ghost
						ghostArray[gNum].setIcon(Icons.GHOST[gNum]); // displaying ghost icon (displaying either of the ghost pictures)

						// (I) There are 3 ghost colors, so this will display the icons of the three ghosts

					}

					// Adds the JLabel (mazeArray) - cell object - to the panel 
					add(mazeArray[row][column]);

				}

				// This applies to every row of maze.txt to ensure every ROW and COLUMN gets accounted for and added
				row++;
			}

			// close the file
			input.close();

		}

		// This code lets the user know in the console that there is an error (file does
		// not exist in path)
		catch (FileNotFoundException error) {

			System.out.println("File not found");
		}
	}

	// This method is one of the three necessary methods of a key listener.
	// This method records the actions to perform when a key is pressed by the user
	public void keyPressed(KeyEvent key) {

		// If Pac-man is not running or if the timer is not running, then the game starts
		if (gameTimer.isRunning() == false && PacMan.isDead() == false)
			gameTimer.start();

		
		// If Pac-man is not dead and the game is not over, it will continue
		if (PacMan.isDead() == false && score != pellets) {

			// Modifying the directions to control the movement
			int direction = key.getKeyCode() - 37; // Subtracting 37 because it is the 'left' key

			// (I) The little arrows on our keyboard starts at 37 (in ASCII code)
			// 	38 means up, 39 means right, 40 means down

			// Initializing the row and columns to the origin point (0, 0)
			int dRow = 0; // delta means 'change'
			int dCol = 0;

			// These sets the direction of Pac-man so that the direction info can be passed onto 
			// the icons class and the mover class
			if (direction == 0) // moving pacman to the left
				dCol = -1; // adding -1 to (x-axis) column makes pacman goes left (delta column)
			else if (direction == 1)
				dRow = -1;
			else if (direction == 2)
				dCol = 1;
			else if (direction == 3)
				dRow = 1;

			// This block of code validates whether the walls of the maze exists so that the pac-man will stop against walls
			if (mazeArray[PacMan.getRow() + dRow][PacMan.getColumn() + dCol].getIcon() != Icons.WALL) {
				PacMan.setIcon(Icons.PACMAN[direction]); // passes the given direction to set the facing of the image
				PacMan.setDirection(direction); // sets the keyboard (technical) directions of Pac-Man
			}
		}
	}

	// Key Released is one of the three necessary methods of a key listener.
	public void keyReleased(KeyEvent key) {
		// Not used
	}

	// Key Typed is one of the three necessary methods of a key listener.
	public void keyTyped(KeyEvent key) {
		// Not used

	}

	// This method moves the Pac-man or the ghosts (based on the subject of mover) but ensures they are moving the right places
	private void performMove(Mover mover) {

		// If you collide, then call death
		// otherwise, set the icon

		// This obtains the location of where the currentCell and nextCell (label) objects are standing
		Cell currentCell = mazeArray[mover.getRow()][mover.getColumn()]; // obtaining current location of user
		Cell nextCell = mazeArray[mover.getNextRow()][mover.getNextColumn()]; // obtaining the next spot the user will
																				// be in
		
		// This controls the Pac-man TELEPORTING from one end of the frame to another end
		if (mover.getColumn() == 1) { // ~ (I) If I'm on one side,
			mover.setColumn(25); // take me to the other side
			mover.setDirection(0); // reset coordinates
			mazeArray[12][1].setIcon(Icons.DOOR); // put the door back
		} else if (mover.getColumn() == 25) { // ~ (I2)
			mover.setColumn(1); // take me to the other side
			mover.setDirection(2); // sets keyboard column to 1 
			mazeArray[12][25].setIcon(Icons.DOOR); // put the door back
		}
		
		// ADDITION: Adding a gate and not allowing Pac-man to enter ghosts' base
		if (mover == PacMan && nextCell.getItem() == 'G' ) {
			return; // exit the function
		}
		
		if (mover != PacMan && (currentCell.getItem() == 'G' || currentCell.getItem() == 'S')) {// the path to move out
			mover.setdColumn(0); //change the direction to move up (dColumn doesn't change)
			mover.setdRow(-1);
			nextCell = mazeArray[mover.getNextRow()][mover.getNextColumn()]; 
		}
		
		// Look one spot ahead and make sure there is no wall ahead
		if (nextCell.getIcon() != Icons.WALL) {

			// if you are the ghost and you are stepping on food, put the food back
			// ghosts don't eat the food
			if (mover == PacMan) {
				//if (currentCell.getItem() == 'F' || currentCell.getItem() == 'C' || currentCell.getItem() == 'R') {
					currentCell.setIcon(Icons.BLANK);
				//}
			}else {
				if (currentCell.getItem() == 'F')  {
					currentCell.setIcon(Icons.FOOD);
				}
				
				else if (currentCell.getItem() == 'C')  {
					currentCell.setIcon(Icons.CHERRY);
				}
				
				else if (currentCell.getItem() == 'R')  {
					currentCell.setIcon(Icons.SPEED);
				}else {
					currentCell.setIcon(Icons.BLANK);
				}
			}
				

			// This code moves the nextCell objects (ghosts, Pac-Man) from the mover class
			mover.move();
			
			// Essential: nextCell becomes currentCell once moved
			currentCell = mazeArray[mover.getRow()][mover.getColumn()]; 
			

			// When ghosts collide into Pac-Man...
			if (collided() && lives == 0)
				death(); // Pac-Man dies
				

			// If Pac-Man is still alive
			else
				currentCell.setIcon(mover.getIcon()); // this obtains Pac-Man's icon  

			// Obtain the cell with the picture so reader knows you're there

			// If Pac-Man eats food... CHERRY ADDITION
			if (mover == PacMan && nextCell.getItem() == 'F') {

				// Score increases
				score++;
				pelletsConsumed++;
				ScorePanel.scoreResult.setText(Integer.toString(score));
				currentCell.setItem('E'); // ????
				
				if (!pacManChomp.isRunning()) {
					pacManChomp.setFramePosition(0);					
					pacManChomp.start();					
				}
				
			}else if (mover == PacMan && nextCell.getItem() == 'C') {
				// Score increases
				score += 10;
				ScorePanel.scoreResult.setText(Integer.toString(score));
				currentCell.setItem('E'); // ????
				pacManChomp.stop();	
			}//SPEEDUP ADDITION
			else if (mover == PacMan && currentCell.getItem() == 'R') {
				
				if (!speedUpSound.isRunning()) {
					speedUpSound.setFramePosition(0);					
					speedUpSound.start();					
				}
				
				
				gameTimer.setDelay(100);
				
				// Schedule a change in the timer delay after 2 seconds
		        Timer delayTimer = new Timer(2000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                gameTimer.setDelay(250); // Update the delay to 250 ms
		                ((Timer) e.getSource()).stop(); // Stop the delay changer
		                speedUpSound.stop();
		            }
		        });

		        delayTimer.setRepeats(false); // Only execute once
		        delayTimer.start();
				pacManChomp.stop();	
				currentCell.setItem('E');
				
			}else if (mover == PacMan){
				pacManChomp.stop();	
			}
			
		}
			

				
				// Setting if, else statements to set the total number of pellets in each level map
				if (level == 1) {
					totalPellets = 222;
				}
					
				else if (level == 2) {
					totalPellets = 564; //342 (number of pellets in level 2) + 222
				}
				
				else if (level == 3) {
					totalPellets = 929; //365 (number of pellets in level 3) + 564
				}
					
				// Ensures that when all pellets are eaten (excluding cherries and speed-up), the game terminates
				if (pelletsConsumed == totalPellets) { //pellets
					System.out.printf("%d, %d\n", pelletsConsumed, totalPellets); // for de-bugging purposes
					if (level > 3) { // game stops when level is greater than 3 and congratulates user
						gameTimer.stop();
						JOptionPane.showMessageDialog(this, "You cleared the board!"); // shows 'congrats' messagebox
					}
					else {
						levelUp(); // user levels up when level is below 3
					}
					
					}

			}

		
		
	 
	
	
	
	// This method removes the current level board and adds the next level in accordance with maze-level[level].txt
	private void levelUp() { 
	
		// stop the game timer once the level is updated
		gameTimer.stop(); 

        System.out.println("Done!"); // for de-bugging purposes to indicate that a level has been switched
        
        // Removes everything from the board
		removeAll(); //refreshes the code
		revalidate();
		repaint();
		
		// Reset score, lives, and level once user makes it to the next level
		score = 0;
		ScorePanel.scoreResult.setText(Integer.toString(score));
		lives = 3;
		ScorePanel.liveResult.setText(Integer.toString(lives));
		level ++;
		ScorePanel.levelResult.setText(Integer.toString(level));
		loadBoard(level); // once a level has been changed, a new maze.txt would be loaded for next level
		
	}


	// This method validates whether the ghost collides into Pac-Man
	private boolean collided() {

		// This includes EVERY ghost in ghostArray
		for (Mover ghost : ghostArray) {

			// If the ghost's 'x' and 'y' coordinates collide into Pac-Man's...
			if (ghost.getRow() == PacMan.getRow() && ghost.getColumn() == PacMan.getColumn()) {
				lives--; // subtract lives
				ScorePanel.liveResult.setText(Integer.toString(lives)); //import lives to appear on score panel
				
				// These are the coordinates that ghosts respawn upon colliding with Pac-Man
				ghost.setRow(13); //Gabi helped me to set the row and column such that the ghosts would appear back in their starting place
				ghost.setColumn(13);

				return true; // return true for collision
			}
		}

		// Ghost did not collide into Pac-Man
		return false;

	}

	// This method is what happens when Pac-Man collides into the ghosts (dies)
	private void death() {

		// Pac-man is dead
		PacMan.setDead(true); // accessed in mover class
		
		pacManKilled.start(); //play the death sound

		// Obtains the coordinates of Pac-man and sets the image into a skull 
		mazeArray[PacMan.getRow()][PacMan.getColumn()].setIcon(Icons.SKULL);

		// Ends the game: Stops the timer and declares "game over"
		gameTimer.stop();
		
		// obtain the player's previous score
		int previousScore = 0;
		// If the player is not new, from nameScore.txt, read the score
		if (nameScore.get(playerName) != null) {
			previousScore = (Integer)nameScore.get(playerName); // set the previous score to the score attained
		}
		
		// If the previous score is less than the score attained, then update score
		if (previousScore < score) {
			
			// saves the user's highest score
			nameScore.put(playerName, score);
			
			saveNameScore(); // calls this method to write updated hashmap
			
			// Opens highScore window to make user see their highest score
			JFrame highScore = new JFrame("High Score");
			highScore.setSize(400, 300);
			
			highScore.add(new highScore(playerName, score));
			
			
			highScore.setVisible(true); // make frame visible
			
			
			//hashmap: https://www.w3schools.com/java/java_hashmap.asp
		} else {
			
			// saves the user's previous score (their new highest score)
			nameScore.put(playerName, previousScore);
			
			// Opens highScore window to make user see their highest score
			JFrame highScore = new JFrame("High Score");
			highScore.setSize(400, 300);
			
			
			highScore.add(new highScore(playerName, previousScore));
			
			
			highScore.setVisible(true); // make frame visible
			
		}
		

	}
	
	private void saveNameScore() {
		String filePath = "name-score.txt";
		// Save the map to the file
		// Writes the hashmap to the file (nameScore)
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        	
        	//Imports each entry in hashmap (nameScore)
            for (Map.Entry<String, Integer> entry : nameScore.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue()); 
                // writes key (name) and score number (value) onto nameScore.txt
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
	}

	// This method moves the ghosts
	private void moveGhosts() {

		// For every 3 ghosts...
		for (Mover ghost : ghostArray) {

			// Set an initial value for the ghost's direction
			int dir = 0;

			// Uses math.random to randomize the ghost's movements
			do {
				dir = (int) (Math.random() * 4);
			} while (Math.abs(ghost.getDirection() - dir) == 2); //????

			// Associates all three ghosts' directions with the randomized directions
			ghost.setDirection(dir);

			// If Pac-Man is still alive, move the ghosts
			if (!PacMan.isDead())
				performMove(ghost); // ghost (argument) turns into 'mover'

		}
	}

	// This actionPerformed is responsible for ensuring for the occurrence of all actions on the gameboard
	public void actionPerformed(ActionEvent event) {

		// If the timer is ticking, the Pac-man can move
		if (event.getSource() == gameTimer) {

			performMove(PacMan); // moves pac-man
			moveGhosts(); // moves the ghosts
			
			
			}

		}
	
	// This method LOADS (reads) the name-Score.txt file to the hashmap
	private void loadNameScore() {
		
		Scanner input; // reads the file to the hashmap
		
		try {

			input = new Scanner(new File("name-score.txt"));

			while (input.hasNext()) { // for each row in file (name-Score.txt)
				// Split the string using ":" as the delimiter 
		        String[] parts = input.nextLine().split(":");
		        
		        // upload the readings from name-Score.txt onto hashmap variable
				nameScore.put(parts[0], Integer.valueOf(parts[1]));
			}
		}catch (FileNotFoundException error) {

			System.out.println("name-score.txt not found");
		}
	}
	
	
}
