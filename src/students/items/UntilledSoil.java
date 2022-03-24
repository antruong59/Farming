package students.items;

public class UntilledSoil extends Item
{
	UntilledSoil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, -1);
		this.setName("UntilledSoil");
	}
	
	public String getRepresent() {
		return this.getRepresent("/");
	}

}
