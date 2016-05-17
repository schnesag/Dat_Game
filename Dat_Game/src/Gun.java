

public class Gun {
	
	GameFrame parentFrame;
	Ship originShip;
	
	BulletQueue queue = new BulletQueue();
	
	boolean firing = false;
	
	public Gun (GameFrame _parentFrame, Ship _originShip) {
		parentFrame = _parentFrame; // JFrame we'll be drawing in
		originShip = _originShip; // ship that fired
		
		(new UpdateBullets()).start();
	}
	
	private class UpdateBullets extends Thread {
		public void run () {
			while (true) {
				// pause until next frame
				try { Thread.sleep(16); }
				catch (InterruptedException e) {System.out.println("Gun: thread didn't wait"); }
				
				// fire new bullet
				if (firing)
					fire();
				
				// iterate through Bullet()'s in queue, updating their positions
				BulletNode currentBulletNode = queue.head;
				for (int i = 0; i < queue.size; i ++) {
					currentBulletNode.data.updatePosition();
					currentBulletNode = currentBulletNode.next;
				}
			}
		}
	}
	
	// add Bullet() to queue, adds to parentFrame, makes visable
	// TODO possibly remove originShip, sending in ship rotation, xvel, yvel
		//when this method is called from Ship class
	public void fire () {
		Bullet newBullet = new Bullet(parentFrame, originShip.xcenter, originShip.ycenter, originShip.rotation,
						originShip.xvel, originShip.yvel);
		queue.enqueue(newBullet); // enqueue bullet
		parentFrame.add(newBullet); // add to JFrame
		newBullet.setVisible(true); // set visible

	}
	
	// set firing boolean to true, so gun starts shooting
	public void activate () {
		firing = !firing;
	}
}
