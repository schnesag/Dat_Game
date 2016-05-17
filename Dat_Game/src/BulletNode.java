
// class to hold Bullet() objects and 
public class BulletNode {
	Bullet data;
	BulletNode next;
	
	public BulletNode (Bullet _data) {
		data = _data;
		System.out.println("bullet node created");
	}
}