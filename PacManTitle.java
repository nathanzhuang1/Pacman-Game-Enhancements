import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PacManTitle extends JFrame implements ActionListener {
	
	// Loading the images
	JLabel pacManImage = new JLabel(new ImageIcon("images/pacManLogo.png"));
	JButton play = new JButton(new ImageIcon("images/startButton.png"));
	
	
	public PacManTitle() {
		setSize(600,  750);
		setTitle("Nathan Zhuang's PacMan Game");
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This code ensures that the window closes when the "x" button
		setLayout(null); // to allow for manual placing of objects										// is clicked
		
		pacManImage.setBounds(0, 0, 550, 309);
		add(pacManImage);
		
		play.setBounds(40, 500, 500, 154);
		play.addActionListener(this);
		add(play);
		
		setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		// sensing action from play button (user clicking it)
		if (e.getSource() == play) {
			
			setVisible(false); // make title space disappear
			new PacManGUI(); // opening new window
			
		}
		
	}
	
	

}
