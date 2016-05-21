
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
				// goes through list of indexes, 'asteroidsToRemove', and removed those Asteroids specified from the game
						// if integer is added while this is executing, there's a chance the wrong Asteroid will be removed,
						// as the Asteroid indexes will have changed with the recent removal (impossible to fix unless
						// I hold up Gun UpdateBullets and this update thread with boolean locks)
				IntegerNode currentIntegerNode = asteroidsToRemove.head;
				for (int i = 0; i < asteroidsToRemove.size; i ++) {

					Asteroid removedAsteroid = asteroidList.dequeue(currentIntegerNode.integer);
					parentFrame.remove(removedAsteroid);
					
					asteroidsToRemove.dequeue();
					
					// if removedAsteroid was not of the smallest level, spawn more Asteroids
					if (removedAsteroid.level > 1) {
						for (int k = 0; k < splitFactor; k ++) 
							enqueue(new Asteroid(parentFrame, removedAsteroid.xcenter, removedAsteroid.ycenter,
									removedAsteroid.radius * 2 / 3, Math.random() * Math.PI * 2, removedAsteroid.vel * 1.2, 
									removedAsteroid.level - 1));
					}
					
					currentIntegerNode = currentIntegerNode.next;
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
	
	// adds an AsteroidList index of an Asteroid that has been shot and needs to be removed
	public void queueRemoveAsteroid (int _indexToRemove) {
		asteroidsToRemove.enqueue(_indexToRemove);
	}
	
}
