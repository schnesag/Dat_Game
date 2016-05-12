package game.menu;
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame
{
	
	public GameFrame(){
	super();
	this.setSize(500,500);
	JButton aButton = new JButton("This is a button");
	JButton anotherButton = new JButton("This is another button");
	this.getContentPane().add(aButton, BorderLayout.CENTER);
	this.getContentPane().add(anotherButton, BorderLayout.PAGE_START);
	
	}
	
}
