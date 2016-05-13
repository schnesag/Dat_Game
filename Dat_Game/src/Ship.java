import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Ship extends JPanel {
	
	int width, height;
	float xcenter, ycenter;
	int xpos, ypos;
	
	int speed;
	
	boolean rightEnginesOn, leftEnginesOn, upEnginesOn, downEnginesOn = false;
	boolean rightCruising, leftCruising, upCruising, downCruising = false;
	
	Ship self; // used when calling ship in private classes

	public Ship (float _xcenter, float _ycenter, int _width, int _height, int _speed) {
		super();
		self = this;
		
		// dimensions of JPanel
		width = _width;
		height = _height;
		
		// center of JPanel in JFrame
		// use these to move ship's location
		xcenter = _xcenter;
		ycenter = _ycenter;
		// top left of JPanel in JFrame
		// will be calculated just before drawing stage of UpdateShip.run() thread
		xpos = (int) xcenter - (width / 2);
		ypos = (int) ycenter - (height / 2);
		
		speed = _speed;
		
		this.setBounds(xpos, ypos, width, height);
		this.setVisible(true);
		
		(new UpdateShip()).start();
			
	}
	
	private class UpdateShip extends Thread {
		public void run() {
			
			while (true) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				// move ship based on engine status
				if (rightEnginesOn && rightCruising)
					xcenter += speed;
				if (leftEnginesOn && leftCruising)
					xcenter -= speed;
				if (upEnginesOn && upCruising)
					ycenter -= speed;
				if (downEnginesOn && downCruising)
					ycenter += speed;
				
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
		
		g.fillRect(0, 0, width, height);
		
		//this.revalidate(); and
		//this.repaint(); to redraw JPanel items
	}
	
	public void startRightEngines () {
		rightEnginesOn = true;
		(new StartRightThread()).start();
	}
	public void startLeftEngines() {
		leftEnginesOn = true;
		(new StartLeftThread()).start();
	}
	public void startUpEngines() {
		upEnginesOn = true;
		(new StartUpThread()).start();
	}
	public void startDownEngines() {
		downEnginesOn = true;
		(new StartDownThread()).start();
	}
	
	public void stopRightEngines () {
		rightEnginesOn = false;
		(new SlowRightThread()).start();
	}
	public void stopLeftEngines () {
		leftEnginesOn = false;
		(new SlowLeftThread()).start();
	}
	public void stopUpEngines () {
		upEnginesOn = false;
		(new SlowUpThread()).start();
	}
	public void stopDownEngines () {
		downEnginesOn = false;
		(new SlowDownThread()).start();
	}
	
	private class StartRightThread extends Thread {
		public void run () {
			System.out.println("speeding up right movement thread started");
			
			for (int i = 1; i <= 99; i ++) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (rightEnginesOn == false) // kill thread if right engines turn off
					this.stop();
				
				xcenter += speed * i * 0.01; // messed up for testing purposes
			}
			rightCruising = true;
		}
	}
	private class StartLeftThread extends Thread {
		public void run () {
			System.out.println("speeding up left movement thread started");
			
			for (int i = 1; i <= 99; i ++) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (leftEnginesOn == false) // kill thread if right engines turn off
					this.stop();
				
				xcenter -= speed * i * 0.01; // messed up for testing purposes
			}
			leftCruising = true;
		}
	}
	private class StartUpThread extends Thread {
		public void run () {
			System.out.println("speeding up up movement thread started");
			
			for (int i = 1; i <= 99; i ++) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (upEnginesOn == false) // kill thread if right engines turn off
					this.stop();
				
				ycenter -= speed * i * 0.01; // messed up for testing purposes
			}
			upCruising = true;
		}
	}
	private class StartDownThread extends Thread {
		public void run () {
			System.out.println("speeding up down movement thread started");
			
			for (int i = 1; i <= 99; i ++) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (downEnginesOn == false) // kill thread if right engines turn off
					this.stop();
				
				ycenter += speed * i * 0.01; // messed up for testing purposes
			}
			downCruising = true;
		}
	}
	
	public class SlowRightThread extends Thread {
		public void run () {
			System.out.println("slowing right movement thread started");
			
			rightCruising = false;
			
			for (int i = 99; i >= 1; i --) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (rightEnginesOn) // kill thread if right engines turn back on
					this.stop();
				
				xcenter += speed * i * 0.01; // messed up for testing purposes
			}
		}
	}
	public class SlowLeftThread extends Thread {
		public void run () {
			System.out.println("slowing left movement thread started");
			
			leftCruising = false;
			
			for (int i = 99; i > 0; i --) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (leftEnginesOn) // kill thread if right engines turn back on
					this.stop();
				
				xcenter -= speed * i * 0.01; // messed up for testing purposes
			}
		}
	}
	public class SlowUpThread extends Thread {
		public void run () {
			System.out.println("slowing up movement thread started");
			
			upCruising = false;
			
			for (int i = 99; i > 0; i --) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (upEnginesOn) // kill thread if right engines turn back on
					this.stop();
				
				ycenter -= speed * i * 0.01; // messed up for testing purposes
			}
		}
	}
	public class SlowDownThread extends Thread {
		public void run () {
			System.out.println("slowing down movement thread started");
			
			downCruising = false;
			
			for (int i = 99; i > 0; i --) {
				try { Thread.sleep(16); } catch (InterruptedException e) { }
				
				if (downEnginesOn) // kill thread if right engines turn back on
					this.stop();
				
				ycenter += speed * i * 0.01; // messed up for testing purposes
			}
		}
	}
	
	
	/*private class SmoothXIncrease extends Thread {
		public void run () {
			System.out.println("smooth x increase thread started");
			//stop(); will stop execution
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }

				xcenter += (float) speed * i;
			}
		}
	}
	
	private class SmoothXDecrease extends Thread {
		public void run () {
			System.out.println("smooth x decrease thread started");
			//stop(); will stop execution
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }

				xcenter -= (int) (i / 10);
			}
		}
	}
	
	private class SmoothYDecrease extends Thread {
		public void run () {
			System.out.println("smooth y decrease thread started");
			
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				ycenter -= (int) (i / 10);
			}
		}
	}
	
	private class SmoothYIncrease extends Thread {
		public void run () {
			System.out.println("smooth y increase thread started");
			
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				ycenter += (int) (i / 10);
			}
		}
	}*/
	
	
	/*public void activateRightEngines () {
		if (rightEnginesOn == false) {
			rightEnginesOn = true;
			xcenter += 10;
			System.out.println("was false");
		}
		
		else {
			(new SmoothXIncrease()).start();
			rightEnginesOn = false;
			System.out.println("was true");
		}
	}
	
	public void activateLeftEngines () {
		if (leftEnginesOn == false) {
			leftEnginesOn = true;
			xcenter -= 10;
		}
		
		else {
			leftEnginesOn = false;
			(new SmoothXDecrease()).start();
		}
	}
	
	public void activateUpEngines () {
		if (upEnginesOn == false) {
			upEnginesOn = true;
			ycenter -= 10;
		}
		
		else {
			upEnginesOn = false;
			(new SmoothYDecrease()).start();
		}
	}

	public void activateDownEngines () {
		if (downEnginesOn == false) {
			downEnginesOn = true;
			ycenter += 10;
		}
		
		else {
			downEnginesOn = false;
			(new SmoothYIncrease()).start();
		}
	}*/
	
	/*public void moveRight () {
		xcenter += 10;
		
		(new SmoothXIncrease()).start();
	}
	
	public void moveLeft () {
		xcenter -= 10;
		
		(new SmoothXDecrease()).start();
	}
	public void moveUp () {
		ycenter -= 10;
		
		(new SmoothYDecrease()).start();
	}
	
	public void moveDown () {
		ycenter += 10;
		
		(new SmoothYIncrease()).start();
	}*/

	
	
}