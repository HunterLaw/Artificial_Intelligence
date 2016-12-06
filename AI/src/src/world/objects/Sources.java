package src.world.objects;

import java.util.ArrayList;

import src.objects.NonTexturedObject2D;

public abstract class Sources extends NonTexturedObject2D{

	ArrayList<EnvObjects> resources;
	int centerx, centery;
	public Sources(int xs, int ys, int width, int height, boolean filleds) {
		super(xs, ys, width, height, filleds);
		resources = new ArrayList<EnvObjects>();
		centerx = xs;
		centery = ys;
	}

	public ArrayList<EnvObjects> getObjects()
	{
		return resources;
	}
	
	public EnvObjects collisionWithResourceObject(PersonBot p)
	{
		for(EnvObjects e: resources)
		{
			if(e.getRect().intersects(p.getRect()))
			{
				return e;
			}
		}
		return null;
	}
}
