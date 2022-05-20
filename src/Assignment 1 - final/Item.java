package students.items;

public abstract class Item {
	
	/* Item class is an abstract base class that declares all the attributes of every item and is inherited by classes: Food, Soil, UntilledSoil and Fertilizer*/
	private String name;
	protected double age = 0;
	protected double maturationAge;
	protected double deathAge;
	protected double monetaryValue;
	protected static double cost;
	protected String represent;
	
	public Item(double maturationAge, double deathAge, double monetaryValue) {		
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
		
	}
	
	// Increase age of item by 1
	public void tick() {
		this.age ++;
	}
	
	// Set age of item
	public void setAge(int age) {
		this.age = age;
	}
	
	// Check if item died
	public boolean died() {
		if (this.age > this.deathAge) {
			return true;
		}
		return false;
	}
	
	// Get the monetary value of item
	public int getValue() {
		
		// Return monetary value if the item age over maturation age
		if (this.age >= this.maturationAge || this.maturationAge == Double.POSITIVE_INFINITY) {
			return (int) this.monetaryValue;
		}
		
		// Else return 0
		return 0;
	}
	
	// Compare between 2 objects, return true if they shared the same age, death age, maturation age and value
	public boolean equals(Object object) {
		
		// If they are the same object
		if (object == this) {
			return true;
		}
		
		// If the object is not an item
		if (!(object instanceof Item)) {
			System.out.println("not an instance of Item");
			return false;
		}
		
		Item compareItem = (Item) object; 
		

		return this.getAge() == compareItem.getAge() 
				&& this.getDeathAge() == compareItem.getDeathAge() 
				&& this.getMaturationAge() == compareItem.getMaturationAge() 
				&& this.getValue() == compareItem.getValue();
	}
	
	// Abstract method toString()
	public abstract String toString();

	// Get name of item
	public String getName() {
		return this.name;
	}
	
	// Set name of item
	protected void setName(String Name) {
		this.name = Name;
	}

	// Get age of item
	public double getAge() {
		return this.age;
	}	

	// Get maturation age
	public double getMaturationAge() {
		return this.maturationAge;
	}

	// Get death age
	public double getDeathAge() {
		return this.deathAge;
	}

	// Set monetary value
	public void setValue(int value) {
		this.monetaryValue = value;
	}

	// Get cost of item
	public static int getCost() {
		return (int) cost;
	}
	
	public static void main(String[] args) {
		Apples corn = new Apples();
		Apples corn1 = new Apples();
		Apples corn2 = new Apples();
		Apples corn3 = new Apples();
		Apples corn4 = new Apples();
		Weed w = new Weed();
		Weed w1 = new Weed();
		Weed w2 = new Weed();
		//Food f = new Food();
		
		System.out.println("Compare Corn and Corn1 " + corn.equals(corn1));
		System.out.println("Compare Corn and Weed " + corn.equals(w));
		System.out.println("Compare Weed and Weed 1 " + w.equals(w1));
		System.out.println("Corn name: " + corn.getName());
		System.out.println("Corn age: " + corn.getAge());
		System.out.println("Corn represent: " + corn.toString());
		corn.setAge(2);
		System.out.println("Val 1 "+corn.getValue());
		corn.setValue(5);
		System.out.println("Val 2 "+corn.getValue());
		System.out.println("Corn age: " + corn.getAge());
		System.out.println("Corn represent: " + corn.toString());
		corn.tick();
		System.out.println("Corn age: " + corn.getAge());
		System.out.println("Corn Maturation age: " + corn.getMaturationAge());
		System.out.println("Corn represent: " + corn.toString());
		System.out.println("Corn gen count: " + Apples.getGenerationCount());
		System.out.println("Weed represent: " + w.toString());
		System.out.println("Weed m_age: " + w.getMaturationAge());
	}
	
	
	
	
}
