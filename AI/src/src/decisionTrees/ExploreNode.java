package src.decisionTrees;

import java.util.ArrayList;

import src.world.objects.PersonBot;

public class ExploreNode extends Node
{
	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	public ExploreNode(PersonBot p)
	{
		id = "Explore";
		person = p;
	}
	
	public void explore()
	{
		person.moveToARandomPoint();
	}
	
	
	@Override
	public void setId(String id)
	{
		System.err.println("You cannot change the id of the explore node");
	}
}
