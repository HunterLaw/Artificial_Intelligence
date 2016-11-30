package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Food extends EnvObjects{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int surround = -1;
	public Food(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.orange;
		texture = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.dispose();
//		System.out.println("X: "+ x + " Y: "+y);
	}

	public void setSurround(int s)
	{
		surround = s;
	}
	
	public int getSurround()
	{
		return surround;
	}
	
}
