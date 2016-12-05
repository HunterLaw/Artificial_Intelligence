package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import src.movement.Direction;

public class Food extends EnvObjects implements Comparator<Food>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int surround = 0;
	ArrayList<Direction> surrounding = new ArrayList<Direction>();
	Random rand = new Random();
	public Food(int x, int y, int width, int height,int sizeOfArray) {
		super(x, y, width, height);
		color = Color.orange;
		texture = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
		
//		System.out.println("X: "+ x + " Y: "+y);
	} 
	public Food() {
		super(-1,-1,-1,-1);
	}
	public void setSurround(int s)
	{
		surround = s;
	}
	
	public void addSurround(Direction dir)
	{
		if(surrounding.contains(dir))System.out.println("Already contains?");
		else
		{
//			System.out.println(toString() + ": Adding "+ dir);
			surrounding.add(dir);
			surround = surrounding.size();
		}
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
//		System.out.println(toString() + ": "+surrounding.toString());
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
	public int compare(Food o1, Food o2) {
		return o1.getSurround()-o2.getSurround();
	}
}
