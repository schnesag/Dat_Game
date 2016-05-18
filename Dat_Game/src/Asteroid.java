import javax.swing.*;
import java.awt.*;

public class Asteroid extends JPanel {

	GameFrame parentFrame;
	Asteroid self;
	
	double xcenter, ycenter;
	double xpos, ypos;
	
	double radius;
	double width, height;
	
	double asteroidXCenter;
	double asteroidYCenter;
	
	double xvel, yvel;
	double rotation;
	
	int type = 2; // 2 for big, 1 for medium, 0 for small
	
	// TODO change some of these parameters to random !!
	public Asteroid (GameFrame _parentFrame, double _xcenter, double _ycenter, double _radius, double _rotation, double _vel) {
		parentFrame = _parentFrame;
		self = this;
		
		radius = _radius;
		width = radius * 2;
		height = radius * 2;
		
		asteroidXCenter = width / 2;
		asteroidYCenter = height / 2;
		
		xcenter = _xcenter;
		ycenter = _ycenter;
		
		xpos = xcenter - asteroidXCenter;
		ypos = ycenter - asteroidYCenter;
		
		rotation = _rotation;
		
		xvel = Math.cos(rotation) * _vel;
		yvel = Math.sin(rotation) * _vel;
		
		// !! to be removed and placed in higher classes
		this.setBounds((int) xpos, (int) ypos, (int) width, (int) height);
		this.setVisible(true);
		parentFrame.add(this);
		
		(new UpdateAsteroid()).start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillOval(0, 0, (int) width, (int) height);
	}
	
	private class UpdateAsteroid extends Thread {
		public void run () {
			while (true) {
				// pause until next frame
				try { Thread.sleep(16); }
				catch (InterruptedException e) { System.out.println("Asteroid: thread didn't wait"); }
				
				// screen wrapping for ship in JFrame parentFrame
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
				
				xpos = xcenter - asteroidXCenter;
				ypos = ycenter - asteroidYCenter;
				
				self.repaint();
				self.setBounds((int) xpos, (int) ypos, (int) width, (int) height);
				
			}
		}
	}
	
}
