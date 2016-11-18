package src;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.world.World;

public class Main
{
	static JFrame frame;
	static JPanel panel; 
	static World world;
	
	public static void main(String[] args)
	{
		GUI();
		world = new World(panel);
	}
	
	public static void GUI()
	{
		frame = new JFrame("AI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(640,480));
		panel.setLayout(null);

		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
