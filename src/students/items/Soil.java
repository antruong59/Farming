package students.items;

public class Soil extends Item
{	
	public Soil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		this.setName("Soil");
		this.represent = ".";

	}
	
	@Override
	public String toString() {
		return this.represent;
	}
	
}
