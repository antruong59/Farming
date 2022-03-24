package students.items;

public class Weed extends Item
{
	Weed() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, -1);
		this.setName("Weed");
	}
	
	public String getRepresent() {
		return this.getRepresent("#");
	}

}
