package src.decisionTrees;

import java.util.ArrayList;
import java.util.HashMap;

import src.world.objects.PersonBot;
import src.world.objects.Sources;

public class PersonDecisionTree extends DecisionTree 
{
	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Sources> locations = new ArrayList<Sources>();
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
			return locations.add(s);
		}
		return false;
	}
	
	public void decide()
	{
		if(person.getHealth() > 75)
		{
			((ExploreNode)findTypeNode(ExploreNode.class)).explore();
		}
		else
		{
			
		}
	}
	
	public Sources findClosestSource()
	{
		ArrayList<Object> min = new ArrayList<Object>();
		for(Sources s: locations)
		{
			int dx = Math.abs(person.getMidX()-s.getMidX());
			int dy = Math.abs(person.getMidY()-s.getMidY());
			double d = Math.pow(dx, 2)+Math.pow(dy, 2);
			if(min.size() == 0)
			{
				min.set(0, d);
				min.set(1, s);
			}
			else if(d < ((int)min.get(0)))
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
