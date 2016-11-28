package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import src.decisionTrees.DecisionTree;

public class Bot extends EnvObjects
{
	double hunger = 100, thirst = 100,hungerDeg =0.025, thirstDeg = 0.05;
	double hungerRat = (double)2/5, thirstRat = (double)3/5;
	double health = (hunger*hungerRat)+(thirst*thirstRat);
	double bThres = 75, gThres = 50, yThres = 25, rThres = 10;
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
		texture = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)texture.getGraphics();
		g.setColor(col);
		g.fillRect(0, 0, width, height);
		g.dispose();
	}
	
	public void update(){}
	
	public Color getColor()
	{
		return col;
	}
	
	
	
	public double getThirst()
	{
		return thirst;
	}
	
	public double getHunger()
	{
		return hunger;
	}
	
	public double getHealth()
	{
		return health;
	}
}
