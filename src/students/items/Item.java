package students.items;

abstract class Item {
	private String name;
	protected double age = 0;
	protected double maturationAge;
	protected double deathAge;
	protected double monetaryValue;
	protected double cost;
	protected String represent;
	
	Item(double maturationAge, double deathAge, double monetaryValue, double cost) {		
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
		this.cost = cost;
		
	}

	public String getName() {
		return this.name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}

	public double getAge() {
		return this.age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getMaturationAge() {
		return this.maturationAge;
	}

	public double getDeathAge() {
		return this.deathAge;
	}

	public double getValue() {
		if (this.age > this.maturationAge) {
			return this.monetaryValue;
		}
		return 0;
	}

	public double getCost() {
		return this.cost;
	}

	public String getRepresent(String represent) {
		if (this.age < this.maturationAge) {
			this.represent = represent;
		} else {
			this.represent = represent.toUpperCase();
		}
		return this.represent;
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
			System.out.println("not an instance of Item");
			return false;
		}
		
		Item compareItem = (Item) object; 
		
		System.out.println("Inside equals!");
		return this.getAge() == compareItem.getAge() 
				&& this.getDeathAge() == compareItem.getDeathAge() 
				&& this.getMaturationAge() == compareItem.getMaturationAge() 
				&& this.getValue() == compareItem.getValue();
	}
	
	
	public String toString() {
		return "";
	};
	
	public static void main(String[] args) {
		Apples corn = new Apples();
		Apples corn1 = new Apples();
		Apples corn2 = new Apples();
		Apples corn3 = new Apples();
		Apples corn4 = new Apples();
		
		System.out.println("Compare Corn and Corn1 " + corn.equals(corn1));
		System.out.println("Corn name: " + corn.getName());
		System.out.println("Corn age: " + corn.getAge());
		System.out.println("Corn represent: " + corn.getRepresent());
		corn.setAge(3);
		System.out.println("Corn age: " + corn.getAge());
		System.out.println("Corn Maturation age: " + corn.getMaturationAge());
		System.out.println("Corn represent: " + corn.getRepresent());
		System.out.println("Corn gen count: " + corn.getGenerationCount());
		
	}
	
	
	
	
}
