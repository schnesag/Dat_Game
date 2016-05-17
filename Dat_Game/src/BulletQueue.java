
public class BulletQueue {
	int size = 0;
	BulletNode head;
	BulletNode tail;
	
	public BulletQueue () {
	}
	
	public void enqueue (Bullet _newBullet) {
		// adds a Bullet() object to a BulletNode() object and places in queue
		BulletNode newNode = new BulletNode (_newBullet);
		
		if (size == 0) { // no nodes in queue
			head = newNode;
			tail = newNode;
			head.next = tail;
		}
		
		else { // nodes are in queue, place at end of line
			tail.next = newNode;
			tail = newNode;
		}
		
		size ++;
	}
	
	public void dequeue () {
		// removes the first BulletNode() added from the queue

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
	
}
