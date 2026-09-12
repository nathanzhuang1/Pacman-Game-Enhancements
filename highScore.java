import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class highScore extends JPanel implements ActionListener {
	
	
	public highScore(String playerName, int score) {
		setLayout(null);
		setBackground(Color.BLACK);
		Color yellowColor = new Color(255, 255, 0);
		
		JLabel titleLabel = new JLabel(playerName + "'s highest score: " + score);
		JButton leaderBoardButton = new JButton("Leaderboard");
		leaderBoardButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 JFrame leaderBoardFrame = new JFrame("Leaderboard");
				 leaderBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 leaderBoardFrame.setSize(400, 300);
				// leaderBoardFrame.setLayout(null);
			     leaderBoardFrame.add(new LeaderBoard()); //adding a panel
			     leaderBoardFrame.setBackground(Color.BLACK);
			     leaderBoardFrame.setVisible(true);
			     
			 }});
	
		titleLabel.setBounds(80, 0, 300, 50);
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
		titleLabel.setForeground(yellowColor);
		add(titleLabel);
	
		leaderBoardButton.setBounds(200, 200, 100, 50);
		leaderBoardButton.setBackground(yellowColor);
		add(leaderBoardButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

