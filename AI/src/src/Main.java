package src;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.UI.Window;
import src.world.World;

public class Main
{
	static Window frame;
	static JPanel panel; 
	static World world;
	
	public static void main(String[] args)
	{
		GUI();
		world = new World(panel);
	}
	
	public static void GUI()
	{
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(640*2,480*2));
		panel.setLayout(null);

		frame = new Window(panel,"AI");
		System.out.println(frame.getSize());
	}
	
}
