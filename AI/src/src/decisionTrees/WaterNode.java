package src.decisionTrees;

import java.util.ArrayList;

import src.world.objects.PersonBot;

public class WaterNode extends Node
{
	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	public WaterNode(PersonBot p)
	{
		id = "Water";
		person = p;
	}
	
	@Override
	public void setId(String id)
	{
		System.err.println("You cannot change the id of the water node");
	}
	
}
