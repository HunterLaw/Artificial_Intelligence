package src.world.objects;

import src.objects.TexturedObject2D;

public class EnvObjects extends TexturedObject2D
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnvObjects(int x, int y, int width ,int height)
	{
		super((double)x,(double)y,width,height,true);
	}

	@Override
	public void update() {
		
	}
}
