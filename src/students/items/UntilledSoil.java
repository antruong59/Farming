package students.items;

public class UntilledSoil extends Item
{
	public UntilledSoil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, -1);
		this.setName("UntilledSoil");
		this.represent = "/";
	}
	
	@Override
	public String toString() {
		return this.represent;
	}

}
