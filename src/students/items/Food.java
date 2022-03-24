package students.items;

abstract class Food extends Item {
	private String name;
	private double age = 0;
	private double maturationAge;
	private double deathAge;
	private double monetaryValue;
	private double cost;
	private String represent = "";
	
	public Food(double maturationAge, double deathAge, double monetaryValue, double cost) {
		super(maturationAge, deathAge, monetaryValue, cost);
		this.setRepresent(represent);
		this.setAge(age);
	}

}
