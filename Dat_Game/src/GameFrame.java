import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameFrame extends JFrame {

	int width, height;
	
	Ship ship;
	
	JOptionPane payUsMoney;
	JOptionPane instructions;
	
	//TODO be removed
	//Asteroid[] asteroids;
	
	AsteroidController asteroidController;
	
	public GameFrame (String _title, int _width, int _height) {
		super(_title); // sets title
		
		// saves width and height
		// use getWidth() and getHeight() to get updated height after resizing
		width = _width;
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

		asteroidController = new AsteroidController(this, 5, 3, 2);
		
		this.addKeyListener(new moveKeyListener());
		
		// TODO !! to be removed
		/*asteroids = new Asteroid[4];
		for (int i = 0; i < asteroids.length; i ++) 
			asteroids[i] = new Asteroid(this, Math.random() * width, Math.random() * height,
										30, Math.random() * Math.PI * 2, Math.random() * 4);*/
		
		
		
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
			//System.out.println("moveKeyListener (GameFrame): Pressed " + event.getKeyCode());
			
			if (event.getKeyCode() == KeyEvent.VK_D)
				ship.rotateRightOn();
			
			else if (event.getKeyCode() == KeyEvent.VK_A)
				ship.rotateLeftOn();
			
			else if (event.getKeyCode() == KeyEvent.VK_W)
				ship.forwardEnginesOn();
			
			else if (event.getKeyCode() == KeyEvent.VK_S)
				ship.reverseEnginesOn();
			
			else if (event.getKeyCode() == KeyEvent.VK_P)
				ship.fireMain();
		}
		
		public void keyReleased (KeyEvent event) {
			//System.out.println("moveKeyListener (GameFrame): Released " + event.getKeyCode());
			
			if (event.getKeyCode() == KeyEvent.VK_D)
				ship.rotateRightOff();
			
			else if (event.getKeyCode() == KeyEvent.VK_A)
				ship.rotateLeftOff();
			
			else if (event.getKeyCode() == KeyEvent.VK_W)
				ship.forwardEnginesOff();
			
			else if (event.getKeyCode() == KeyEvent.VK_S)
				ship.reverseEnginesOff();
			
			else if (event.getKeyCode() == KeyEvent.VK_P)
				ship.stopMain();
			
		}
	}
	
	
}
