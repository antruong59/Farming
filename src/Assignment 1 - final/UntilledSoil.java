package students.items;

public class UntilledSoil extends Item
{
	/* UntilledSoil class inherited from Item with set values*/
	public UntilledSoil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, -1);
		this.setName("Untilled Soil");
		this.represent = "/";
	}
	
	// Override abstract method toString() of class Item to get the represent
	@Override
	public String toString() {
		return this.represent;
	}

}
