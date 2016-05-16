import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Ship extends JPanel {
	
	GameFrame parent;
	
	int width, height;
	double xcenter, ycenter;
	int xpos, ypos;
	
	double xvel, yvel = 0;
	double acceleration;
	double maxvel = 6;
	
	double rotation = - (Math.PI / 2); // starting rotation for ship
	double rotationRate = 0.1; // radians per frame ship turns
	
	// coordinates at center of ship relative to JPanel
	double shipXCenter;
	double shipYCenter;
	
	// X and Y coordinates for ship art
	int[] artXPoints;
	int[] artYPoints;
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	/* @TODO, create engine objects.  Then we won't need 16 classes and methods for the ship movement,
	 *  only 4! */
	
	boolean forwardEnginesOn, reverseEnginesOn = false;
	boolean rotatingRight, rotatingLeft = false;
	
	Ship self; // used when calling ship in private classes

	public Ship (GameFrame _parent, float _xcenter, float _ycenter, int _width, int _height, double _acceleration) {
		super();
		self = this;
		
		parent = _parent;
		
		// dimensions of JPanel
		width = _width;
		height = _height;
		
		shipXCenter = width / 2;
		shipYCenter = height / 2;
		
		// center of JPanel in JFrame
		// use these to move ship's location
		xcenter = _xcenter;
		ycenter = _ycenter;
		// top left of JPanel in JFrame
		// will be calculated just before drawing stage of UpdateShip.run() thread
		xpos = (int) xcenter - (width / 2);
		ypos = (int) ycenter - (height / 2);
		
		// triangle JPanel art positions
		artXPoints = new int[3];
		artYPoints = new int[3];
		
		// rate which velocities increase
		acceleration = _acceleration;
		
		this.setBounds(xpos, ypos, width, height);
		this.setVisible(true);
		
		(new UpdateShip()).start();
			
	}
	
	private class UpdateShip extends Thread {
		public void run() {
			
			while (true) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				
				// screen wrapping for ship in JFrame parent
				if (xcenter < 0)
					xcenter = parent.width + xcenter;
				else if (xcenter > parent.width)
					xcenter = xcenter - parent.width;
				
				if (ycenter < 0)
					ycenter = parent.height + ycenter;
				else if (ycenter > parent.height)
					ycenter = ycenter - parent.height;
				
				
				// ship rotation
				if (rotatingRight)
					rotation += rotationRate;
				if (rotatingLeft)
					rotation -= rotationRate;
				
				// triangle point rotation
				artXPoints[0] = (int) (shipXCenter + 20 * Math.cos(rotation));
				artXPoints[1] = (int) (shipXCenter + 20 * Math.cos(rotation - (Math.PI * 2 / 3)));
				artXPoints[2] = (int) (shipXCenter + 20 * Math.cos(rotation + (Math.PI * 2 / 3)));
				
				artYPoints[0] = (int) (shipYCenter + 20 * Math.sin(rotation));
				artYPoints[1] = (int) (shipYCenter + 20 * Math.sin(rotation - (Math.PI * 2 / 3)));
				artYPoints[2] = (int) (shipYCenter + 20 * Math.sin(rotation + (Math.PI * 2 / 3)));
				
				// changes ship velocities based on rotation
				if (forwardEnginesOn) {
					xvel += Math.cos(rotation) * acceleration;
					yvel += Math.sin(rotation) * acceleration;
				}
				if (reverseEnginesOn) {
					xvel -= Math.cos(rotation) * acceleration;
					yvel -= Math.sin(rotation) * acceleration;
				}

				
				// add velocities to positions
				xcenter += xvel;
				ycenter += yvel;
				
				// calculate coordinate positions for top left corner of JPanel
				xpos = (int) xcenter - (width / 2);
				ypos = (int) ycenter - (width / 2);
				
				self.repaint(); // redraw contents of JPanel
				self.setBounds(xpos, ypos, width, height); // redraw JPanel in JFrame
			
			}
		}
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		//g.fillRect(0, 0, width, height);
		
		// triangle of ship
		g.fillPolygon(artXPoints, artYPoints, artXPoints.length);
		
		// line pointing to front of triangle
		g.setColor(Color.BLACK);
		g.drawLine((int) shipXCenter, (int) shipYCenter, artXPoints[0], artYPoints[0]);
		
		//this.revalidate(); and
		//this.repaint(); to redraw JPanel items
	}
	

	public void forwardEngines () {
		forwardEnginesOn = !forwardEnginesOn;
	}
	public void reverseEngines () {
		reverseEnginesOn = !reverseEnginesOn;
	}
	
	public void rotateRight () {
		rotatingRight = !rotatingRight;
	}
	public void rotateLeft () {
		rotatingLeft = !rotatingLeft;
	}

}