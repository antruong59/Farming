package students.items;

public abstract class Food extends Item {

	/* Abstract class Food that pass maturation age, death age and monetary value inherited from Item*/
	public Food(double maturationAge, double deathAge, double monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
		cost = 0;
	}

}
