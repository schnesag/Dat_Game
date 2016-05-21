
public class AsteroidController {
	AsteroidController self;
	
	GameFrame parentFrame;
	
	AsteroidList asteroidList;
	
	int levels;
	int splitFactor;
	
	BulletList collidedBullets;
	
	public AsteroidController (GameFrame _parentFrame, int _num, int _levels, int _splitFactor) {
		self = this;
		
		parentFrame = _parentFrame; // JFrame
		
		asteroidList = new AsteroidList();
		
		levels = _levels; // levels of asteroids there are
		splitFactor = _splitFactor; // # of asteroids each one splits into
		
		collidedBullets = new BulletList();
		
		for (int i = 0; i < _num; i ++)
			enqueue(new Asteroid(parentFrame, Math.random() * parentFrame.getWidth(), Math.random() * parentFrame.getHeight(),
										30, Math.random() * Math.PI * 2, Math.random() * 4));
		
		(new UpdateAsteroids()).start();
	}
	
	private class UpdateAsteroids extends Thread {
		public void run () {
			
			while (true) {
				// pause until next frame
				try { Thread.sleep(16); }
				catch (InterruptedException e) {System.out.println("AsteroidController: thread didn't wait"); }
				
				// Check Collisions
				// loops through collidedBullets, finding which asteroids they collided with
				while (collidedBullets.size > 0) {
					BulletNode currentBulletNode = collidedBullets.head;
					
					for (int i = 0; i < collidedBullets.size; i ++) { // loop through Bullets
						
						AsteroidNode currentAsteroidNode = asteroidList.head;;
						
						for (int k = 0; k < asteroidList.size; k ++) { // loop through Asteroids
							
							// if collision has happened
							if (currentBulletNode.bullet.collided(currentAsteroidNode.asteroid.xcenter,
									currentAsteroidNode.asteroid.ycenter, currentAsteroidNode.asteroid.radius)) {
								
								parentFrame.remove(currentAsteroidNode.asteroid);
								asteroidList.dequeue(k);

							}
							currentAsteroidNode = currentAsteroidNode.next;
						}
						collidedBullets.dequeue(i);
						currentBulletNode = currentBulletNode.next;
					}
				}
				
				// Move
				// moves asteroids
				AsteroidNode currentAsteroidNode = asteroidList.head;
				for (int i = 0; i < asteroidList.size; i ++) {
					currentAsteroidNode.asteroid.updatePosition();
					currentAsteroidNode = currentAsteroidNode.next;
				}
				
			}
			
		}
	}
	
	public void enqueue (Asteroid _newAsteroid) {
		asteroidList.enqueue(_newAsteroid);
	}
	
	public void dequeue () {
		asteroidList.dequeue();
	}
	
	public void dequeue (int _index) {
		asteroidList.dequeue(_index);
	}
	
	// adds a Bullet to check for collisions against
	public void addCollisionCheck (Bullet _bulletToCheck) {
		collidedBullets.enqueue(_bulletToCheck);
	}
	
}
