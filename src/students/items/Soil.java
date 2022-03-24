package students.items;

public class Soil extends Item
{
	Soil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		this.setName("Soil");
	}
	
	public String getRepresent() {
		return this.getRepresent(".");
	}

}
