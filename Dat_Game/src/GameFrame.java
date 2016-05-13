import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ! can pass 'this' to a method and use it later ( like 'self' )

public class GameFrame extends JFrame {

	int width, height;
	
	Ship ship;
	
	JButton left, right, up, down;
	
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
		left.setBounds(30, 200, 50, 15);
		this.add(left);
		
		up = new JButton("<--");
		up.addActionListener(new moveButtonListener());
		up.setBounds(45, 150, 15, 50);
		this.add(up);
		
		down = new JButton("<--");
		down.addActionListener(new moveButtonListener());
		down.setBounds(45, 200, 15, 50);
		this.add(down);
	}

	// -- Button Listeners --
	private class moveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			
			if (event.getSource().equals(right)) // if right button is source
				ship.moveRight(); // move right
			
			else if (event.getSource().equals(left)) // if left button is source
				ship.moveLeft(); // move left
			
			else if (event.getSource().equals(up)) // if up button is source
				ship.moveUp(); // move up
			
			else if (event.getSource().equals(down)) // if down button is source
				ship.moveDown(); // move down
			
			else // neither right or left button called
				System.out.println("moveButtonListener: no button matching event.getSource()");
		}
	}
	
}
