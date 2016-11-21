package src.decisionTrees;

import java.util.ArrayList;

import src.world.objects.PersonBot;

public class FoodNode extends Node
{
	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	public FoodNode(PersonBot p)
	{
		id = "Food";
		person = p;
	}
	
	@Override
	public void setId(String id)
	{
		System.err.println("You cannot change the id of the food node");
	}
}
