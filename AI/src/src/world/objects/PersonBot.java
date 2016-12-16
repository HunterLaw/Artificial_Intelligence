package src.world.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import src.decisionTrees.PersonDecisionTree;
import src.movement.Direction;
import src.objects.NonTexturedObject2D;
import src.world.World;

public class PersonBot extends Bot {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	Point randPoint = new Point();
	Point goalPoint = null;
	boolean moveToRPoint = false;
	PersonDecisionTree decisions;
	boolean atPoint = false;
	boolean swimming = false;
	boolean moving = false;
	Thread collisionCheck;
	Collision collision;
	public PersonBot(int x, int y, int width , int height)
	{
		super(x,y,width,height);
		hunger = 76;
		thirst = 76;
//		thirstDeg *= 5;
		hungerDeg *= 5;
		decisions = new PersonDecisionTree(this);
		collision = new Collision(this);
		collisionCheck = new Thread(collision);
//		System.out.println(health);
	}

	public void move(Direction loor, Direction uord)
	{
		moving = true;
		if(loor == Direction.left)
		{
			if(!swimming)
				x -= moveSpeed;
			else
				x -= moveSpeed/2;
		}
		else if(loor == Direction.right)
		{
			if(!swimming)
				x += moveSpeed;
			else
				x += moveSpeed/2;
		}
		if(uord == Direction.up)
		{
			if(!swimming)
				y -= moveSpeed;
			else
				y -= moveSpeed/2;
		}
		else if(uord == Direction.down)
		{
			if(!swimming)
				y += moveSpeed;
			else
				y += moveSpeed/2;
		}
	}
	
	public void moveToObject(NonTexturedObject2D e)
	{
		if(e != null)
			goalPoint = new Point(e.getMidX(),e.getMidY());
		else
		{
			goalPoint = null;
			atPoint = false;
		}
	}
	
	public void moveToGoalPoint(int moveSpeed)
	{
		moveToPoint(goalPoint, moveSpeed);
	}
	
	public void moveToRandomPoint(int moveSpeed)
	{
		moveToPoint(randPoint,moveSpeed);
	}
	
	public void moveToPoint(Point p, int moveSpeed)
	{
		if(Math.abs(p.x - x) < moveSpeed)
		{
			x = p.x;
		}
		else if(x < p.x)
		{
			x += moveSpeed;
		}
		else if(x > p.x)
		{
			x -= moveSpeed;
		}
		
		if(Math.abs(p.y - y) < moveSpeed)
		{
			y = p.y;
		}
		else if(y < p.y)
		{
			y += moveSpeed;
		}
		else if(y > p.y)
		{
			y -= moveSpeed;
		}
		
		if(y == p.y && x == p.x)
		{
			atPoint = true;
			moveToRPoint = false;
		}
		else
		{
			atPoint = false;
		}
	}
	
	public boolean moveToRandomPointAroundSource(Sources s, int radius)
	{
		if(goalPoint == null)
		{
			goalPoint = new Point();
			goalPoint.x = rand.nextInt(radius*2)-radius+s.getMidX();
			goalPoint.y = rand.nextInt(radius*2)-radius+s.getMidY();
			moveToRPoint = true;
			atPoint = false;
			return false;
		}
		else if(atPoint)
		{
			goalPoint = null;
			atPoint = false;
			return true;
		}
		return false;
	}
	
	public void moveToARandomPoint()
	{
		if(!moveToRPoint)
		{
			randomPoint();
			moveToRPoint = true;
			atPoint = false;
		}
	}
	
	public void setPoint(Point p)
	{
		goalPoint = p;
	}
	
	public void randomPoint()
	{
//		System.out.println("here");
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
		double hungAdditive = 0, thirAdditive = 0;
		if(swimming)
		{
			hungAdditive = (hungerDeg/2);
			thirAdditive = (thirstDeg/2);
		}
		else if(moving)
		{
			hungAdditive = (hungerDeg/3);
			thirAdditive = (thirstDeg/3);
		}
			
		if(health >= bThres)
		{
			addHunger(-((hungerDeg/5)+hungAdditive));
			addThirst(-((thirstDeg/5)+thirAdditive));
		}
		else if(health >= gThres)
		{
			addHunger(-((hungerDeg/4)+hungAdditive));
			addThirst(-((thirstDeg/4)+thirAdditive));
		}
		else if(health >= yThres)
		{
			addHunger(-((hungerDeg/2)+hungAdditive));
			addThirst(-((thirstDeg/2)+thirAdditive));
		}
		else
		{
			addHunger(-((hungerDeg)+hungAdditive));
			addThirst(-((thirstDeg)+thirAdditive));
		}
		if(health < 0) health = 0;
		if(thirst < 0) thirst = 0;
		health = (hunger*hungerRat)+(thirst*thirstRat);
	}
	
	public void update()
	{
		if(health > 0)
		{
			if(!collisionCheck.isAlive())
			{
				collisionCheck.start();
			}
			updateHealthVars();
			setHealthColor();
	//		System.out.println("Thirst: "+thirst);
	//		System.out.println("Hunger: "+hunger);
	//		System.out.println("Health: "+health);
			decisions.decide();
			if(!atPoint)
			{
				if(goalPoint == null)
				{
					if(!swimming)
					{
						moveToRandomPoint(moveSpeed);
					}
					else
					{
						moveToRandomPoint(moveSpeed/2);
					}
				}
				else
				{
					if(!swimming)
					{
						moveToGoalPoint(moveSpeed);
					}
					else
					{
						moveToGoalPoint(moveSpeed/2);
					}
				}
			}
			else
			{
				moving = false;
			}
		}
//		if(goalPoint != null && atPoint)
//		{
//			goalPoint = null;
//			atPoint = false;
//		}
	}
	
	
	
	public void setSwimming(boolean swim)
	{
		swimming = swim;
	} 
	
	public ArrayList<Sources> getLocations()
	{
		return decisions.getLocations();
	}
	public ArrayList<Sources> getWaterLocations()
	{
		return decisions.getWaterLocations();
	}
	public ArrayList<Sources> getFoodLocations()
	{
		return decisions.getFoodLocations();
	}
	
	
	public boolean getSwimming() {return swimming;}
	
	public boolean atGoalPoint()
	{
		return (goalPoint != null && atPoint);

	}
	
	public static class Collision implements Runnable
	{
		static PersonBot me;
		static Sources collided;
		static EnvObjects e;
		long num = 0;
		double avg =0;
		long start =0;
		double elapsed =0;
		public Collision(PersonBot p)
		{
			me = p;
		}
		public Sources getCollidedSources()
		{
			Sources temp = collided;
			collided = null;
			return temp;
		}
		public void run()
		{
			while(!Thread.interrupted())
			{
				start = System.nanoTime();
				collided = World.collisionWithSources(me);
				if(collided != null)
				{
					synchronized(me){me.decisions.addLocation(collided);}
//					System.out.println("collision");
					e = collided.collisionWithResourceObject(me);
					if(e instanceof Water)
					{
						synchronized(me){
							me.setSwimming(true);
						}
					}
					else if(me.getSwimming())
					{
						synchronized(me){me.setSwimming(false);}
					}
				}
				else
				{
					if(me.getSwimming())
					{
						synchronized(me){me.setSwimming(false);}
					}
				}
				elapsed = ((double)(System.nanoTime()-start)/1000);
//				System.out.println("Time to complete: "+elapsed);
				num += 1;
				avg += elapsed;
//				System.out.println("Average time to complete: "+(double)(avg/num));
				try{
					Thread.sleep(1000/60);
				} catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("im done");
		}
	}
	
}
