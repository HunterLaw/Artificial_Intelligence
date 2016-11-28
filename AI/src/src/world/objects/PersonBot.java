package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import src.decisionTrees.PersonDecisionTree;
import src.movement.Direction;

public class PersonBot extends Bot {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	Point randPoint = new Point();
	boolean moveToRPoint = false;
	PersonDecisionTree decisions;
	boolean atRPoint = false;
	public PersonBot(int x, int y, int width , int height)
	{
		super(x,y,width,height);
		decisions = new PersonDecisionTree(this);
		System.out.println(health);
	}

	public void move(Direction loor, Direction uord)
	{
		if(loor == Direction.left)
		{
			x -= moveSpeed;
		}
		else if(loor == Direction.right)
		{
			x += moveSpeed;
		}
		if(uord == Direction.up)
		{
			y -= moveSpeed;
		}
		else if(uord == Direction.down)
		{
			y += moveSpeed;
		}
	}
	
	public void moveToPoint()
	{
		if(Math.abs(randPoint.x - x) < moveSpeed)
		{
			x = randPoint.x;
		}
		else if(x < randPoint.x)
		{
			x += moveSpeed;
		}
		else if(x > randPoint.x)
		{
			x -= moveSpeed;
		}
		
		if(Math.abs(randPoint.y - y) < moveSpeed)
		{
			y = randPoint.y;
		}
		else if(y < randPoint.y)
		{
			y += moveSpeed;
		}
		else if(y > randPoint.y)
		{
			y -= moveSpeed;
		}
		
		if(y == randPoint.y && x == randPoint.x)
		{
			atRPoint = true;
			moveToRPoint = false;
		}
		else
		{
			atRPoint = false;
		}
	}
	
	public void moveToARandomPoint()
	{
		if(!moveToRPoint)
		{
			randomPoint();
			moveToRPoint = true;
			atRPoint = false;
		}
	}
	
	public void randomPoint()
	{
		System.out.println("here");
		randPoint.x = rand.nextInt((640*2)-width)+(width/2);
		randPoint.y = rand.nextInt((480*2)-height)+(height/2);
	}
	
	public void setHealthColor()
	{
		if(health >= bThres) color = Color.blue;
		else if(health >= gThres) color = Color.green;
		else if(health >= yThres) color = Color.yellow;
		else if(health >= rThres) color = Color.red;
		else color = Color.black;
		
		Graphics2D g = (Graphics2D) texture.getGraphics();
		g.setColor(color);
		g.fillRect(0,0,width,height);
		g.dispose();
	}
	
	public void updateHealthVars()
	{
		if(health >= bThres)
		{
			hunger -= (hungerDeg/5);
			thirst -= (thirstDeg/5);
		}
		else if(health >= gThres)
		{
			hunger -= (hungerDeg/4);
			thirst -= (thirstDeg/4);
		}
		else if(health >= yThres)
		{
			hunger -= (hungerDeg/2);
			thirst -= (thirstDeg/2);
		}
		else
		{
			hunger -= (hungerDeg);
			thirst -= (thirstDeg);
		}
		health = (hunger*hungerRat)+(thirst*thirstRat);
	}
	
	public void update()
	{
		updateHealthVars();
		setHealthColor();
		System.out.println("Thirst: "+thirst);
		System.out.println("Hunger: "+hunger);
		System.out.println("Health: "+health);
		decisions.decide();
		if(!atRPoint)
		{
			moveToPoint();
		}
		
	}
	
}
