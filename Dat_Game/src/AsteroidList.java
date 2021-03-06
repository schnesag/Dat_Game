
public class AsteroidList {
	int size = 0;
	AsteroidNode head;
	AsteroidNode tail;
	
	public AsteroidList () {
	}
	
	public void enqueue (Asteroid _newAsteroid) {
		// adds a asteroid() object to a asteroidNode() object and places at end of list
		AsteroidNode newNode = new AsteroidNode (_newAsteroid);
		
		if (size == 0) { // no nodes in queue
			head = newNode;
			tail = newNode;
			head.next = tail;
		}
		
		else { // nodes are in queue, place at end of line
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		}
		
		size ++;
	}
	
	public Asteroid dequeue () {
		// removes the first AsteroidNode() from the queue
		// returns first asteroid
		
		AsteroidNode oldHeadNode = head;
		
		if (size == 1) {
			head = null;
			tail = null;
		}
		
		else if (size > 1) {
			head = head.next;
		}
		
		else if (size <= 0)
			System.out.println("AsteroidQueue Error: trying to dequeue from empty queue");
		
		size --;
		return oldHeadNode.asteroid;
	}
	
	public Asteroid dequeue (int _index) {
		// removes AsteroidNode() '_index' from the queue
		// returns currently selected asteroid
		
		AsteroidNode currentNode = head;
		
		if (size == 1) {
			head = null;
			tail = null;
		}
		
		else if (size > 1) {
			currentNode = head;
			
			if (_index == 0) { // first item
				currentNode = head;
				
				head = currentNode.next;
				currentNode.next.previous = null;
				//currentNode.next = null;
			}
			
			else if (_index == this.size - 1) { // last item
				currentNode = tail;
				
				tail = currentNode.previous;
				currentNode.previous.next = null;
				//currentNode.previous = null;
			}
			
			else { // n'th item
				for (int i = 0; i < _index; i ++) // find node to remove
					currentNode = currentNode.next;
				
					currentNode.previous.next = currentNode.next;
					currentNode.next.previous = currentNode.previous;
					//currentNode.next = null;
					//currentNode.previous = null;
				// use for asteroids and Asteroids
				// move Asteroids into a superclass that controls them with 1 thread
			}
			
			
		}
		
		else if (size <= 0)
			System.out.println("AsteroidQueue Error: trying to dequeue from empty queue");
		
		size --;
		return currentNode.asteroid;
	}
	
	
}
