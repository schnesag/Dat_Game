import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Ship extends JPanel {
	
	GameFrame parentFrame;
	
	int width, height;
	double xcenter, ycenter;
	int xpos, ypos;
	
	double xvel, yvel = 0;
	double acceleration;
	double maxspeed = 3;
	
	// in RADIANS
	double rotation = - (Math.PI / 2); // starting rotation for ship (clockwise from west direction)
	double rotationRate = 0.1; // radians per frame ship turns
	
	// coordinates at center of ship relative to JPanel
	double shipXCenter;
	double shipYCenter;
	
	// X and Y coordinates for ship art
	int[] artXPoints;
	int[] artYPoints;
	
	// are set by key bind methods to tell UpdateShip thread when and where to move
	boolean forwardEnginesOn, reverseEnginesOn = false;
	boolean rotatingRight, rotatingLeft = false;
	
	// Ship stat displays
	JLabel xLabel;
	JLabel yLabel;
	
	Gun mainGun;
	
	Ship self; // used when calling ship in private classes

	public Ship (GameFrame _parentFrame, float _xcenter, float _ycenter, int _width, int _height, double _acceleration) {
		super();
		self = this;
		parentFrame = _parentFrame;

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
		xpos = (int) (xcenter - shipXCenter);
		ypos = (int) (ycenter - shipYCenter);
		
		// triangle JPanel art positions
		artXPoints = new int[3];
		artYPoints = new int[3];
		
		// rate which velocities increase
		acceleration = _acceleration;
		
		xLabel = new JLabel("test");
		yLabel = new JLabel("test2");
		
		mainGun = new Gun(parentFrame, this);
		
		this.setLayout(null);
		
		// Labels for X and Y position
		xLabel.setLocation((int) (width * 0.125), (int) (height * 0.8));
		xLabel.setSize((int) (width * 0.5), (int) (height * 0.15));
		this.add(xLabel);
		
		yLabel.setLocation((int) (width * 0.625), (int) (height * 0.8));
		yLabel.setSize((int) (width * 0.5), (int) (height * 0.15));
		this.add(yLabel);
		
		// draw
		this.setBounds(xpos, ypos, width, height);
		this.setVisible(true);
		
		(new UpdateShip()).start(); // start UpdateShip  thread
		
	}
	
	private class UpdateShip extends Thread {
		public void run() {
			
			while (true) {
				// pause until next frame
				try { Thread.sleep(16); }
				catch (InterruptedException e) { System.out.println("Ship: thread didn't wait"); }
				
				// screen wrapping for ship in JFrame parentFrame
				if (xcenter < 0)
					xcenter = parentFrame.getWidth() + xcenter;
				else if (xcenter > parentFrame.getWidth())
					xcenter = xcenter - parentFrame.getWidth();
				
				if (ycenter < 0)
					ycenter = parentFrame.getHeight() + ycenter;
				else if (ycenter > parentFrame.getHeight())
					ycenter = ycenter - parentFrame.getHeight();
				
				
				// ship rotation
				if (rotatingRight)
					rotation += rotationRate;
				if (rotatingLeft)
					rotation -= rotationRate;
				
				// triangle point rotation based on rotation
				artXPoints[0] = (int) (shipXCenter + 20 * Math.cos(rotation));
				artXPoints[1] = (int) (shipXCenter + 20 * Math.cos(rotation - (Math.PI * 2 / 3)));
				artXPoints[2] = (int) (shipXCenter + 20 * Math.cos(rotation + (Math.PI * 2 / 3)));
				
				artYPoints[0] = (int) (shipYCenter + 20 * Math.sin(rotation));
				artYPoints[1] = (int) (shipYCenter + 20 * Math.sin(rotation - (Math.PI * 2 / 3)));
				artYPoints[2] = (int) (shipYCenter + 20 * Math.sin(rotation + (Math.PI * 2 / 3)));
				
				// changes ship velocities based on rotation
				// checks whether combined velocity is less than the maximum allowed ( maxspeed )
				if (forwardEnginesOn && Math.sqrt(Math.pow(xvel, 2) + Math.pow(yvel, 2)) <= maxspeed) {
					xvel += Math.cos(rotation) * acceleration;
					yvel += Math.sin(rotation) * acceleration;
				}
				
				if (reverseEnginesOn && Math.sqrt(Math.pow(xvel, 2) + Math.pow(yvel, 2)) <= maxspeed) {
					xvel -= Math.cos(rotation) * acceleration;
					yvel -= Math.sin(rotation) * acceleration;
				}

				// slowly slow down ship
				xvel *= 0.99;
				yvel *= 0.99;
				
				// add velocities to positions
				xcenter += xvel;
				ycenter += yvel;
				
				// calculate coordinate positions for top left corner of JPanel
				xpos = (int) xcenter - (width / 2);
				ypos = (int) ycenter - (width / 2);
				
				xLabel.setText(String.valueOf((int) xcenter));
				yLabel.setText(String.valueOf((int) ycenter));
				
				self.repaint(); // redraw contents of JPanel
				self.setBounds(xpos, ypos, width, height); // redraw JPanel in JFrame
			
			}
		}
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
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
	
	// activate main gun
	public void fireMain () {
		mainGun.activate();
	}
	
}