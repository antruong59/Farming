package students.items;

public class Weed extends Item
{
	public Weed() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, -1);
		this.setName("Weed");
		this.represent = "#";
	}
	
	@Override
	public String toString() {
		return this.represent;
	}

}
