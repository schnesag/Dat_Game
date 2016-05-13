import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ! can pass 'this' to a method and use it later ( like 'self' )

public class GameFrame extends JFrame {

	int width, height;
	
	Ship ship;
	
	JButton left, right;
	
	JOptionPane payUsMoney;
	
	public GameFrame (String _title, int _width, int _height) {
		super(_title); // sets title
		
		width = _width; // saves width and height
		height = _height;
		payUsMoney = new JOptionPane();
		payUsMoney.showMessageDialog(this, "Please Pay $9.99 to continue using Software!","Get Dat Money", JOptionPane.WARNING_MESSAGE);

		this.setSize(width, height); // sets width and height
		this.setLayout(null); // free layout for x and y positioning
		
		// create ship and add to JFrame this
		ship = new Ship(20, 20, 80, 80);
		this.add(ship);
		
		// move ship right
		right = new JButton("-->");
		right.addActionListener(new moveButtonListener());
		right.setBounds(80, 200, 50, 15);
		this.add(right);
		
		// move ship left
		left = new JButton("<--");
		left.addActionListener(new moveButtonListener());
		left.setBounds(20, 200, 50, 15);
		this.add(left);
	}

	// -- Button Listeners --
	private class moveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			
			if (event.getSource().equals(right)) // if right button is source
				ship.increaseX(); // move right
			
			else if (event.getSource().equals(left)) // if left button is source
				ship.decreaseX(); // move left
			
			else // neither right or left button called
				System.out.println("moveButtonListener: no button matching event.getSource()");
		}
	}
	
}
