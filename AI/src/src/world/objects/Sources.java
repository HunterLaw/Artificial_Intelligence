package src.world.objects;

import java.util.ArrayList;

import src.objects.NonTexturedObject2D;

public abstract class Sources extends NonTexturedObject2D{

	ArrayList<EnvObjects> resources;
	public Sources(double xs, double ys, int width, int height, boolean filleds) {
		super(xs, ys, width, height, filleds);
		resources = new ArrayList<EnvObjects>();
	}

	public ArrayList<EnvObjects> getObjects()
	{
		return resources;
	}
}
