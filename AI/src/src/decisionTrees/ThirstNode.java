package src.decisionTrees;

import java.util.ArrayList;

import src.world.objects.PersonBot;

public class ThirstNode extends Node
{

	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	public ThirstNode(PersonBot p)
	{
		id = "Thirst";
		person = p;
	}
	
	@Override
	public void setId(String id)
	{
		System.err.println("You cannot change the id of the thirst node");
	}
}
