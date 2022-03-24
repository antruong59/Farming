package students.items;

public class Apples extends Food {
	private double age = 0;
	private double maturationAge;
	private double deathAge;
	private double monetaryValue;
	private double cost;
	private String represent = "";
	
	public Apples(double maturationAge, double deathAge, double monetaryValue, double cost) {
		super(3, 6, 2, 1);
		counter ++;
		
		if (this.age < this.maturationAge) {
			this.represent = "g";
		} else {
			this.represent = "G";
		}

	}
	public int getGenerationCount() {
		return counter;
	}
	
	public static int counter = 0;

	
}
