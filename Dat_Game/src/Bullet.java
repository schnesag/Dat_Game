import javax.swing.*;
import java.awt.*;

public class Bullet extends JPanel {
	int xpos, ypos; // top left corner position in JFrame
	double xcenter, ycenter; // center position in JFrame
	
	int width, height = 5; // dimensions of JPanel
	
	// center of bullet in JPanel dimensions
	double bulletXCenter, bulletYCenter;
	
	// velocities of Bullet
	double xvel;
	double yvel;
	
	// speed bullet will fire if firing ship's speed is 0
	double baseSpeed = 6.3;
	
	public Bullet (double _xcenter, double _ycenter, double _fireRotation, double _initXVel, double _initYVel) {
		super();
		
		bulletXCenter = width / 2;
		bulletYCenter = height / 2;
		
		// center of JPanel in JFrame
		xcenter = _xcenter;
		ycenter = _ycenter;
		// top left of JPanel in JFrame
		xpos = (int) (xcenter - bulletXCenter);
		ypos = (int) (ycenter - bulletYCenter);
		
		xvel = _initXVel + (Math.cos(_fireRotation) * baseSpeed);
		yvel = _initYVel + (Math.sin(_fireRotation) * baseSpeed);
		
		this.setBounds(xpos, ypos, width, height);

		System.out.println("new bullet at x: " + xcenter + " y: " + ycenter);
		System.out.println("xvel: " + xvel + "yvel: " + yvel);
		
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
	}
	
	public void addVelocity () {
		xcenter += xvel;
		ycenter += yvel;
		
		xpos = (int) (xcenter - bulletXCenter);
		ypos = (int) (ycenter - bulletYCenter);
		
		this.setBounds(xpos, ypos, width, height);
	}
}
