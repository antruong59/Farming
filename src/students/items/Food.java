package students.items;

public abstract class Food extends Item {

	public Food(double maturationAge, double deathAge, double monetaryValue, double Cost) {
		super(maturationAge, deathAge, monetaryValue);
		this.cost = Cost;
	}

}
