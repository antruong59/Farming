package students.items;

public class Apples extends Food {
	
	public Apples() {
		super(3, 5, 3, 2);
		this.setName("Apple");
		counter ++;

	}
	
	public String getRepresent() {
		return this.getRepresent("a");
	}
	
	public int getGenerationCount() {
		return counter;
	}
	
	public static int counter = 0;

	
}
