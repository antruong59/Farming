package students.items;

public class Grain extends Food {
	public static int counter = 0;
	
	/* Grain class inherited from Food with set values*/
	public Grain() {
		super(2, 6, 2);
		cost = 1;
		this.setName("Grain");
		counter ++;

	}
	
	// Override abstract method toString() of class Item to get the represent by age
	@Override
	public String toString() {
		if (this.age < this.maturationAge) {
			this.represent = "g";
		} else {
			this.represent = "G";
		}
		return this.represent;
	}
	
	// Count created generation
	public static int getGenerationCount() {
		return counter;
	}

	
}
