package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FoodSource extends EnvObjects
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Random rand = new Random();
	
	public FoodSource(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.orange;
		int size = 2+rand.nextInt(2);
//		int size = 3;
		this.width *= size;
		this.height *= size;
//		System.out.println("S:"+size);
//		int quant = rand.nextInt(size*size)-1;
		int[][] s = new int[size][size];
		if(size%2 == 0)
		{
			s[rand.nextInt(2)+(size/2)-1][rand.nextInt(2)+(size/2)-1] =1;
		}
		else
		{
			s[(int)(size/2)][(int)(size/2)]=1;
		}
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
					s[xs][ys] = 1;
				}
			}
			quant--;
		}
//		printArray(s);
//		System.out.println("W:"+width);
		texture = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		for(int ys =0;ys<size;ys++)
		{
			for(int xs = 0;xs<size;xs++)
			{
				if(s[ys][xs] == 1)
				{
					g.fillRect(xs*width, ys*height, width, height);
				
				}
			}
		}
		g.dispose();
	}
	
	public void printArray(int[][] s)
	{
		for(int y = 0;y<s.length;y++)
		{
			for(int x =0; x< s[0].length;x++)
			{
				System.out.print(s[y][x] +" ");
			}
			System.out.println();
		}
	}
	
	public boolean isXYNextTo(int x, int y, int[][] s)
	{
		boolean rc = false;
		if(x != 0 && s[x-1][y] == 1)
			rc = true;
		else if(x != (s.length-1) && s[x+1][y] == 1)
			rc = true;
		else if(y != 0 && s[x][y-1]== 1)
			rc = true;
		else if(y != (s.length-1) && s[x][y+1] == 1)
			rc = true;
		return rc;
	}

}
