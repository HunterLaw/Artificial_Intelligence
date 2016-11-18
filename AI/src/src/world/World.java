package src.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.world.objects.EnvObjects;
import src.world.objects.PersonBot;

public class World implements Runnable
{
	
	boolean running = false;
	BufferedImage image;
	JPanel panel;
	Thread thread;
	
	ArrayList<EnvObjects> objects = new ArrayList<EnvObjects>(); 
	PersonBot chars;
	
	
	
	public World(JPanel panel)
	{
		chars = new PersonBot(20,20,10,10);
		this.panel = panel;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	public void update()
	{
		chars.update();
	}
	
	public void render()
	{
		image = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(chars.getColor());
		g.fillRect(chars.getX(), chars.getY(), chars.getWidth(), chars.getHeight());
		g.dispose();
	}
	
	public void draw()
	{
		Graphics2D g = (Graphics2D) panel.getGraphics();
		if(g != null)
		{
			g.drawImage(image,0,0,null);
		}
		g.dispose();
	}
	
	@Override
	public void run() {
		
		while(running)
		{
			update();
			render();
			draw();
			try{
				Thread.sleep(10);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
