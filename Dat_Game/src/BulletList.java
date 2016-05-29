
public class BulletList {
	int size = 0;
	BulletNode head;
	BulletNode tail;
	
	public BulletList () {
	}
	
	public void enqueue (Bullet _newBullet) {
		// adds a Bullet() object to a BulletNode() object and places at end of list
		BulletNode newNode = new BulletNode (_newBullet);
		
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
	
	public void dequeue () {
		// removes the first BulletNode() from the queue
		// returns currently selected bullet
		
		if (size == 1) {
			head = null;
			tail = null;
		}
		
		else if (size > 1) {
			head = head.next;
		}
		
		else if (size <= 0)
			System.out.println("BulletQueue Error: trying to dequeue from empty queue");
		
		size --;
	}
	
	public void dequeue (int _index) {
		// removes BulletNode() '_index' from the queue
		// returns currently selected bullet
		
		if (size == 1) {
			head = null;
			tail = null;
		}
		
		else if (size > 1) {
			BulletNode currentNode = head;
			
			if (_index == 0) { // first item
				currentNode = head;
				
				head = currentNode.next;
				currentNode.next.previous = null;
				//currentNode.next = null;
			}
			
			else if (_index == 0) { // first item
				currentNode = head;
				
				currentNode.next.previous = null;
				head = currentNode.next;
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
				// use for Bullets and Asteroids
				// move Asteroids into a superclass that controls them with 1 thread
			}
			
			
		}
		
		else if (size <= 0)
			System.out.println("BulletQueue Error: trying to dequeue from empty queue");
		
		size --;
	}
	
	
}
