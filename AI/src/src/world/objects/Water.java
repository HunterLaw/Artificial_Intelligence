package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import src.movement.Direction;

public class Water extends EnvObjects implements Comparator<Water>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int surround = -1;
	ArrayList<Direction> surrounding = new ArrayList<Direction>();
	Random rand = new Random();
	public Water(int x, int y, int width, int height,int sizeOfArray) {
		super(x, y, width, height);
		color = Color.cyan;
		texture = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
		if(x != 0)
		{
			surrounding.add(Direction.left);
		}
		if(x != sizeOfArray-1)
		{
			surrounding.add(Direction.right);
		}
		if(y != 0)
		{
			surrounding.add(Direction.up);
		}
		if(y != sizeOfArray-1)	
		{
			surrounding.add(Direction.down);
		}
		
		System.out.println("here");
//		System.out.println("X: "+ x + " Y: "+y);
	} 
	public Water() {
		super(-1,-1,-1,-1);
	}
	public void setSurround(int s)
	{
		surround = s;
	}
	
	public void removeSurround(Direction dir)
	{
		if(surrounding.remove(dir))surround--;
		if(surround < 0) System.out.println("removed "+dir + " from "+toString());
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
			p.x = (int)x;
			p.y = (int)y-1;
			break;
		case down:
			p.x = (int)x;
			p.y = (int)y+1;
			break;
		default:
			break;
		}
		return p;
	}
	
	public int getSurround()
	{
		return surround;
	}

	@Override
	public int compare(Water o1, Water o2) {
		return o2.getSurround()-o1.getSurround();
	}

}
