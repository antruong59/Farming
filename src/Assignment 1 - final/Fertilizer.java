package students.items;

import java.util.Dictionary;
import java.util.Hashtable;

public class Fertilizer extends Item
{	
	protected int power;
	protected int costF;
	
	/* Fertilizer class inherited from Food with set values*/

	public Fertilizer(String ferName, int cost, int power) {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		this.setName(ferName);
		this.represent = "*";
		this.power = power;
		this.costF = cost;

	}
	
	// Override abstract method toString() of class Item to get the represent
	@Override
	public String toString() {
		return this.represent;
	}
	
	// Get the power of the fertilizer
	public int getPower() {
		return this.power;
	}
	
	// Get the cost of fertilizer
	public int getCostFertilizer() {
		return (int) this.costF;
	}
	
	public static void main(String[] args) {
		Fertilizer f = new Fertilizer("Double the harvest", 2, 2);
		System.out.println(f.getCostFertilizer());
		System.out.println(f.getName());
		System.out.println(f.getPower());
		
	}
}
