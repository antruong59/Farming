package students;

import students.items.Apples;
import students.items.Food;
import students.items.Item;
import students.items.Soil;
import students.items.UntilledSoil;
import students.items.Weed;

import java.lang.Math;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class Field {
	protected int height;
	protected int width;
	protected Item[][] field;
	
	public Field(int height, int width)
	{
		this.height = height;
		this.width = width;
		
		this.field = new Item[this.height][this.width]; 
		for (int row = 0; row < this.height; row++) {
	        for (int col = 0; col < this.width; col++) {
	        	Soil soil = new Soil();
	        	field[row][col] = soil;
	        }
		}
//		System.out.println("Soil numb: " + Soil.getGenerationCount());

	}
	
	public void getField() {
		for (int col = 0; col <= this.width; col++) {
			if (col == 0) {
				System.out.print("     ");
			} else {
			System.out.print(col + "    ");
			}	
		}
		System.out.println("\n");
		
		for (int row = 0; row < this.height; row++) {
			System.out.print((row + 1) + "    ");
	        for (int col = 0; col < this.width; col++) {
	        	
	        	System.out.print(this.field[row][col].toString() + "    ");
	        }
	        System.out.println("\n");
		}
	}
	
	public void tick() {
		for (int row = 0; row < this.height; row++) {
	        for (int col = 0; col < this.width; col++) {
	        	this.field[row][col].tick();
	        	int randNum = new Random().nextInt(5);
	        	//System.out.println("Rand Num: " + randNum);
	        	
	        	boolean turnToWeed = randNum == 0;
	        	
	        	if (this.field[row][col] instanceof Soil && turnToWeed) {
	        		Weed weed = new Weed();
	        		this.field[row][col] = weed;	
	        	}
	        	
	        	if (this.field[row][col].died()) {
	        		UntilledSoil untilledsoil = new UntilledSoil();
	        		this.field[row][col] = untilledsoil;
	        	}
	        }
		}
	}
	
//	public String toString() {
//		
//	}
//	
	public void till(int x, int y) {
		Soil soil = new Soil();
		this.field[y][x] = soil;
	}
	
	public String get(int x, int y) {
		return this.field[y][x].toString();
	}
	
	public void plant(int x, int y, Food item) {
		if (this.field[y - 1][x - 1] instanceof Soil) {
			this.field[y - 1][x - 1] = item;
		} else {
			System.out.printf("You can only plant %s in soil", item.toString());
		}
		
	}
	
//	public Hashtable getValue() {
//		Hashtable itemValue = new Hashtable();
//		
//		for (int row = 0; row < this.height; row++) {
//	        for (int col = 0; col < this.width; col++) {
//	        	boolean isKeyPresent = ((Hashtable) itemValue).containsKey(this.field[row][col].toString());
//	        	if (isKeyPresent) {
//	        		itemValue.put(this.field[row][col].toString(), 0);
//	        	} else {
//	        		itemValue.put(this.field[row][col].toString(), 
//			        ((Hashtable) itemValue).get(this.field[row][col].toString()) + this.field[row][col].getValue());
//	        	}
//	        }
//		}
//		
//		return itemValue;
//		
//	}
	
	public void getSummary() {
		
	}
	
	public static void main(String[] args) {
		Field field = new Field(5, 10);
		System.out.println("Field height " + field.height);
		System.out.println("Field height " + field.width);
		field.getField();
		field.tick();
		Apples a = new Apples();
		field.plant(1, 3, a);
		field.getField();
		field.tick();
		field.tick();
		field.tick();
//		field.tick();
//		field.tick();
//		field.tick();
		field.getField();
//		System.out.println(field.getValue().toString());
		
		
		
	}
}
