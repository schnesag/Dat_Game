

import java.awt.*;
import javax.swing.*;

public class Ship extends JPanel {
	
	int xpos, ypos;
	int width, height;

	public Ship (int _xpos, int _ypos, int _width, int _height) {
		super();

		xpos = _xpos;
		ypos = _ypos;
		
		width = _width;
		height = _height;
		
		this.setBounds(xpos, ypos, width, height);
		
		this.setVisible(true);
			
	}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(0, 0, width, height);
		
		//this.revalidate(); and
		//this.repaint(); to redraw JPanel items
	}
	
	public void increaseX () {
		xpos += 10;
		this.setBounds(xpos, ypos, width, height);
		
		(new SmoothXIncrease()).start();
	}
	
	public void decreaseX () {
		xpos -= 10;
		this.setBounds(xpos, ypos, width, height);
		
		(new SmoothXDecrease()).start();
	}
	
	private class SmoothXIncrease extends Thread {
		public void run () {
			System.out.println("smooth increase thread started");
			//stop(); will stop execution
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }

				xpos += (int) (i / 10);
				setBounds(xpos, ypos, width, height);
			}
		}
	}
	
	private class SmoothXDecrease extends Thread {
		public void run () {
			System.out.println("smooth decrease thread started");
			
			for (int i = 90; i > 0; i --) {
				try { Thread.sleep(16); }
				catch (InterruptedException e) { }
				
				xpos -= (int) (i / 10);
				setBounds(xpos, ypos, width, height);
			}
		}
	}
	
}

