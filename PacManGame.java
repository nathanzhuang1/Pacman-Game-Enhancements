/*
 * 1. Name: Nathan Zhuang
 * 2. Date: Sunday, Dec 15, 2024 (extension)
 * 3. Course Code: ICS3U1-05 Mr.Fernandes
 * 4. Title of Project: Nathan Zhuang's Pacman Game
 * 5. Description: This project is to create a functional Pac-Man game. The objective of the game is to complete
 * all levels without losing all 3 lives in each one. There are speed-ups and cherries for score enhancements. 
 * I added sound to the game such that the game theme is playing, sound is played when pellets are eaten, and sound is also played when Pac-Man dies. 
 * There is also a high score that is displayed to the reader after the game is completed, even if their previous
 * score is worse than their best score (but the frame would display the best score). There is also a leaderboard, though not in order,
 * that displays the participants and their achievements. Lakshna helped me to read music clips and to ensure they are working. Gabi helped me 
 * set respawn position for ghosts upon losing a life. My dad helped me to use a hash-map to write the name and score of the user onto a .txt file
 * and to read them.
 * 6. Major Skills: Arraylists, comparator, hashmaps (dad helped me on this), while/for-loops, try and catch to read a file, try and catch to write a file, 
 * using getAudioInputStream (Lakshna helped me on this) to read clips, creating panels and frames.
 * 7. Added Features: 
 * Basic -> Score, adding a gate to forbid Pac-Man from entering house, creating an 'elevator' for ghosts to exit their house, 
 * creating a title screen, creating lives, creating a cherry bonus item (score booster), adding sound/music, and adding levels
 * Advanced -> Highscore with name (saved to file), leaderboard (but names are not listed in order)
 * 8. Areas of Concern:
 * List is not in order for leaderboard
 */


/*
 * A - This class opens the new window of PacManGUI
 */

public class PacManGame {
	
	// ~ A1. This main method allows other codes to be used 
	public static void main(String[] args) {
		
		new PacManTitle();
		//~ A2. Calling the constructor of the class that creates the Pac-Man game, 
		//      including the background, images, objects, etc.
		//new PacManGUI(); //only classes are uppercase
		
	}

}
