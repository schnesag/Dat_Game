
// class to hold Asteroid() objects and 
public class AsteroidNode {
	Asteroid asteroid;
	AsteroidNode next;
	AsteroidNode previous;
	
	public AsteroidNode (Asteroid _asteroid) {
		asteroid = _asteroid;
	}
}