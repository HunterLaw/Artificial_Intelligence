package src.world.objects;

import java.awt.Color;

public class Bot extends EnvObjects
{
	double hunger = 100, thirst = 100;
	double hungerRat = 2/5, thirstRat = 3/5;
	double health = (hunger*hungerRat)+(thirst*thirstRat);
	double gThres = .75, yThres = .50, rThres = .25, bThes = .10;
	double strength;
	double survivability;
	Injury injury = Injury.none;
	Action act = Action.none;
	Color col = Color.green;
	public Bot(int x, int y, int width, int height)
	{
		super(x,y,width,height);
	}
	
	
}
