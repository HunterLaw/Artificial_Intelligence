package src.world.objects;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

import src.movement.Direction;

public class FoodSource extends Sources
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Random rand = new Random();
	PriorityQueue<Food> pq = new PriorityQueue<Food>(new Food());
	public FoodSource(int x, int y, int width, int height) {
		super(x, y, width, height,true);
//		System.out.println(this);
		color = Color.orange;
		int size = 2+rand.nextInt(3);
//		int size = 3;
		this.width *= size;
		this.height *= size;
//		System.out.println("S:"+size);
		int quant = rand.nextInt((size*size)-1);
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
		s[i][j] = new Food(i,j,width,height,size);
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
				if(((Food)e).surround != 0)
					pq.add((Food)e);
			}
//			printArray(s);
//			Water w = hasOneInPQ(pq);
			Point p;
//			if(w == null)
			p = pq.peek().getRandSurroundPoint();
//			else
//				p = w.getRandSurroundPoint();
//			printPriorityQueue(pq);
			s[p.x][p.y] = new Food(p.x,p.y,width,height,size);
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
		for(EnvObjects f: resources)
		{
			Food f2 = (Food)f;
			f2.setX(x+(f2.getX()*width));
			f2.setY(y+(f2.getY()*height));
		}
		width *= size;
		height *= size;
//		System.out.println(water.size());
//		g.dispose();
	}
	
	public void insertResources(Food food, Food[][] f)
	{
		int x = food.getX(),y = food.getY();
		if(x != 0)
		{
			//Left
			if(f[x-1][y] == null)
			{
				food.addSurround(Direction.left);
			}
			else
			{
				f[x-1][y].removeSurround(Direction.right);
			}
		}
		if(x != f.length-1)
		{
			//Right
			if(f[x+1][y] == null)
			{
				food.addSurround(Direction.right);
			}
			else
			{
				f[x+1][y].removeSurround(Direction.left);
			}
		}
		if(y != 0)
		{
			//Up
			if(f[x][y-1] == null)
			{
				food.addSurround(Direction.up);
			}
			else
			{
				f[x][y-1].removeSurround(Direction.down);
			}
		}
		if(y != f.length-1)
		{
			//Down
			if(f[x][y+1] == null)
			{
				food.addSurround(Direction.down);
			}
			else
			{
				f[x][y+1].removeSurround(Direction.up);
			}
		}
		resources.add(food);
	}
	
	public void interact()
	{
		System.out.println("here");
		int max = 0,surround =0;
		ArrayList<Food> maxs = new ArrayList<Food>();
		for(EnvObjects e: resources)
		{
			surround = ((Water)e).surround;
			if(surround > max)
			{
				max = surround;
				maxs.clear();
				maxs.add((Food)e);
			}
			else if(surround == max)
			{
				maxs.add((Food)e);
			}
		}
		removed.add(maxs.get(rand.nextInt(maxs.size())));
		resources.removeAll(removed);
		System.out.println(removed.toString());
		updateResources = true;
	}
	
	public int countFood(Food[][] f)
	{
		int sum =0;
		for(int x =0;x < f.length;x++)
		{
			for(int y =0;y < f[0].length;y++)
			{
				if(f[x][y] != null)
				{
					sum++;
				}
				
			}
		}
		return sum;
	}
	
	public Food hasOneInPQ(PriorityQueue<Food> p)
	{
		Food rc = null;
		Iterator<Food> it = p.iterator();
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

	public void printArray(Food[][] f)
	{
		for(int x =0;x < f.length;x++)
		{
			for(int y =0;y < f[0].length;y++)
			{
				if(f[y][x] != null)
				{
					System.out.print(" "+f[y][x].surround);
				}
				else
				{
					System.out.print("-1");
				}
				if(y+1 != f[0].length)
				{
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
	
	public void printPriorityQueue(PriorityQueue<Food> p)
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