package src.world.objects;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.UI.Window;

public class Stats
{
	static Window stats;
	static JPanel statsp;
	static JLabel health,hunger,thirst,hungDeg,thirDeg;
	
	static Bot bot;
	
	public Stats(Bot object)
	{
		bot = object;
		statsp = new JPanel();
		statsp.setPreferredSize(new Dimension(300,150));
		statsp.setLayout(null);
		
		
		health = new JLabel("Health: "+ bot.getHealth());
		health.setBounds(5, 5, 95, 20);
		
		hunger = new JLabel("Hunger: " + bot.getHunger());
		hunger.setBounds(5, 30, 95, 20);
		
		thirst = new JLabel("Thirst: " + bot.getThirst());
		thirst.setBounds(5, 55, 95, 20);
		
		hungDeg = new JLabel("Hung Deg: " + bot.hungerDeg);
		hungDeg.setBounds(5, 80, 95, 20);
		
		thirDeg = new JLabel("Thir Deg: " + bot.thirstDeg);
		thirDeg.setBounds(5, 105, 95, 20);
		
		statsp.add(health);
		statsp.add(hunger);
		statsp.add(thirst);
		statsp.add(hungDeg);
		statsp.add(thirDeg);

		stats = new Window(statsp,object.getClass().getSimpleName()+" Stats");

	}
	
	public void update()
	{
		health.setText("Health: "+ bot.getHealth());
		hunger.setText("Hunger: "+ bot.getHunger());
		thirst.setText("Thirst: "+ bot.getThirst());
		hungDeg.setText("Hung Deg: "+ bot.hungerDeg);
		thirDeg.setText("Thir Deg: "+ bot.thirstDeg);

	}
}
