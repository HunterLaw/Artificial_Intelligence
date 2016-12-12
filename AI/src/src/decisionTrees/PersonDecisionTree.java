package src.decisionTrees;

import java.util.ArrayList;

import src.world.objects.FoodSource;
import src.world.objects.PersonBot;
import src.world.objects.Sources;
import src.world.objects.WaterSource;

public class PersonDecisionTree extends DecisionTree 
{
	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Sources> locations = new ArrayList<Sources>();
	ArrayList<WaterSource> waterSources = new ArrayList<WaterSource>();
	ArrayList<FoodSource> foodSources = new ArrayList<FoodSource>();

	boolean interacted = false;
	public PersonDecisionTree(PersonBot p)
	{
		person = p;
		nodes.add(new FoodNode(person));
		nodes.add(new ThirstNode(person));
		nodes.add(new ExploreNode(person));
		
	}
	
	
	public boolean addLocation(Sources s)
	{
		if(!locations.contains(s))
		{
			if( (s instanceof WaterSource && !waterSources.contains((WaterSource)s)) &&
			    (s instanceof FoodSource  && !foodSources.contains((FoodSource)s))      );
			{
				System.out.println("Added location to memory");
				return locations.add(s);
			}
		}
		return false;
	}
	
	public void decide()
	{
		if(person.getHealth() > 75)
		{
			((ExploreNode)findTypeNode(ExploreNode.class)).explore();
			interacted = false;
		}
		else
		{
			if(locations.size() > 0 || !interacted)
			{
				Sources s = findClosestSource(locations);
				if(person.atGoalPoint() && !interacted)
				{
					System.out.println("here");
					person.moveToObject(null);
					if(s instanceof WaterSource)
					{
						waterSources.add((WaterSource)s);
						locations.remove(s);
						while(!s.isExhausted())
						{
							s.interact();
							if(person.addThirst(10))
							{
								interacted = true;
								System.out.println(person.getThirst());
								break;
							}
							
						}
					}
					else if(s instanceof FoodSource)
					{
						foodSources.add((FoodSource)s);
						locations.remove(s);
						if(!person.addHunger(15))
						{
							interacted = true;
						}
					}
				}
				else
				{
					person.moveToObject(s);
				}
			}
			else
			{
				
			}
		}
	}
	
	public Sources findClosestSource(ArrayList<Sources> locations)
	{
		ArrayList<Object> min = new ArrayList<Object>(2);
		for(Sources s: locations)
		{
			int dx = Math.abs(person.getMidX()-s.getMidX());
			int dy = Math.abs(person.getMidY()-s.getMidY());
			double d = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
			if(min.size() == 0)
			{
				min.add(0, d);
				min.add(1, s);
			}
			else if(d < ((double)min.get(0)))
			{
				min.set(0, d);
				min.set(1, s);
			}
		}
		return (Sources)min.get(1);
	}
	
	public Node findTypeNode(Class<?> n)
	{
		for(Node node:nodes)
		{
			if(node.getClass() == n)
			{
				return node;
			}
		}
		return null;
	}
	
}
