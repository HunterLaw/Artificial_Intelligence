package src.world.objects;

import java.util.ArrayList;

import src.objects.NonTexturedObject2D;

public abstract class Sources extends NonTexturedObject2D{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<EnvObjects> resources;
	ArrayList<EnvObjects> removed;
	int centerx, centery;
	boolean updateResources = false;
	public Sources(int xs, int ys, int width, int height, boolean filleds) {
		super(xs, ys, width, height, filleds);
		resources = new ArrayList<EnvObjects>();
		removed = new ArrayList<EnvObjects>();
		centerx = xs;
		centery = ys;
	}

	public ArrayList<EnvObjects> getObjects()
	{
		return resources;
	}
	
	public ArrayList<EnvObjects> getRemovedObjects()
	{
		updateResources = false;
		return removed;
	}
	
	public abstract void interact();
	
	public boolean isExhausted()
	{
		return (resources.size() == 0);
	}
	
	public boolean needToUpdateResources()
	{
		return updateResources;
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
