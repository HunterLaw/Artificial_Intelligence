package src.world.objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class FoodSource extends Sources
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Random rand = new Random();
	
	
	public FoodSource(int x, int y, int width, int height) {
		super(x, y, width, height,true);
		color = Color.orange;
		int size = 2+rand.nextInt(2);
//		int size = 3;
		this.width *= size;
		this.height *= size;
//		System.out.println("S:"+size);
//		int quant = rand.nextInt(size*size)-1;
		Food[][] s = new Food[size][size];
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
		s[i][j] = new Food(x+(width*i),y+(width*j),width,height);

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
					s[xs][ys] = new Food(x+(width*xs),y+(height*ys),width,height);
				}
			}
			quant--;
		}
//		printArray(s);
//		System.out.println("W:"+width);
//		texture = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = (Graphics2D) texture.getGraphics();
//		g.setColor(color);
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
//		g.dispose();
	}
	
	public boolean isXYNextTo(int x, int y, Food[][] s)
	{
		boolean rc = false;
		if(x != 0 && s[x-1][y] instanceof Food)
			rc = true;
		else if(x != (s.length-1) && s[x+1][y] instanceof Food)
			rc = true;
		else if(y != 0 && s[x][y-1] instanceof Food)
			rc = true;
		else if(y != (s.length-1) && s[x][y+1] instanceof Food)
			rc = true;
		return rc;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
