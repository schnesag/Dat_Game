package game.menu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

// this is my COMMENT AHHHHH

public class GameFrame extends JFrame
{
	JButton aButton;
	JButton anotherButton;
	
		public GameFrame()
		{
			super();
			this.setSize(500,500);
			aButton = new JButton("This is a button");
			anotherButton = new JButton("This is another button");
			this.getContentPane().add(aButton, BorderLayout.CENTER);
			this.getContentPane().add(anotherButton, BorderLayout.PAGE_START);
			addListeners();
		}
	
	// Method to add listeners to the buttons.
	private void addListeners()
	{
		aButton.addActionListener( new aButtonListener());
		anotherButton.addActionListener(new anotherButtonListener());
	}
	
	//********* Adding Listeners ************
	private class aButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("You pressed a Button!");
			
		}

	}
	
	private class anotherButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("You pressed Another Button!");
		}
	}

	
}
