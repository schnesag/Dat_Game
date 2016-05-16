import javax.swing.JFrame;

public class Run {

	public static void main(String[] args) {
		
		GameFrame game = new GameFrame("A Game", 500, 500);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);

	}

}
