package students.items;

public abstract class Food extends Item {

	public Food(double maturationAge, double deathAge, double monetaryValue, double cost) {
		super(maturationAge, deathAge, monetaryValue);
		this.cost = cost;
	}

}
