import java.awt.*;
import javax.swing.*;

public class Ship extends JPanel {
	
	int xpos, ypos;
	int width, height;
	
	Ship self; // used when calling ship in private classes

	public Ship (int _xpos, int _ypos, int _width, int _height) {
		super();
		self = this;
		
		xpos = _xpos;
		ypos = _ypos;
		
		width = _width;
		height = _height;
		
		this.setBounds(xpos, ypos, width, height);
		
		this.setVisible(true);
		
		(new updateShip()).start();
			
	}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(0, 0, width, height);
		
		//this.revalidate(); and
		//this.repaint(); to redraw JPanel items
	}
	
	public void moveRight () {
		xpos += 10;
		
		(new SmoothXIncrease()).start();
	}
	
	public void moveLeft () {
		xpos -= 10;
		
		(new SmoothXDecrease()).start();
	}
	public void moveUp () {
		ypos -= 10;
		
		(new SmoothYDecrease()).start();
	}
	
	public void moveDown () {
		ypos += 10;
		
		(new SmoothYIncrease()).start();
	}
	
	private class SmoothXIncrease extends Thread {
		public void run () {
			System.out.println("smooth x increase thread started");
			//stop(); will stop execution
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }

				xpos += (int) (i / 10);
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

				xpos -= (int) (i / 10);
			}
		}
	}
	
	private class SmoothYDecrease extends Thread {
		public void run () {
			System.out.println("smooth y decrease thread started");
			
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				ypos -= (int) (i / 10);
			}
		}
	}
	
	private class SmoothYIncrease extends Thread {
		public void run () {
			System.out.println("smooth y increase thread started");
			
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				ypos += (int) (i / 10);
			}
		}
	}
	
	private class updateShip extends Thread {
		public void run() {
			
			while (true) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				self.repaint();
				self.setBounds(xpos, ypos, width, height);
			
			}
		}
	}
	
	
}