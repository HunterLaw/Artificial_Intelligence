package src.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import src.UI.FPS;
import src.UI.Renderer2D;
import src.UI.ScrollingMap;
import src.movement.Direction;
import src.objects.NonTexturedObject2D;
import src.world.objects.FoodSource;
import src.world.objects.PersonBot;
import src.world.objects.Sources;
import src.world.objects.WaterSource;

public class World implements Runnable
{
	
	boolean running = false;
	BufferedImage image;
	JPanel panel;
	Thread thread;
	
	ArrayList<NonTexturedObject2D> objects = new ArrayList<NonTexturedObject2D>();
	ArrayList<Sources> envobjects = new ArrayList<Sources>();
	PersonBot chars;
	
	FPS fps = new FPS(60);
	ScrollingMap map;
	Renderer2D render;
	
	static File bgs;
	
	Random rand = new Random();

	
	public World(JPanel panel)
	{
		chars = new PersonBot(20,20,10,10);
		
		render  = new Renderer2D(640*2, 480*2, BufferedImage.TYPE_INT_RGB);
		map = new ScrollingMap(0,0,640*2,480*2,640,480);
		map.setCharacter(chars);
		bgs = new File(this.getClass().getClassLoader().getResource("media/TestScrolling.png").getPath());
		map.loadBasicImage(bgs);
		
		for(int i = rand.nextInt(5);i>=0;i--)
		{
			System.out.println(i);
			envobjects.add(new FoodSource(rand.nextInt((640*2)-(10*3)),rand.nextInt((480*2)-(10*3)),10,10));
			envobjects.add(new WaterSource(rand.nextInt((640*2)-(10*8)),rand.nextInt((480*2)-(10*8)),10,10));
//			System.out.println(objects.size());
		}
		for(Sources s: envobjects)
		{
			objects.addAll(s.getObjects());
		}
		objects.add(chars);
		
		render.setObjectArray(objects);
		
		render.setBgObject(map, map.getWidth(), map.getHeight());

		
		this.panel = panel;
		
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	public void update()
	{
		chars.update();
		map.update(objects, Direction.right, Direction.up);
	}
	
	public void render()
	{
		image = render.render();
//		image = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = (Graphics2D) image.getGraphics();
//		g.setColor(Color.lightGray);
//		g.fillRect(0, 0, 640, 480);
//		g.setColor(chars.getColor());
//		g.fillRect(chars.getX(), chars.getY(), chars.getWidth(), chars.getHeight());
//		g.dispose();
	}
	
	public void draw()
	{
		Graphics2D g = (Graphics2D) panel.getGraphics();
		if(g != null)
		{
			g.drawImage(image,map.getWinX(),map.getWinY(),null);
		}
		g.dispose();
	}
	
	@Override
	public void run() {
		while(running)
		{
			if(fps.secondDone())
			{
				fps.startFPSTime();
			}
			if(fps.update())
			{
				update();
			}
			render();
			draw();
			fps.addFPS();
			try{
				Thread.sleep(fps.getWaitTime());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
