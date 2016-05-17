import javax.swing.*;
import java.awt.*;

public class Bullet extends JPanel {
	GameFrame parentFrame;
	
	double xpos, ypos; // top left corner position in JFrame
	double xcenter, ycenter; // center position in JFrame
	
	int width, height; // dimensions of JPanel
	
	// center of bullet in JPanel dimensions
	double bulletXCenter, bulletYCenter;
	
	// velocities of Bullet
	double xvel;
	double yvel;
	
	// speed bullet will fire if firing ship's speed is 0
	double baseSpeed = 6.3;
	
	long creationTime; // time bullet created in milliseconds
	long expireTime;
	
	public Bullet (GameFrame _parentFrame, double _xcenter, double _ycenter, double _fireRotation, double _initXVel, double _initYVel, long _expireTime) {
		super();
		parentFrame = _parentFrame;
		
		// dimensions of JPanel
		width = 5;
		height = 5;
		
		// center coordinate of JPanel in JPanel coordinates
		bulletXCenter = width / 2;
		bulletYCenter = height / 2;
		
		// center of JPanel in JFrame
		xcenter = _xcenter;
		ycenter = _ycenter;
		// top left of JPanel in JFrame
		xpos = xcenter - bulletXCenter;
		ypos = ycenter - bulletYCenter;
		
		xvel = _initXVel + (Math.cos(_fireRotation) * baseSpeed);
		yvel = _initYVel + (Math.sin(_fireRotation) * baseSpeed);
		
		creationTime = System.currentTimeMillis(); // time bullet was created in milliseconds
		expireTime = _expireTime;
		
		this.setBounds((int) xpos, (int) ypos, width, height);

		/*System.out.println("new bullet at x: " + xcenter + " y: " + ycenter);
		System.out.println("xvel: " + xvel + "yvel: " + yvel);*/
		
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
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
		
		xpos = xcenter - bulletXCenter;
		ypos = ycenter - bulletYCenter;
		
		this.setBounds((int) xpos, (int) ypos, width, height);
	}
	
	// checks if a bullet has been in the air for too long
	public boolean isExpired () {
		return System.currentTimeMillis() - creationTime >= expireTime;
	}
}
