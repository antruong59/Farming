package students.items;

public class Apples extends Food {
	public static int counter = 0;
	
	/* Apples class inherited from Food with set values*/
	public Apples() {
		super(3, 5, 3);
		cost = 2;
		this.setName("Apple");
		counter ++;

	}
	
	// Override abstract method toString() of class Item to get the represent by age
	@Override
	public String toString() {
		if (this.age < this.maturationAge) {
			this.represent = "a";
		} else {
			this.represent = "A";
		}
		return this.represent;
	}
	
	// Count created generation
	public static int getGenerationCount() {
		return counter;
	}
	
}
