

public class Gun {
	
	GameFrame parentFrame;
	Ship originShip;
	
	double xcenter, ycenter;
	
	BulletList bulletList = new BulletList();
	
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
				
				// update Gun() position, where bullets emanate from
				xcenter = originShip.artXPoints[0] + originShip.xpos;
				ycenter = originShip.artYPoints[0] + originShip.ypos;
				
				// fire new bullet ( if gun has waited enough until next shot)
				if (firing && System.currentTimeMillis() - lastFireTime >= shotDelay)
					fire();
				
				// MOVEMENT
				// iterate through Bullet()'s in bulletList, updating their positions and redrawing
				BulletNode currentBulletNode = bulletList.head;
				for (int i = 0; i < bulletList.size; i ++) {
					currentBulletNode.bullet.updatePosition();
					currentBulletNode = currentBulletNode.next;
				}
				

				// TODO implement class with Asteroids
				
				// COLLISIONS
				// iterates through Bullets, for each Bullet, iterates through Asteroids
				// checks if Bullets have collided with Asteroids
				currentBulletNode = bulletList.head;
				
				for (int i = 0; i < bulletList.size; i ++) { // loop through Bullets
					
					Asteroid currentAsteroid;
					
					for (int k = 0; k < parentFrame.asteroids.length; k ++) { // loop through Asteroids
						currentAsteroid = parentFrame.asteroids[k];
						// if collision has happened
						if (currentBulletNode.bullet.collided(currentAsteroid.xcenter,
								currentAsteroid.ycenter, currentAsteroid.radius)) {
							
							parentFrame.remove(currentBulletNode.bullet);
							bulletList.dequeue(i);
						}
						
					}
					currentBulletNode = currentBulletNode.next;
				}
				
				// remove time expired bullets
				while (bulletList.size > 0 && bulletList.head.bullet.isExpired()) {
					parentFrame.remove(bulletList.head.bullet);
					bulletList.dequeue();
				}
			}
		}
	}
	
	
	// add Bullet() to bulletList, adds to parentFrame, makes visable
	// TODO possibly remove originShip, sending in ship rotation, xvel, yvel
		//when this method is called from Ship class
	public void fire () {
		Bullet newBullet = new Bullet(parentFrame, xcenter, ycenter, 2.5,
						originShip.rotation, originShip.xvel, originShip.yvel, expireTime);

		bulletList.enqueue(newBullet); // enqueue bullet

		parentFrame.add(newBullet); // add to JFrame
		newBullet.setVisible(true); // set visible
		
		lastFireTime = System.currentTimeMillis(); // remember time this bullet was fired
	}
	
	// set firing boolean to true, so gun starts shooting
	public void activate () {
		firing = !firing;
	}
	
}
