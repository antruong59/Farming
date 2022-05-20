package students.items;

public class Soil extends Item
{	
	/* Soil class inherited from Item with set values*/
	public Soil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		this.setName("Soil");
		this.represent = ".";

	}
	
	// Override abstract method toString() of class Item to get the represent
	@Override
	public String toString() {
		return this.represent;
	}
	
}
