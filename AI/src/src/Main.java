package src;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main
{
	static JFrame frame;
	static JPanel panel; 
	
	public static void main(String[] args)
	{
		GUI();
	}
	
	public static void GUI()
	{
		frame = new JFrame("AI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(640,480));
		panel.setLayout(null);
		panel.setBackground(Color.green);

		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
