package src.world.objects;

import java.awt.Rectangle;

public class EnvObjects 
{
	Rectangle rect;
	int x =0, y=0;
	int width =0, height =0;
	public EnvObjects(int x, int y, int width ,int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(width,height);
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
}
