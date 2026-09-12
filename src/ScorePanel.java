import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;


public class ScorePanel extends JPanel {
	
	// Calling the labels
	JLabel scoreLabel = new JLabel("Score: ");
	public static JLabel scoreResult = new JLabel("0");
	
	JLabel livesLabel = new JLabel(" | Lives: ");
	public static JLabel liveResult = new JLabel("3");
	
	JLabel levelLabel = new JLabel(" | Level: ");
	public static JLabel levelResult = new JLabel("1");

	
	// This customizes the labels on the panel to make it visible for the user
	public ScorePanel() {
		 
		Color yellowColor = new Color(255, 255, 0);
		setBackground(Color.BLACK); // setting background colors
		
		// Making scoreLabel visible
		scoreLabel.setBounds(0, 0, 400, 100);
		scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
		scoreLabel.setForeground(yellowColor);
		add(scoreLabel);
		
		scoreResult.setBounds(0, 0, 600, 80);
		scoreResult.setFont(new Font("Monospaced", Font.BOLD, 30));
		scoreResult.setForeground(yellowColor);		
		add(scoreResult);
		
		// Making liveLabel visible
		livesLabel.setBounds(0, 0, 1000, 100);
		livesLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
		livesLabel.setForeground(yellowColor);
		add(livesLabel);

		liveResult.setBounds(0, 0, 800, 100);
		liveResult.setFont(new Font("Monospaced", Font.BOLD, 30));
		liveResult.setForeground(yellowColor);
		add(liveResult);
		
		// Making levelLabel visible
		levelLabel.setBounds(0, 0, 1000, 100);
		levelLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
		levelLabel.setForeground(yellowColor);
		add(levelLabel);

		levelResult.setBounds(0, 0, 1200, 100);
		levelResult.setFont(new Font("Futura", Font.BOLD, 30));
		levelResult.setForeground(Color.WHITE);
		add(levelResult);
	}



}
