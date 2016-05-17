import javax.swing.*;
import java.awt.*;

public class Bullet extends JPanel {
	GameFrame parentFrame;
	
	int xpos, ypos; // top left corner position in JFrame
	double xcenter, ycenter; // center position in JFrame
	
	int width, height; // dimensions of JPanel
	
	// center of bullet in JPanel dimensions
	double bulletXCenter, bulletYCenter;
	
	// velocities of Bullet
	double xvel;
	double yvel;
	
	// speed bullet will fire if firing ship's speed is 0
	double baseSpeed = 6.3;
	
	public Bullet (GameFrame _parentFrame, double _xcenter, double _ycenter, double _fireRotation, double _initXVel, double _initYVel) {
		super();
		parentFrame = _parentFrame;
		
		width = 10;
		height = 10;
		
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

		/*System.out.println("new bullet at x: " + xcenter + " y: " + ycenter);
		System.out.println("xvel: " + xvel + "yvel: " + yvel);*/
		
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
	}
	
	public void updatePosition () {
		
		// screen wrapping for bullet in JFrame parentFrame
		if (xcenter < 0)
			xcenter = parentFrame.getWidth() + xcenter;
		else if (xcenter > parentFrame.getWidth())
			xcenter = xcenter - parentFrame.getWidth();
		
		if (ycenter < 0)
			ycenter = parentFrame.getHeight() + ycenter;
		else if (ycenter > parentFrame.getHeight())
			ycenter = ycenter - parentFrame.getHeight();
		
		xcenter += xvel;
		ycenter += yvel;
		
		xpos = (int) (xcenter - bulletXCenter);
		ypos = (int) (ycenter - bulletYCenter);
		
		this.setBounds(xpos, ypos, width, height);
	}
}
