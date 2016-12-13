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
	ArrayList<Sources> waterSources = new ArrayList<Sources>();
	ArrayList<Sources> foodSources = new ArrayList<Sources>();

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
//		if(!s.isExhausted())
		if(!locations.contains(s))
		{
			if((s instanceof WaterSource && !waterSources.contains((WaterSource)s)) ||
			    (s instanceof FoodSource  && !foodSources.contains((FoodSource)s))      )
			{
				locations.add(s);
				System.out.println("Added location to memory: "+s);
				System.out.println("WaterSources: "+waterSources);
				System.out.println("FoodSources: "+foodSources);
				System.out.println("Locations: "+locations);
				return true;
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
			
			if(!interacted)
			{
				Sources s;
				if(locations.size() == 0)
				{
					if(foodSources.size() >0  && waterSources.size()> 0)
					{
						if(person.getHunger() > person.getThirst())
						{
							s = findClosestSource(foodSources);
						}
						else
						{
							s = findClosestSource(waterSources);
						}
					}
					else if(foodSources.size() > 0)
					{
						s = findClosestSource(foodSources);
					}
					else
					{
						s = findClosestSource(waterSources);
					}
				}
				else
				{
					s = findClosestSource(locations);
				}
				if(person.atGoalPoint())
				{
					System.out.println("At Point");
					person.moveToObject(null);
					if(s instanceof WaterSource)
					{
						if(!waterSources.contains((WaterSource)s))
						{
							waterSources.add((WaterSource)s);
							locations.remove(s);
						}
						while(!s.isExhausted())
						{
							s.interact();
							System.out.println("interacting");
							if(person.addThirst(10))
							{
								interacted = true;
								break;
							}
							
						}
					}
					else if(s instanceof FoodSource)
					{	
						if(!foodSources.contains((FoodSource)s))
						{
							foodSources.add((FoodSource)s);
							locations.remove(s);
						}
						while(!s.isExhausted())
						{
							s.interact();
							System.out.println("interacting");
							if(person.addHunger(15))
							{
								interacted = true;
								break;
							}
						}

					}
					System.out.println("Water sources: "+waterSources);
					System.out.println("FoodSources: "+foodSources);
					System.out.println("Locations: "+locations);
				}
				else
				{
					person.moveToObject(s);
				}
			}
		}
	}
	
	public Sources findClosestSource(ArrayList<Sources> locations)
	{
		ArrayList<Object> min = new ArrayList<Object>(2);
		if(locations.size() > 0)
		{
			for(Sources s: locations)
			{
				if(s.isExhausted())continue;
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
			if(min.isEmpty()) return null;
			return (Sources)min.get(1);
		}
		return null;
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
	
	public ArrayList<Sources> getLocations(){return locations;}
	public ArrayList<Sources> getWaterLocations(){return waterSources;}
	public ArrayList<Sources> getFoodLocations(){return foodSources;}
	
}
