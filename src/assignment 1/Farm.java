package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import students.items.Apples;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;

public class Farm {
	protected int fieldWidth;
	protected int fieldHeight;
	protected int fund;
	protected Field farm;
	private static boolean inFarm; 
		
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.fund = startingFunds;
		
		Field field = new Field(this.fieldHeight, this.fieldWidth);
		this.farm = field;
		
	}
	
	public void harvest(int x, int y, String command) {
		int randNum = new Random().nextInt(25);
    	//System.out.println("Rand Num: " + randNum);
		int randPrize = new Random().nextInt(5);
    	
    	boolean haveTreasure = randNum == 0;
    	
    	if (haveTreasure) {
    		if (randPrize == 0) {
    			this.fund += 2;
    		} else if (randPrize == 1) {
    			this.farm.get(x - 1, y - 1).setValue(5);
    		} else if (randPrize == 2) {
    			this.farm.get(x - 1, y - 1).setValue(5);
    		} else if (randPrize == 3) {
    			this.farm.get(x - 1, y - 1).setValue(5);
    		} else if (randPrize == 4) {
    			this.farm.get(x - 1, y - 1).setValue(5);
    		}
    			
    	}
    	
		if (this.farm.get(x - 1, y - 1) instanceof Food) {
			if (this.farm.get(x - 1, y - 1).getValue() > 0) {
				this.fund += this.farm.get(x - 1, y - 1).getValue();
				System.out.printf("Sold %s for $%s\n", this.farm.get(x - 1, y - 1).getName(), this.farm.get(x - 1, y - 1).getValue());
				this.farm.till(x - 1, y - 1);
			} else {
				
				System.out.println("Plant is not mature enough to harvest");
				while (command.equals("h")) {
					
					Scanner continuePlant = new Scanner(System.in);
					System.out.println("\nDo you still want to sell it? (y/n)");
					// Register
					String yesNo = continuePlant.nextLine();
					yesNo = yesNo.replaceAll(" ", "");
					if (yesNo.equals("y")) {
						System.out.printf("Sold %s for $%s\n", this.farm.get(x - 1, y - 1).getName(), this.farm.get(x - 1, y - 1).getValue());
						this.farm.till(x - 1, y - 1);
						command = "";
						
					} else if (yesNo.equals("n")){
						command = "";
						
					} else {
						System.out.println("Please enter either y or n");
					}
					
				}
			}
			
		} else {
			System.out.printf("\nYou can't harvest %s, just Food only!", this.farm.get(x - 1, y - 1).getName());
		}
//		System.out.println("\nIn while loop h");
	}
	
	public void plant(int x, int y, String command) {
		while (command.equals("p")) {
			
			Scanner getItem = new Scanner(System.in);
			System.out.printf("Enter:\n"
					+ " - 'a' to buy an apple for $%s\n"
					+ " - 'g' to buy grain for $%s\n"
					, 2, 1);
			// Register
			String item = getItem.nextLine();
			item = item.replaceAll(" ", "");
			if (!(this.farm.get(x - 1, y - 1) instanceof Soil)) {
				System.out.println("You can only plant in Soil\n");
				command = "";
				
			} else if ((!item.equals("a")) && (!item.equals("g"))){
				System.out.println("Please enter either a or g");
				
			}else {
			
				if (item.equals("a") && this.fund >= this.farm.get(x - 1, y - 1).getCost()) {
					Apples apple = new Apples();
					this.farm.plant(x - 1, y - 1, apple);
					this.fund -= this.farm.get(x - 1, y - 1).getCost();
					
				} else if (item.equals("g") && this.fund >= this.farm.get(x - 1, y - 1).getCost()) {
					Grain grain = new Grain();
					this.farm.plant(x - 1, y - 1, grain);
					this.fund -= this.farm.get(x - 1, y - 1).getCost();
					
				} else if (this.fund < this.farm.get(x - 1, y - 1).getCost()) {
					System.out.println("\nYou don't have enough fund to buy this item");
				}
				command = "";
			} 
			
		}
		
//		System.out.println("\nIn while loop p");
	}
	
	public void run()
	{
		inFarm = true;

		
		while (inFarm) {
			Scanner userInput = new Scanner(System.in);
			System.out.println("\n***Welcome to the farm***");
			System.out.println(this.farm);
			System.out.println("Bank balance: $" +  this.fund);
			System.out.println("\nEnter your next action: "
					+ "\n t x y: till"
					+ "\n h x y: harvest"
					+ "\n p x y: plant"
					+ "\n s: field summary"
					+ "\n w: wait"
					+ "\n q: quit");
			String input = userInput.nextLine();
			input = input.replaceAll(" ", "");
//			System.out.println(input);
			String[] noLocCommand = new String[]{"s", "w", "q"};
			 
	        // Convert String Array to List
	        List<String> noLocCommandlist = Arrays.asList(noLocCommand);
			
			if (input.length() < 1) {
				System.out.println("\nPlease try another command, either 't', 'h', 'p', 's', 'w' or 'q' :>\n");
			
			} else if (input.length() == 1 && !noLocCommandlist.contains(input)) {
				System.out.println("\nPlease enter the location along with command!");
				
			} else if (input.equals("s")) {
				System.out.println(this.farm.getSummary());
//				System.out.println("\nIn while loop s");
				
			} else if (input.equals("w")) {
				this.farm.tick();
//				System.out.println("\nIn while loop w");
				
			} else if (input.equals("q")) {
//				System.out.println("\nIn while loop q");
				inFarm = false;
				
			} else {
				String command = input.substring(0,1);
				
				
				try {
					int x = 0;
					int y = 0;
					
					System.out.println(Integer.parseInt(input.substring(1,3)) >= 10);
					System.out.println(Integer.parseInt(input.substring(1,3)) <= this.fieldWidth);
					System.out.println(input.length() > 3);
//					
					if (input.length() > 3 
							&& (Integer.parseInt(input.substring(1,3)) >= 10) 
							&& (Integer.parseInt(input.substring(1,3)) <= this.fieldWidth)
							&& (Integer.parseInt(input.substring(3)) <= this.fieldHeight)) {
						x = Integer.parseInt(input.substring(1,3));
						y = Integer.parseInt(input.substring(3));
//						System.out.println("In first if" + x + y);
						
					} else if (input.length() > 3 
							&& (Integer.parseInt(input.substring(2)) >= 10) 
							&& (Integer.parseInt(input.substring(2)) <= this.fieldHeight)
							&& (Integer.parseInt(input.substring(1,2)) <= this.fieldWidth)) {
						x = Integer.parseInt(input.substring(1,2));
						y = Integer.parseInt(input.substring(2));
//						System.out.println("In second if" + x + y);
						
					} else if (input.length() == 3 
							&& (Integer.parseInt(input.substring(1,2)) <= this.fieldHeight)
							&& (Integer.parseInt(input.substring(2,3)) <= this.fieldWidth)) {
						x = Integer.parseInt(input.substring(1,2));
						y = Integer.parseInt(input.substring(2,3));
//						System.out.println("In normal" + x + y);
						
					} else {
						x = Integer.parseInt(input.substring(0, 1));
//						System.out.println("Should go to catch" + x + y);
					}
//					System.out.println(x > this.fieldWidth || y > this.fieldHeight);
//					System.out.println(x > this.fieldWidth);
//					System.out.println(y > this.fieldHeight);
					
					if (command.equals("t")) {
						this.farm.till(x - 1, y - 1);
//						System.out.println("\nIn while loop t" + x + y);
						
					} else if (command.equals("h")) {
						this.harvest(x, y, command);
								
					} else if (command.equals("p")) {
						this.plant(x, y, command);
					}
					
					this.farm.tick();
					
				} catch (NumberFormatException nfe){
					System.out.printf("\nInvalid command! Please follow the format <command> <x> <y>");
					System.out.println("\n" + nfe.getMessage());
					
				} catch (ArrayIndexOutOfBoundsException aioobe){
					System.out.printf("\nThe location should be in range (%s, %s)", this.fieldWidth, this.fieldHeight);
					System.out.println("\n" + aioobe.getMessage());
				}
			}
		}
	}
						

	public static void main(String[] args) {
		Farm farm = new Farm(10, 5, 10);
		farm.run();
	}
}
