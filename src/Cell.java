
import javax.swing.JLabel;

/*
 * This class uses the objects that are assigned in Icons.java to make the game display accurate images
 */


// B1.1 - This is the class that uses JLabels 
public class Cell extends JLabel {
	
	//Set the private instance variable (item) represents each character in maze.txt
	private char item;
	
	// This is the constructor method that creates a dynamic variable
	public Cell(char item) {
		
		super(); //calls the object constructor method
		this.item = item;
		
		//Calls the method 
		setCodeIcon();
	}
	
	// This sets the item so that the value of the item can be modified as there is a new value entered
	public void setItem(char item) {
		this.item = item;
	}
	
	// This returns the item to make it accessible throughout the program
	public char getItem() {
		return item;
	}
	
	//This method converts the letters to image
	private void setCodeIcon() {
		if (item == 'P') // We use SINGLE QUOTES for primitive types in character
			setIcon(Icons.PACMAN[0]);
		else if (item == '0') 
			setIcon(Icons.GHOST[0]);
		else if (item == '1')
			setIcon(Icons.GHOST[1]);
		else if (item == '2')
			setIcon(Icons.GHOST[2]);
		//else if (item == '3')
			//setIcon(Icons.GHOST[3]);
		else if (item == 'W')
			setIcon(Icons.WALL);
		else if (item == 'F')
			setIcon(Icons.FOOD);
		else if (item == 'D')
			setIcon(Icons.DOOR);
		else if (item == 'C')
			setIcon(Icons.CHERRY);
		else if (item == 'R')
			setIcon(Icons.SPEED);
	}

}
