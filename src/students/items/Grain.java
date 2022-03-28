package students.items;

public class Grain extends Food {
	public static int counter = 0;
	
	public Grain() {
		super(2, 6, 2, 1);
		this.setName("Grain");
		counter ++;

	}
	
	@Override
	public String toString() {
		if (this.age < this.maturationAge) {
			this.represent = "g";
		} else {
			this.represent = "G";
		}
		return this.represent;
	}
	
	public static int getGenerationCount() {
		return counter;
	}

	
}
