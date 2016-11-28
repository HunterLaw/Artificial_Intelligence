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
		int size = rand.nextInt(3)+1;
//		int quant = rand.nextInt(size*size)-1;
		int[][] s = new int[size][size];
		if(size%2 == 0)
		{
			s[rand.nextInt(1)+1][rand.nextInt(1)+1] =1;
		}
		boolean flag = false;
		for(int quant = rand.nextInt(size*size)-1;quant>=0;quant--)
		{
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
		}
		texture = new BufferedImage(size*width,size*height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		for(int ys =0;ys<size;ys++)
		{
			for(int xs = 0;xs<size;xs++)
			{
				g.fillRect(xs*width, ys*height, width, height);
			}
		}
		g.dispose();
	}
	
	public boolean isXYNextTo(int x, int y, int[][] s)
	{
		if(s[x-1][y] == 1 || s[x+1][y] == 1 || s[x][y-1]== 1 || s[x][y+1] == 1)return true;
		return false;
	}

}
