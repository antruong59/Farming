package students.items;

public class Apples extends Food {
	
	public Apples() {
		super(3, 5, 3, 2);
		this.setName("Apple");
		counter ++;

	}
	
	@Override
	public String toString() {
		if (this.age < this.maturationAge) {
			this.represent = "a";
		} else {
			this.represent = "A";
		}
		return this.represent;
	}
	
	public static int getGenerationCount() {
		return counter;
	}
	
	public static int counter = 0;

	
}
