package students.items;

public class Soil extends Item
{
//	public static int counter = 0;
	
	public Soil() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
		this.setName("Soil");
		this.represent = ".";
//		counter++;
	}
	
	@Override
	public String toString() {
		return this.represent;
	}
	
//	public static int getGenerationCount() {
//		return counter;
//	}

}
