package students;

import students.items.Apples;
import students.items.Food;
import students.items.Grain;
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
	        	this.field[row][col] = soil;
	        }
		}
		
		this.field.toString();

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
	
	public String toString() {
		String getField = new String();
		for (int col = 0; col <= this.width; col++) {
			if (col == 0) {
				getField += "     ";
			} else {
				getField += col + "    ";
			}	
		}
		getField += "\n\n";
		
		for (int row = 0; row < this.height; row++) {
			getField += (row + 1) + "    ";
	        for (int col = 0; col < this.width; col++) {
	        	
	        	getField += this.field[row][col].toString() + "    ";
	        }
	        getField += "\n\n";
		}
		return getField;
	}
	
	public void till(int x, int y) {
		Soil soil = new Soil();
		this.field[y][x] = soil;
	}
	
	public Item get(int x, int y) {
		return this.field[y][x];
	}
	
	public void plant(int x, int y, Item item) {
		try {
			if (this.field[y][x] instanceof Soil) {
				this.field[y][x] = item;
				
			} else {
				System.out.printf("You can only plant %s in Soil\n", item.getName());
			}
		} catch (Exception e) {
			System.out.printf("There is no location (%s, %s) to plant! Please choose another location\n", x, y);
		}
			
	}
	
	public int getValue() {
		int totalValue = 0;
		
		for (int row = 0; row < this.height; row++) {
	        for (int col = 0; col < this.width; col++) {
	        	if (this.field[row][col].getValue() != 0) {
	        		totalValue += this.field[row][col].getValue();
	        	}
	     
	        }
		}
		
		return totalValue;
		
	}
	
	public String getSummary() {
		int counterApples = 0;
		int counterGrain = 0;
		int counterSoil = 0;
		int counterUntiled = 0;	
		int counterWeed = 0;
		
		for (int row = 0; row < this.height; row++) {
	        for (int col = 0; col < this.width; col++) {
	        	if (this.field[row][col].toString().toUpperCase().equals("A")) {
	        		counterApples ++;
	        	} else if (this.field[row][col].toString().toUpperCase().equals("G")) {
	        		counterGrain ++;
	        	} else if (this.field[row][col].toString().equals(".")) {
	        		counterSoil ++;
	        	} else if (this.field[row][col].toString().equals("/")) {
	        		counterUntiled ++;
	        	} else if (this.field[row][col].toString().equals("#")) {
	        		counterWeed ++;
	        	}
	        	
	        }
		}
		
		String summary = new String();
 
		summary += String.format("%-15.50s%-15.50s%n", "Apples: ", counterApples);
		summary += String.format("%-15.50s%-15.50s%n", "Grain: ", counterGrain);
		summary += String.format("%-15.50s%-15.50s%n", "Soil: ", counterSoil);
		summary += String.format("%-15.50s%-15.50s%n", "Untilled: ", counterUntiled);
		summary += String.format("%-15.50s%-15.50s%n", "Weed: ", counterWeed);
		summary += String.format("%-15.50s%-15.50s%n", "For a total of ", "$" + this.getValue());
		summary += String.format("%-15.50s  %-15.50s%n", "Total apples created:", Apples.getGenerationCount());
		summary += String.format("%-15.50s  %-15.50s%n", "Total grain created:", Grain.getGenerationCount());
		
		return summary;
	}
	
	public static void main(String[] args) {
		Field field = new Field(5, 5);
		System.out.println(field.getSummary());
		System.out.println("Without apples \n" + field);
		System.out.println("Field height " + field.height);
		System.out.println("Field height " + field.width);
//		field.getField();
		field.tick();
//		field.getField();
		Apples a = new Apples();
//		System.out.println(Apples.getGenerationCount());
		field.plant(1, 3, a);
		System.out.println("With apples \n" + field + "end");
		System.out.println(field.getSummary());
//		field.getField();
		field.tick();
//		field.getField();
		field.tick();
//		field.getField();
		field.tick();
		System.out.println("With apples \n" + field + "end");
//		field.getField();
		field.tick();
//		field.getField();
		field.tick();
		System.out.println("With apples \n" + field + "end");
		System.out.println(field.getSummary());
//		field.getField();
		Apples a1 = new Apples();
		field.plant(0, 0, a1);
		field.tick();
		System.out.println("With apples \n" + field + "end");
//		field.getField();
//		System.out.println(field.getValue());
		System.out.println(field.getSummary());
		
	}
}
