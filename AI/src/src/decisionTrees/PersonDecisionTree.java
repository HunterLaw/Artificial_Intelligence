package src.decisionTrees;

import java.util.ArrayList;

import src.world.objects.PersonBot;

public class PersonDecisionTree extends DecisionTree 
{
	PersonBot person;
	ArrayList<Node> nodes = new ArrayList<Node>();
	public PersonDecisionTree(PersonBot p)
	{
		person = p;
		nodes.add(new FoodNode(person));
		nodes.add(new ThirstNode(person));
		nodes.add(new ExploreNode(person));
		
	}
	
	
	public void decide()
	{
		if(person.getHealth() > 75)
		{
			((ExploreNode)findTypeNode(ExploreNode.class)).explore();
		}
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
