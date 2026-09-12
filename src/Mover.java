import javax.swing.JLabel;

/*
 * This class ...
 */

public class Mover extends JLabel {
	
	//Setting private instance variables for row and column
	private int row;
	private int column;
	
	//dRow refers to the modified row and column
	private int dRow;
	private int dColumn;
	
	
	//Turn isDead to true or false to determine and know whether the ghosts are edible or not
	private boolean isDead;
	
	//By default, everything is already at zero and at false
	public Mover(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	//Generate the rest
	
	//This method...
	//int direction represents the direction in which the user is moving in. Our job is to set the direction
	// to set the columns are rows
	
	//The getters and setters is dynamic and will accept new positions of the ghost and pac-man
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getdRow() {
		return dRow;
	}

	public void setdRow(int dRow) {
		this.dRow = dRow;
	}

	public int getdColumn() {
		return dColumn;
	}

	public void setdColumn(int dColumn) {
		this.dColumn = dColumn;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	//This method...
	public void move() {
		
		//Moving rows: row, can you change by the dRow?
		row += dRow;
		
		//Going side to side represents dColumn
		column += dColumn;
		
	}

	public void setDirection (int direction) {
		
		//
		dRow = 0;
		dColumn = 0;
		
		//
		if (direction == 0)
			dColumn = -1; //this means to go LEFT
		else if (direction == 1)
			dRow = -1; //this means to UP
		else if (direction == 2)
			dColumn = 1; //this means to go RIGHT
		else if (direction == 3)
			dRow = 1; //this means to travel DOWN
			
	}	
	
	//This method returns the MODIFIED coordinates of the objects so that they can be used
	public int getDirection() {
		
		//This validates the locations in which the pac-man will move
		//(I) Think of a Cartesian plane: 
		// The rows (x) represent this shape: (|) The columns (y) represent this shape: (--)
		
		//(I) When dRow is ZERO, dColumn is shifted. Likewise goes to dColumn when it is zero
		if (dRow == 0 && dColumn == -1) 
			return 0; //when direction is zero, dColumn will go DOWN
		else if (dRow == -1 && dColumn == 0) 
			return 1; //when direction is 1, dRow will go LEFT
		else if (dRow == 0 && dColumn == 1)
			return 2; //when direction is 2, dColumn goes UP
		else
			return 3; //when direction is 3, dRow will go RIGHT
		
		// (I) As these integers are returned, this will call upon the setDirection method, which defines the direction 
		// in which dColumn and dRow will move
		
	}
	
	//This method is responsible for MOVING the objects LEFT AND RIGHT (ghosts, pac-man) throughout the gameboard
	public int getNextRow() {
		//return where you are now and the change from left to right
		return row + dRow;

	}
	
	//This method is responsible for MOVING the objects UP AND DOWN (ghosts, pac-man) throughout the gameboard
	public int getNextColumn() {
		//return where you are now and the change from up and down
		return column + dColumn;

	}
	
	
	/*
	 * if {
	 * mover.setDirection(0); //force left
	 * } else if ... {
	 * mover.setDirection(2); //force right
	 */

}
