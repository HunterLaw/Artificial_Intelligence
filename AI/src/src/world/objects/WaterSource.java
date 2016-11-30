package src.world.objects;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import src.objects.NonTexturedObject2D;

public class WaterSource extends Sources{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666105555535577495L;

	Random rand = new Random();
	public WaterSource(int x, int y, int width, int height) {
		super(x, y, width, height,true);
		color = Color.cyan;
		int size = 4+rand.nextInt(5);
//		int size = 3;
		this.width *= size;
		this.height *= size;
//		System.out.println("S:"+size);
//		int quant = rand.nextInt(size*size)-1;
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
		s[i][j] = new Water(i,j,width,height);
		s[i][j].setSurround(4);
		insertResources(s[i][j],s);
		boolean flag = false;
		int quant = rand.nextInt((size*size)-1);
//		System.out.println("Q:"+quant);
//		printArray(s);
		while(quant>=0)
		{
			int surround = ((Water)resources.get(0)).getSurround();
			int n = rand.nextInt(surround);
			switch(n)
			{
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
				default:
			}
			quant--;
		}
//		printArray(s);
//		System.out.println("W:"+width);
//		texture = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = (Graphics2D) texture.getGraphics();
//		g.setColor(color);
//		System.out.println(water.size());

		for(int ys =0;ys<size;ys++)
		{
			for(int xs = 0;xs<size;xs++)
			{
				if(s[ys][xs] != null)
				{
					resources.add(s[ys][xs]);
				}
			}
		}
//		System.out.println(water.size());
//		g.dispose();
	}
	
	
	
	public void insertResources(Water water, Water[][] w)
	{
		int sum = 0;
		int x = water.getX(),y = water.getY();
		if(x != 0)
		{
			if(w[x-1][y] == null)
				sum++;
			else
				w[x-1][y].subSurround();
		}
		if(x != w.length)
		{
			if(w[x+1][y] == null)
				sum++;
			else
				w[x+1][y].subSurround();
		}
		if(y != 0)
		{
			if(w[x][y-1] == null)
				sum++;
			else
				w[x][y-1].subSurround();
		}
		if(y != w.length)
		{
			if(w[x][y+1] == null)
				sum++;
			else
				w[x][y+1].subSurround();
		}
		water.setSurround(sum);
		if(resources.size() == 0) resources.add(water);
		else
		{
			for(int i =0;i < resources.size();i++)
			{
				if(sum <=((Water)resources.get(i)).getSurround())
				{
					resources.add(i, water);
					return;
				}
				else if(i+1 == resources.size())
				{
					resources.add(water);
				}
			}
		}
	}
	
	public Point findFourWay(Water[][] s)
	{
		Point p = new Point(-1,-1);
		
		return p;
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
