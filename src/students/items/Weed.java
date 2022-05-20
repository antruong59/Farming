package students.items;

public class Weed extends Item
{
	/* Weed class inherited from Item with set values*/
	public Weed() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, -1);
		this.setName("Weed");
		this.represent = "#";
	}
	
	// Override abstract method toString() of class Item to get the represent
	@Override
	public String toString() {
		return this.represent;
	}

}
