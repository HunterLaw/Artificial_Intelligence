package src.world.objects;

import java.awt.Color;
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
		s[i][j] = new Water(x+(width*i),y+(width*j),width,height);
		boolean flag = false;
		int quant = rand.nextInt((size*size)-1);
//		System.out.println("Q:"+quant);
//		printArray(s);
		while(quant>=0)
		{
//			System.out.println("QA:"+quant);
			flag = false;
			while(!flag)
			{
				int xs = rand.nextInt(size);
				int ys = rand.nextInt(size);
				if(isXYNextTo(xs,ys,s))
				{
					flag = true;
					s[xs][ys] =  new Water(x+(width*xs),y+(height*ys),width,height);
				}
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
