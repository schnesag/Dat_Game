
// class to hold Bullet() objects and 
public class BulletNode {
	Bullet bullet;
	BulletNode next;
	BulletNode previous;
	
	public BulletNode (Bullet _bullet) {
		bullet = _bullet;
	}
}