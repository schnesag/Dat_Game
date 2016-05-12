package game.menu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameFrame extends JFrame
{
	JButton aButton;
	JButton anotherButton;
	JButton anotherAnotherButton;
		public GameFrame()
		{
			super();
			this.setSize(500,500);
			aButton = new JButton("This is a button");
			anotherButton = new JButton("This is another button");
			anotherAnotherButton = new JButton("This is another another button");
			this.getContentPane().add(aButton, BorderLayout.CENTER);
			this.getContentPane().add(anotherButton, BorderLayout.PAGE_START);
			this.getContentPane().add(anotherAnotherButton, BorderLayout.PAGE_END);
			addListeners();
		}
	
	// Method to add listeners to the buttons.
	private void addListeners()
	{
		aButton.addActionListener( new aButtonListener());
		anotherButton.addActionListener(new anotherButtonListener());
		anotherAnotherButton.addActionListener(new anotherAnotherButtonListener());
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
	private class anotherAnotherButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("You pressed Another Another Button!");
		}
	}

	
}
