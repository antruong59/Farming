package students.items;

abstract class Item {
	private String name;
	private double age = 0;
	private double maturationAge;
	private double deathAge;
	private double monetaryValue;
	private double cost;
	private String represent = "";
	
	public Item(double maturationAge, double deathAge, double monetaryValue, double cost) {		
		this.setMaturationAge(maturationAge);
		this.setDeathAge(deathAge);
		this.setMonetaryValue(monetaryValue);
		this.setRepresent(represent);
		this.setAge(age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getMaturationAge() {
		return maturationAge;
	}

	public void setMaturationAge(double maturationAge) {
		this.maturationAge = maturationAge;
	}

	public double getDeathAge() {
		return deathAge;
	}

	public void setDeathAge(double deathAge) {
		this.deathAge = deathAge;
	}

	public double getValue() {
		if (this.age > this.maturationAge) {
			return monetaryValue;
		}
		return 0;
	}

	public void setMonetaryValue(double monetaryValue) {
		this.monetaryValue = monetaryValue;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}
	
	public void tick() {
		this.age ++;
	}
	
	public boolean died() {
		if (this.age > this.deathAge) {
			return true;
		}
		return false;
	}
	
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		
		if (!(object instanceof Item)) {
			return false;
		}
		
		Item compareItem = (Item) object; 
		
		return this.getAge() == compareItem.getAge() && this.getDeathAge() == compareItem.getDeathAge() && this.getMaturationAge() == compareItem.getMaturationAge() && this.getValue() == compareItem.getValue();
	}
	
	
	public String toString() {
		return "";
	};
	
	public static void main(String[] args) {
		Apples corn = new Apples(5, 10, 3, 2);
		Apples tomato = new Apples();
		
		System.out.println("Corn age: " + corn.getAge());
		System.out.println("Compare Tomato and Corn " + corn.equals(tomato));
		System.out.println("Compare Corn and Corn " + corn.equals(corn));
	}
	
	
	
	
}
