package src.world.objects;

import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

import src.movement.Direction;

public class WaterSource extends Sources{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666105555535577495L;

	Random rand = new Random();
	PriorityQueue<Water> pq = new PriorityQueue<Water>(new Water());
	public WaterSource(int x, int y, int width, int height) {
		super(x, y, width, height,true);
//		System.out.println(this);
		color = Color.cyan;
		int size = 4+rand.nextInt(5);
//		int size = 3;
		this.width *= size;
		this.height *= size;
//		System.out.println("S:"+size);
		int quant = rand.nextInt((size*size)-1);
		Water[][] s = new Water[size][size];
		int i,j;
		if(size%2 == 0)
		{
			i = rand.nextInt(2)+(size/2)-1;
			j = rand.nextInt(2)+(size/2)-1;
		}
		else
		{
			i = (int)(size/2);
			j = (int)(size/2);
		}
		s[i][j] = new Water(i,j,width,height,size);
		insertResources(s[i][j],s);
//		int quant = rand.nextInt((size*size)-1);
//		int quant = 7;
//		System.out.println("Q:"+quant);
//		printArray(s);
		while(quant>=0)
		{
//			printArray(s);
//			System.out.println("Quant: "+quant);
			pq.clear();
//			pq.el
			for(EnvObjects e:resources)
			{
				if(((Water)e).surround != 0)
					pq.add((Water)e);
			}
//			printArray(s);
//			Water w = hasOneInPQ(pq);
			Point p;
//			if(w == null)
			p = pq.peek().getRandSurroundPoint();
//			else
//				p = w.getRandSurroundPoint();
//			printPriorityQueue(pq);
			s[p.x][p.y] = new Water(p.x,p.y,width,height,size);
			insertResources(s[p.x][p.y],s);
			quant--;
		}
//		printArray(s);
//		System.out.println("W:"+width);
//		texture = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = (Graphics2D) texture.getGraphics();
//		g.setColor(color);
//		System.out.println(water.size());
//		printArray(s);
		for(EnvObjects w: resources)
		{
			Water w2 = (Water)w;
			w2.setX(x+(w2.getX()*width));
			w2.setY(y+(w2.getY()*height));
		}
//		System.out.println(water.size());
//		g.dispose();
	}
	
	
	
	public void insertResources(Water water, Water[][] w)
	{
		int x = water.getX(),y = water.getY();
		if(x != 0)
		{
			//Left
			if(w[x-1][y] == null)
			{
				water.addSurround(Direction.left);
			}
			else
			{
				w[x-1][y].removeSurround(Direction.right);
			}
		}
		if(x != w.length-1)
		{
			//Right
			if(w[x+1][y] == null)
			{
				water.addSurround(Direction.right);
			}
			else
			{
				w[x+1][y].removeSurround(Direction.left);
			}
		}
		if(y != 0)
		{
			//Up
			if(w[x][y-1] == null)
			{
				water.addSurround(Direction.up);
			}
			else
			{
				w[x][y-1].removeSurround(Direction.down);
			}
		}
		if(y != w.length-1)
		{
			//Down
			if(w[x][y+1] == null)
			{
				water.addSurround(Direction.down);
			}
			else
			{
				w[x][y+1].removeSurround(Direction.up);
			}
		}
		resources.add(water);
	}
	
	public int countWater(Water[][] w)
	{
		int sum =0;
		for(int x =0;x < w.length;x++)
		{
			for(int y =0;y < w[0].length;y++)
			{
				if(w[x][y] != null)
				{
					sum++;
				}
				
			}
		}
		return sum;
	}
	
	public Water hasOneInPQ(PriorityQueue<Water> p)
	{
		Water rc = null;
		Iterator<Water> it = p.iterator();
		while(it.hasNext())
		{
			rc = it.next();
			if(rc.surround == 1)
			{
				return rc;
			}
		}
		return null;
	}
	
	public boolean isXYNextTo(int x, int y, Water[][] s)
	{
		boolean rc = false;
		if(x != 0 && s[x-1][y] instanceof Water)
			rc = true;
		else if(x != (s.length-1) && s[x+1][y] instanceof Water)
			rc = true;
		else if(y != 0 && s[x][y-1] instanceof Water)
			rc = true;
		else if(y != (s.length-1) && s[x][y+1] instanceof Water)
			rc = true;
		return rc;
	}

	public void printArray(Water[][] w)
	{
		for(int x =0;x < w.length;x++)
		{
			for(int y =0;y < w[0].length;y++)
			{
				if(w[y][x] != null)
				{
					System.out.print(" "+w[y][x].surround);
				}
				else
				{
					System.out.print("-1");
				}
				if(y+1 != w[0].length)
				{
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
	
	public void printPriorityQueue(PriorityQueue<Water> p)
	{
		for(int i =0;i< p.size();i++)
		{
			System.out.print(p.poll().getSurround() + " ");
		}
		System.out.println();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
