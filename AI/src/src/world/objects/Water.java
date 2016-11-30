package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import src.movement.Direction;

public class Water extends EnvObjects{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int surround = -1;
	ArrayList<Direction> surrounding = new ArrayList<Direction>();
	Random rand = new Random();
	public Water(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.cyan;
		texture = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
//		System.out.println("X: "+ x + " Y: "+y);
	}
	
	public void setSurround(int s)
	{
		surround = s;
	}
	
	public void subSurround()
	{
		surround--;
	}
	
	public Point getRandSurroundPoint()
	{
		Point p = new Point();
		int i = rand.nextInt(surrounding.size());
		switch(surrounding.get(i))
		{
		case left:
			p.x = (int)x-1;
			p.y = (int)y;
			break;
		case right:
			p.x = (int)x+1;
			p.y = (int)y;
			break;
		case up:
			p.x = (int)x+1;
			p.y = (int)y;
			break;
		case down:
			p.x = (int)x+1;
			p.y = (int)y;
			break;
		}
		return p;
	}
	
	public int getSurround()
	{
		return surround;
	}

}
