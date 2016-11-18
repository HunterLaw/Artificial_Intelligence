package src.world.objects;

import java.awt.Color;

import src.decisionTrees.DecisionTree;

public class Bot extends EnvObjects
{
	double hunger = 100, thirst = 100;
	double hungerRat = 2/5, thirstRat = 3/5;
	double health = (hunger*hungerRat)+(thirst*thirstRat);
	double gThres = .75, yThres = .50, rThres = .25, bThes = .10;
	double strength;
	double survivability;
	int moveSpeed = 2;
	Injury injury = Injury.none;
	Action act = Action.none;
	Color col = Color.green;
	DecisionTree decisions;
	public Bot(int x, int y, int width, int height)
	{
		super(x,y,width,height);
	}
	
	public void update(){}
	
	public Color getColor()
	{
		return col;
	}
}
