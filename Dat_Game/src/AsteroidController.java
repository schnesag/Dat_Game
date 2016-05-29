
public class AsteroidController {
	AsteroidController self;
	
	GameFrame parentFrame;
	
	AsteroidList asteroidList;
	
	int levels;
	int splitFactor;
	
	IntegerList asteroidsToRemove;
	
	public AsteroidController (GameFrame _parentFrame, int _num, int _levels, int _splitFactor) {
		self = this;
		
		parentFrame = _parentFrame; // JFrame
		
		asteroidList = new AsteroidList();
		
		levels = _levels; // levels of asteroids there are
		splitFactor = _splitFactor; // # of asteroids each one splits into
		
		asteroidsToRemove = new IntegerList();
		
		for (int i = 0; i < _num; i ++)
			enqueue(new Asteroid(parentFrame, Math.random() * parentFrame.getWidth(), Math.random() * parentFrame.getHeight(),
										30, Math.random() * Math.PI * 2, Math.random() * 4, levels));
		
		(new UpdateAsteroids()).start();
	}
	
	private class UpdateAsteroids extends Thread {
		public void run () {
			
			while (true) {
				// pause until next frame
				try { Thread.sleep(16); }
				catch (InterruptedException e) {System.out.println("AsteroidController: thread didn't wait"); }
				
				// Remove Asteroids
				// goes through Asteroids checking if their 'alive' boolean is false, if it is, remove the Asteroid
				
				AsteroidNode currentAsteroidNode = asteroidList.head;
				for (int i = 0; i < asteroidList.size; i ++) {
					
					if (! currentAsteroidNode.asteroid.alive) {
						dequeue(i);
						parentFrame.remove(currentAsteroidNode.asteroid);
						
						// if Asteroid just removed was not of the smallest level, spawn more Asteroids
						if (currentAsteroidNode.asteroid.level > 1) {
							for (int k = 0; k < splitFactor; k ++) 
								enqueue(new Asteroid(parentFrame, currentAsteroidNode.asteroid.xcenter, currentAsteroidNode.asteroid.ycenter,
										currentAsteroidNode.asteroid.radius * 2 / 3, Math.random() * Math.PI * 2, currentAsteroidNode.asteroid.vel * 1.2, 
										currentAsteroidNode.asteroid.level - 1));
						}
					}
					
					currentAsteroidNode = currentAsteroidNode.next;
				}
				
				// Move
				// moves asteroids
				currentAsteroidNode = asteroidList.head;
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
	
	// adds an AsteroidList index of an Asteroid that has been shot and needs to be removed
	public void queueRemoveAsteroid (int _indexToRemove) {
		asteroidsToRemove.enqueue(_indexToRemove);
	}
	
}
