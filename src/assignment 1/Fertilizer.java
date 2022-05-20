package students.items;

import java.util.Dictionary;
import java.util.Hashtable;

public class Fertilizer extends Item
{	
	protected String fertilizerName;
	protected int power;

	public Fertilizer(String ferName, int cost, int power) {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, cost);
		this.setName("Fertilizer");
		this.represent = "*";
		this.fertilizerName = ferName;
		this.power = power;

	}
	
	@Override
	public String toString() {
		return this.represent;
	}
	
	public static void main(String[] args) {
		Fertilizer f = new Fertilizer("Double the harvest", 2, 2);
		System.out.println(f.getCost());
		
	}
}
