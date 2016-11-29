package src.world.objects;

import java.awt.Color;

public class Water extends EnvObjects{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Water(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.cyan;
		System.out.println("X: "+ x + " Y: "+y);
	}

}
