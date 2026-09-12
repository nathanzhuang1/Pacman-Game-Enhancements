import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;


public class LeaderBoard extends JPanel {
	
	// creating a hash-map
	private Map<String, Integer> nameScore = new HashMap<String, Integer>();

	public LeaderBoard() {
		setLayout(null);
		Color yellowColor = new Color(255, 255, 0); // initializing yellow color
		
		setBackground(Color.BLACK); // sets the background to black
		
		// Initialize the leaderboard data by loading it from the file
		loadNameScore();
		
		// Creating a the leaderboard label
		JLabel leaderboard = new JLabel("LeaderBoard");
		leaderboard.setFont(new Font("Monospaced", Font.BOLD, 30));
		leaderboard.setForeground(yellowColor);
		leaderboard.setAlignmentX(CENTER_ALIGNMENT); // centering label
		leaderboard.setBounds(50, 10, 100, 40);
		add(leaderboard);
		
		
		//restricting the layout to 10 people in leaderboard (10 per row)
        setLayout(new GridLayout(10, 2));
        
        // Creates a label for each player, with their name and best score  
        Map nameScoreSort = sort(nameScore, true);
        for (Map.Entry entry : nameScore.entrySet()) {
            JLabel entryLabel = new JLabel(entry.getKey() + ": " + entry.getValue());

            entryLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
            entryLabel.setForeground(yellowColor);
            entryLabel.setAlignmentX(TOP_ALIGNMENT);
            entryLabel.setBounds(20, 150, 20, 20);
            add(entryLabel);
        }

        
	}
	
       
	
	private void loadNameScore() {
		
		
		
		//creating a scanner to ensure that nameScore.txt is readable
		Scanner input;
		
		//testing for the existence of that file
		try {

			// creates the new file that stores the readable hashmap
			input = new Scanner(new File("name-score.txt"));

			while (input.hasNext()) { // reads each line from the name-score.text file
				// Split the string using ":" as the delimiter
		        String[] parts = input.nextLine().split(":"); 
				nameScore.put(parts[0], Integer.valueOf(parts[1]));
				// parts[0] is the player's name while parts[1] is the player's score
				// this is obtained when the name and score are converted into an INTEGER
			}
			
		}catch (FileNotFoundException error) {

			System.out.println("name-score.txt not found");
			
		}
	}
	
	
	// Sorts hashmaps by value -> https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
	
	//K is the key type while V is the value type
	//Unsorted -> unsorted input map
	private static <K extends Comparable<K>, V extends Comparable<V>> Map<K, V> sort( 
	        final Map<K, V> unsorted,
	        final boolean order) {
		
		// converting hash-map to arraylist 
	    final var list = new ArrayList<>(unsorted.entrySet());

	    // Sorting the list
	    list.sort((o1, o2) -> order 
	                          ? o1.getValue().compareTo(o2.getValue()) == 0 // ? is short-hand for writing an if-else statement
	                            ? o1.getKey().compareTo(o2.getKey())
	                            : o1.getValue().compareTo(o2.getValue())
	                          : o2.getValue().compareTo(o1.getValue()) == 0
	                            ? o2.getKey().compareTo(o1.getKey())
	                            : o2.getValue().compareTo(o1.getValue()));
	    return list.stream().collect(
	            Collectors.toMap(
	                    Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new
	            )
	    );
	}
	

}
