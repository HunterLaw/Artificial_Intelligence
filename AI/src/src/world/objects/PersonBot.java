package src.world.objects;

public class PersonBot extends Bot {
	
	
	public PersonBot(int x, int y, int width , int height)
	{
		super(x,y,width,height);
	}

	public void move(int loor, int uord)
	{
		if(loor != 0)
		{
			if(loor == 1)
			{
				x -= moveSpeed;
			}
			else if(loor == 2)
			{
				x += moveSpeed;
			}
		}
		if(uord != 0)
		{
			if(loor == 1)
			{
				y -= moveSpeed;
			}
			else if(loor == 2)
			{
				y += moveSpeed;
			}
		}
	}
	
	public void update()
	{
		move(2,2);
	}
	
}
