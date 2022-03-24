package students.items;

public class Grain extends Food {
	
	public Grain() {
		super(2, 6, 2, 1);
		this.setName("Grain");
		counter ++;

	}
	
	public String getRepresent() {
		return this.getRepresent("g");
	}
	public int getGenerationCount() {
		return counter;
	}
	
	public static int counter = 0;

	
}
