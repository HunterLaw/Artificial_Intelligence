package src.decisionTrees;

import java.util.ArrayList;

public class Node 
{
	String id;
	ArrayList<Node> nodes = new ArrayList<Node>();
	public Node()
	{
		
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
}
