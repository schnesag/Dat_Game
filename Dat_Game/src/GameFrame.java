import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameFrame extends JFrame {

	int width, height;
	
	Ship ship;
	
	JOptionPane payUsMoney;
	JOptionPane instructions;
	
	public GameFrame (String _title, int _width, int _height) {
		super(_title); // sets title
		
		width = _width; // saves width and height
		height = _height;
		payUsMoney = new JOptionPane();
		payUsMoney.showMessageDialog(this, "Please Pay $9.99 to continue using Software!","Get Dat Money",
									 JOptionPane.WARNING_MESSAGE);
		instructions = new JOptionPane();
		instructions.showMessageDialog(this, "Use WASD to move ship.", "Instructions", JOptionPane.WARNING_MESSAGE);

		this.setSize(width, height); // sets width and height
		this.setLayout(null); // free layout for x and y positioning
		
		// create ship and add to JFrame this
		ship = new Ship(this, width / 2, height / 2, 80, 80, 0.1);
		this.add(ship);
		
		this.addKeyListener(new moveKeyListener());
		
		/* JButton examples -----------
		right = new JButton("-->");
		right.addActionListener(new moveButtonListener());
		right.addChangeListener(new moveButtonChangeListener());
		right.addMouseListener(new moveButtonMouseListener());
		right.setBounds(80, 200, 50, 15);
		this.add(right);*/

	}

	// -- Key and Button Listeners --

	private class moveKeyListener implements KeyListener {
		
		public void keyTyped (KeyEvent event) {
			
		}
		
		public void keyPressed (KeyEvent event) {
			System.out.println("moveKeyListener (GameFrame): Pressed " + event.getKeyCode());
			
			if (event.getKeyCode() == event.VK_D)
				ship.rightEngines();
			
			else if (event.getKeyCode() == event.VK_A)
				ship.leftEngines();
			
			else if (event.getKeyCode() == event.VK_W)
				ship.upEngines();
			
			else if (event.getKeyCode() == event.VK_S)
				ship.downEngines();
		}
		
		public void keyReleased (KeyEvent event) {
			System.out.println("moveKeyListener (GameFrame): Released " + event.getKeyCode());
			
			if (event.getKeyCode() == event.VK_D)
				ship.rightEngines();
			
			else if (event.getKeyCode() == event.VK_A)
				ship.leftEngines();
			
			else if (event.getKeyCode() == event.VK_W)
				ship.upEngines();
			
			else if (event.getKeyCode() == event.VK_S)
				ship.downEngines();
			
		}
		
	}
	
	// OLD STUFF
	
	/*private class moveKeyListener implements KeyListener {
		
		public void keyTyped (KeyEvent event) {
			
		}
		
		public void keyPressed (KeyEvent event) {
			System.out.println("moveKeyListener (GameFrame): Pressed " + event.getKeyCode());
			
			if (event.getKeyCode() == event.VK_D)
				ship.startRightEngines();
			
			else if (event.getKeyCode() == event.VK_A)
				ship.startLeftEngines();
			
			else if (event.getKeyCode() == event.VK_W)
				ship.startUpEngines();
			
			else if (event.getKeyCode() == event.VK_S)
				ship.startDownEngines();
		}
		
		public void keyReleased (KeyEvent event) {
			System.out.println("moveKeyListener (GameFrame): Released " + event.getKeyCode());
			
			if (event.getKeyCode() == event.VK_D)
				ship.stopRightEngines();
			
			else if (event.getKeyCode() == event.VK_A)
				ship.stopLeftEngines();
			
			else if (event.getKeyCode() == event.VK_W)
				ship.stopUpEngines();
			
			else if (event.getKeyCode() == event.VK_S)
				ship.stopDownEngines();
			
		}
		
	}*/
	/*private class moveButtonMouseListener implements MouseListener {
		
		public void mouseClicked (MouseEvent event) {
			if (event.getSource().equals(right))
				ship.rightEnginesOn = true;
			else if (event.getSource().equals(left))
				ship.leftEnginesOn = true;
			else if (event.getSource().equals(up))
				ship.upEnginesOn = true;
			else if (event.getSource().equals(down))
				ship.downEnginesOn = true;
		}
		
		public void mouseReleased (MouseEvent event) {
			if (event.getSource().equals(right)) {
				ship.rightEnginesOn = false;
			}
				
			else if (event.getSource().equals(left)) {
				ship.leftEnginesOn = false;
			}
					
			else if (event.getSource().equals(up))
				ship.upEnginesOn = false;
						
			else if (event.getSource().equals(down))
				ship.downEnginesOn = false;
		}
		
		public void mouseEntered (MouseEvent event) {}
		public void mouseExited (MouseEvent event) {}
		public void mousePressed (MouseEvent event) {}

	}*/
	
	/*private class moveButtonChangeListener implements ChangeListener {
		public void stateChanged (ChangeEvent event) {
			
			if (event.getSource().equals(right)) {
				System.out.println("Change event detected");
				ship.activateRightEngines();
			}
			
			else if (event.getSource().equals(left))
				ship.activateLeftEngines();
			
			else if (event.getSource().equals(up))
				ship.activateUpEngines();
			
			else if (event.getSource().equals(down))
				ship.activateDownEngines();
			
			else
				System.out.println("moveButtonChangeListener (GameFrame): no button matching event.getSource()");
		}
	}*/
	
	/*private class moveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			
			//if (event.getSource().equals(right)) // if right button is source
				//ship.moveRight(); // move right
			
			if (event.getSource().equals(left)) // if left button is source
				ship.moveLeft(); // move left
			
			else if (event.getSource().equals(up)) // if up button is source
				ship.moveUp(); // move up
			
			else if (event.getSource().equals(down)) // if down button is source
				ship.moveDown(); // move down
			
			else // neither right or left button called
				System.out.println("moveButtonListener: no button matching event.getSource()");
		}
	}*/
	
}
