package src.world.objects;

import java.awt.Color;

public class WaterSource extends EnvObjects{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666105555535577495L;

	public WaterSource(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.cyan;
	}
	
}
