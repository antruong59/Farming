package students.items;

abstract class Food extends Item {

	Food(double maturationAge, double deathAge, double monetaryValue, double cost) {
		super(maturationAge, deathAge, monetaryValue);
		this.cost = cost;
	}

}
