

public class Gun {
	
	GameFrame parentFrame;
	Ship originShip;
	
	double xcenter, ycenter;
	
	BulletQueue queue = new BulletQueue();
	
	boolean firing = false;

	long expireTime; // time bullets stay in air for in milliseconds
	long lastFireTime; // last time a bullet was fired
	long shotDelay; // minimum milliseconds between shots
	
	public Gun (GameFrame _parentFrame, Ship _originShip, long _expireTime, long _shotDelay) {
		parentFrame = _parentFrame; // JFrame we'll be drawing in
		originShip = _originShip; // ship that fired
		
		expireTime = _expireTime;
		shotDelay = _shotDelay;
		
		// bullet origin position
		xcenter = originShip.artXPoints[0] + originShip.xpos;
		ycenter = originShip.artYPoints[0] + originShip.ypos;
		
		(new UpdateBullets()).start();
	}
	
	private class UpdateBullets extends Thread {
		public void run () {
			while (true) {
				// pause until next frame
				try { Thread.sleep(16); }
				catch (InterruptedException e) {System.out.println("Gun: thread didn't wait"); }
				
				// update Gun() position, where bullets eminate from
				xcenter = originShip.artXPoints[0] + originShip.xpos;
				ycenter = originShip.artYPoints[0] + originShip.ypos;
				
				// fire new bullet ( if gun has waited enough until next shot)
				if (firing && System.currentTimeMillis() - lastFireTime >= shotDelay)
					fire();
				
				// iterate through Bullet()'s in queue, updating their positions
				BulletNode currentBulletNode = queue.head;
				for (int i = 0; i < queue.size; i ++) {
					currentBulletNode.data.updatePosition();
					currentBulletNode = currentBulletNode.next;
				}
				
				// remove expired bullets
				while (queue.size > 0 && queue.head.data.isExpired()) {
					parentFrame.remove(queue.head.data);
					queue.dequeue();
				}
			}
		}
	}
	
	
	// add Bullet() to queue, adds to parentFrame, makes visable
	// TODO possibly remove originShip, sending in ship rotation, xvel, yvel
		//when this method is called from Ship class
	public void fire () {
		Bullet newBullet = new Bullet(parentFrame, xcenter, ycenter,
						originShip.rotation, originShip.xvel, originShip.yvel, expireTime);

		queue.enqueue(newBullet); // enqueue bullet

		parentFrame.add(newBullet); // add to JFrame
		newBullet.setVisible(true); // set visible
		
		lastFireTime = System.currentTimeMillis(); // remember time this bullet was fired
	}
	
	// set firing boolean to true, so gun starts shooting
	public void activate () {
		firing = !firing;
	}
	
}
