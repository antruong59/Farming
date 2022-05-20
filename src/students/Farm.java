package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import students.items.Apples;
import students.items.Fertilizer;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;

public class Farm {
	/* Farm class is a main menu of the game, using farm as a item storage to function
	 * Having README instruction in the 'i: instruction' section of the game*/
	protected int fieldWidth;
	protected int fieldHeight;
	
	// User fund, bank balance
	protected int fund;
	
	// Set farm as a field
	protected Field farm;
	
	// Check in farm status
	private boolean inFarm; 
	
	// Check ability to function
	private boolean canFunction;
	
	// Set mode of game (either "beginner" or "normal")
	private String mode;
	
	// Display mode switch on/off
	private boolean displayMode;
	
	// Initialize farm by field
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.fund = startingFunds;
		
		Field field = new Field(this.fieldHeight, this.fieldWidth);
		this.farm = field;
		
	}
	
	// Get surprise prize when harvesting at location (x, y)
	public void suprisePrize(int x, int y) {
		
    	// Random 5 different chance of surprise prizes
		int randPrize = new Random().nextInt(5);
		
		// Chance to + $2 
		if (randPrize == 0) {
			this.fund += 2;
			System.out.println("Congrat! You just got $2!");
		
		// Chance to increase value of harvested plant 
		} else if (randPrize == 1) {
			this.farm.get(x - 1, y - 1).setValue(5);
			System.out.printf("\nCongrats! Your %s's value just increase to %s!", this.farm.get(x - 1, y - 1).getName(), this.farm.get(x - 1, y - 1).getValue());
		
		// Chance to - $1
		} else if (randPrize == 2) {
			this.fund -= 1;
			System.out.println("Congrats! You just lost $1! Unlucky! :(");
			
		// Chance to lose the plant
		} else if (randPrize == 3) {
			this.till(x, y);
			System.out.println("Sorry, your plant has just gone:> Unlucky! ");
			
		// Chance to play minigame Tictactoe
		// Just set to 3x3 to easier implementation
		// Can be tested by adding option to user choose customized size
		} else if (randPrize == 4) {
			boolean inMinigame = true;
			String winner = null;
			
			System.out.println("Congrats, you just got a challenge from harvesting!" 
						+ "\n***** Welcome to TIcTacToe *****");
			
			// In minigame and haven't got any winner
			// Having instruction in how to play
			// Can refuse not to play
			while (inMinigame && winner == null) {
				Scanner playGame = new Scanner(System.in);
				System.out.println("\tTICTACTOE"
						+ "\nPlease enter an action here:"
						+ "\n (p) Let's play!"
						+ "\n (h) How to play"
						+ "\n (q) Refuse"
						+ "\n*** Noted: If you won, you will get an extra prize (FUND X2)"
						+ "\n There could also be a ~hiden-bad-luck-chance~ if you refuse :)");
				
				String option = playGame.nextLine();
				option = option.replaceAll(" ", "");
				List<String> optionList = Arrays.asList("p", "h", "q");
				
				// User input validation
				if (!optionList.contains(option)) {
					System.out.println("Please enter either (p), (h) or (q)");
					
				} else if (option.equals("q")) {
					int badLuck = new Random().nextInt(10);
					
					// 10% chance to get bad luck if quit without playing the game 
					if (badLuck == 0) {
						this.fund -= 5;
					}
					
					System.out.println("What a pity! No prize for you this time then :<");
					inMinigame = false;
					
				} else if (option.equals("h")) {
					System.out.println("\n************************************" 
							+ "\n\t====== HOW TO PLAY =====\t"
							+ "\n * You can either choose (X) or (O) to play this game"
							+ "\n * (X) always go first"
							+ "\n * You will play with a bot in a 3x3 Tictactoe game"
							+ "\n * You need to have 3 in a row to win this game (either vertical, horizontal or diagonal"
							+ "\n * Enjoy your game!");
					
				} else if (option.equals("p")) {
					Tictactoe minigame = new Tictactoe(3);
					winner = minigame.run();
					
					// Won -- double the fund
					if (winner.equals("player")) {
						System.out.println("Good job! You're doing great! Here is your prize!");
						this.fund = this.fund * 2;
						
					// Draw -- +5 to fund
					} else if (winner.equals("draw")) {
						System.out.println("Almost there! Still got something for you anyway!");
						this.fund += 5;
					
					// Lost -- +1 supporting
					} else {
						System.out.println("Better luck next time then, thanks for playing!");
						this.fund ++;
					}
					
				}
				
			}
			
			 
		}
    	
	}
	
	// Harvest at location (x, y)
	// Random chance having treasure when harvesting
	// Treasure only appear when user action harvest successSfully
	public void harvest(int x, int y) {
		
		// Random chance having treasure when harvesting
		int randNum = new Random().nextInt(5);
		boolean haveTreasure = randNum == 0;
		
		// Harvest Food only
		// With mature plant, harvest immediately and sell for money
		// With immature plant, prompt to ask for confirmation again
		if (this.farm.get(x - 1, y - 1) instanceof Food) {
			if (this.farm.get(x - 1, y - 1).getValue() > 0) {
				if (haveTreasure) {
					this.suprisePrize(x , y);
				}
				this.fund += this.farm.get(x - 1, y - 1).getValue();
				System.out.printf("Sold %s for $%s\n", this.farm.get(x - 1, y - 1).getName(), this.farm.get(x - 1, y - 1).getValue());
				this.farm.harvest(x - 1, y - 1);
			} else {
				
				System.out.println("Plant is not mature enough to harvest");
				String yesNo = "";
				
				while (this.farm.get(x - 1, y - 1).getValue() == 0 && !yesNo.equals("n")) {
					
					Scanner continuePlant = new Scanner(System.in);
					System.out.println("\nDo you still want to sell it? (y/n)\nYou will get nothing from selling it now!");
					
					yesNo = continuePlant.nextLine();
					yesNo = yesNo.replaceAll(" ", "");
					if (yesNo.equals("y")) {
						if (haveTreasure) {
							this.suprisePrize(x , y);
						}
						System.out.printf("\nSold %s for $%s\n", this.farm.get(x - 1, y - 1).getName(), this.farm.get(x - 1, y - 1).getValue());
						this.farm.harvest(x - 1, y - 1);
						
					} else if (yesNo.equals("n")) {
						canFunction = false;
						System.out.println("Yeah, good choice");
						
					} else {
						System.out.println("\nPlease enter either y or n");
					}
					
				}
			}
			
		} else {
			System.out.printf("\nYou can't harvest %s, just Food only!", this.farm.get(x - 1, y - 1).getName());
			canFunction = false;
		}

	}
	
	// Plant a Food item at (x, y)
	// Check for enough fund to buy
	// Plant in Soil only
	public void plant(int x, int y) {
		
		if (!(this.farm.get(x - 1, y - 1) instanceof Soil)) {
			canFunction = false;
			System.out.println("You can only plant in Soil\n");
			
		} else if (this.fund <= 0 || this.fund < Apples.getCost() || this.fund < Grain.getCost()) {
			canFunction = false;
			System.out.println("\nYou don't have enough fund to buy this item");
			
		} else {
		
			while (this.farm.get(x - 1, y - 1) instanceof Soil) {
				
				Scanner getItem = new Scanner(System.in);
				System.out.printf("Enter:\n"
						+ " - 'a' to buy an apple for $%s\n"
						+ " - 'g' to buy grain for $%s\n"
						, 2, 1);
			
				String item = getItem.nextLine();
				item = item.replaceAll(" ", "");
				if ((!item.equals("a")) && (!item.equals("g"))){
					System.out.println("Please enter either a or g");
					
				}	else {
				
					if (item.equals("a") && this.fund >= Apples.getCost()) {
						Apples apple = new Apples();
						this.farm.plant(x - 1, y - 1, apple);
						this.fund -= Apples.getCost();
						
					} else if (item.equals("g") && this.fund >= Grain.getCost()) {
						Grain grain = new Grain();
						this.farm.plant(x - 1, y - 1, grain);
						this.fund -= Grain.getCost();
						
					} 
				} 
			}
			
		}
		

	}
	
	// Till at (x, y) and marked as tilled
	public void till(int x, int y) {
		this.farm.markTilledItem(x - 1, y - 1);
		this.farm.till(x - 1, y - 1);
	}
	
	// Choose fertilizer and buy with corresponding cost
	// Fertilize plant Food only
	// Check for enough fund to buy
	public void fertilizer(int x, int y) {
		String fertilizer = null;
		
		while (fertilizer == null) {
			Scanner option = new Scanner(System.in);
			System.out.println("Please choose one of these fertilizers:" 
								+ "\n (1) F1: more Vitamins, fasten maturation age -- cost: $2"
								+ "\n (2) F2: plant more valuable -- cost: $4"
								+ "\n (q) Maybe later");

			String choice = option.nextLine();
			choice = choice.replaceAll(" ", "");
			List<String> optionList = Arrays.asList("1", "2", "q");
			
			if (!optionList.contains(choice)) {
				System.out.println("Please enter either (1), (2) or (q)");
				
			} else if (choice.equals("q")) {
				canFunction = false;
				fertilizer = "";
				
			} else if (!(this.farm.get(x - 1, y - 1) instanceof Food)) {
				canFunction = false;
				System.out.println("\nYou can only use fertilizer for plant!");
				
			} else if (choice.equals("1")) {
				Fertilizer f1 = new Fertilizer("Fasten Mature age", 2, 2);
				
				if (this.fund < f1.getCostFertilizer()) {
					System.out.println("You don't have enough money to get this item!");
				
				} else {
					int age = (int) this.farm.get(x - 1, y - 1).getAge();
					age += f1.getPower();
					
					this.farm.get(x - 1, y - 1).setAge(age);
					System.out.printf("\nUsing: %s -- Your plant age is now +2, age = %s\n", f1.getName(), this.farm.get(x - 1, y - 1).getAge());
					this.fund -= f1.getCostFertilizer();
					
					fertilizer = "f1";
				}
				
				
			} else if (choice.equals("2")) {
				Fertilizer f2 = new Fertilizer("Plant more valuable", 4, 2);
				
				if (this.fund < f2.getCostFertilizer()) {
					System.out.println("You don't have enough money to get this item!");
				
				} else {
					int value = (int) this.farm.get(x - 1, y - 1).getValue();
					value = (int) value * 2;
					
					this.farm.get(x - 1, y - 1).setValue(value);
					System.out.printf("\nUsing: %s -- Your plant value is now double, value = %s\n", f2.getName(), this.farm.get(x - 1, y - 1).getValue());
					
					this.fund -= f2.getCostFertilizer();
					
					fertilizer = "f2";
				}
				
			}
		}
	}
	
	public void run()
	{
		// Default mode set to "normal"
		// Display mode off
		this.inFarm = true;
		this.mode = "normal";
		this.displayMode = false;
		
		while (this.inFarm) {
			this.canFunction = true;
			
			Scanner userInput = new Scanner(System.in);
			System.out.println("\n\t*****\tWelcome to the farm\t*****\t");
			
			if (displayMode == true) {
				System.out.printf("\nCurrent mode: %s\n", mode);
			}
			
			System.out.println(this.farm);
			System.out.println("Bank balance: $" +  this.fund);
			System.out.println("\nEnter your next action: "
					+ "\n t x y: till"
					+ "\n h x y: harvest"
					+ "\n p x y: plant"
					+ "\n f x y: fertilizer"
					+ "\n s: field summary"
					+ "\n w: wait"
					+ "\n m: mode (beginner/normal)"
					+ "\n i: instruction"
					+ "\n q: quit");
			String input = userInput.nextLine();
			input = input.replaceAll(" ", "");

			 
	        // Valid command with no location
	        List<String> noLocCommandlist = Arrays.asList("s", "w", "q", "m", "i");
	        
	     // Valid command with location
	        List<String> locCommandlist = Arrays.asList("t", "h", "p");
			
	        // Input validation
			if ((input.length() <= 1 && !noLocCommandlist.contains(input) && !locCommandlist.contains(input))
					|| (input.length() > 1 && noLocCommandlist.contains(input.substring(0,1)))) {
				System.out.println("\nPlease try another command, either 't', 'h', 'p', 's', 'w', 'm', 'i' or 'q' :>\n");
			
			} else if (input.length() == 1 && locCommandlist.contains(input)) {
				System.out.println("\nPlease enter the location along with command!");
				
			} else if (input.equals("s")) {
				System.out.println(this.farm.getSummary());

				
			} else if (input.equals("w")) {
				this.farm.tick();

			// Set mode to either beginner or normal, mode on - off	
			} else if (input.equals("m")) {
				String mode = null;
				
				List<String> modeList = Arrays.asList("1", "2", "3", "4");
				
				while (!modeList.contains(mode)) {
					Scanner getMode = new Scanner(System.in);
					System.out.println("\nEnter:"
							+ "\n <1> Beginner mode"
							+ "\n <2> Normal mode"
							+ "\n <3> Display mode on"
							+ "\n <4> Display mode off");
				
					mode = getMode.nextLine();
					mode = mode.replaceAll(" ", "");
					
					if (mode.equals("1")) {
						this.mode = "beginner";
						System.out.println("You just set mode to Beginner");
						
					} else if (mode.equals("2")) {
						this.mode = "normal";
						System.out.println("You just set mode to Normal");
						
					} else if (mode.equals("3")) {
						this.displayMode = true;
						System.out.println("Display mode on");
						
					}else if (mode.equals("4")) {
						this.displayMode = false;
						System.out.println("Display mode off");
						
					} else {
						System.out.println("\nPlease enter either (1), (2), (3) or (4)");
					}
					
				}
				

				
			} else if (input.equals("i")) {
				System.out.println("---------- WELCOME TO THE FARM ----------\n"
						+ "\n	=== INSTRUCTION ===\n"
						+ "\n* * This is a program conducted by An Truong – truan004\n"
						+ "\nTHE FARM is a farming game that enables you to either buy, plant, harvest or sell \nyour farming products with 9 options and multiple of surprise presents during your game. \nHere is the functionality of each command option:\n"
						+ "\n-- t x y: till your chosen location (x, y) and turn every current item in this \n   location into Soil\n"
						+ "\n-- h x y: harvest your farming product at location (x, y), then sell it to get \n   money ** MUST BE FOOD ONLY **\n"
						+ "\n-- p x y: plant a FOOD at location (x, y), you will have to pay money to get \n   these plants corresponding to their cost \n   ** DON’T FORGET TO CHECK YOUR BANK BALANCE BEFORE BUYING **\n"
						+ "\n-- f x y: fertilize your FOOD plant at location (x, y), you will have to pay \n   money to get these fertilizers corresponding to their cost \n   ** DON’T FORGET TO CHECK YOUR BANK BALANCE BEFORE BUYING **\n"
						+ "\n-- s: display summary of the field, including all information about the plants \n   recently available in your farm and a total number of product you have created\n"
						+ "\n-- w: wait and skip a turn without execute any action\n"
						+ "\n-- m: switch the mode between beginner and normal \n   ** (beginner) don’t have punishment for any wrong action, while (normal) still have\n"
						+ "\n-- i: instruction of how to play the game\n"
						+ "\n-- q: exit from the game\n"
						+ "\n==== SPECIAL FEATURE ====\n"
						+ "\n*** When ever you harvest any FOOD plant in the field, you will have a chance \nto have a surprise prize. It can be either a prize or a minigame or even a ~bad-luck~! ***\n"
						+ "------------------ YOU ARE NOW READY FOR THE GAME ** ENJOY ------------------\n"
						+ "");
				
			}else if (input.equals("q")) {

				System.out.println("See you next time then! Thank you for spending your time!");
				inFarm = false;
				
			} else {
				String command = input.substring(0,1);
				
				// Input validation, check without space as well, work with input from 1 to 99
				try {
					
					int x = 0;
					int y = 0;
					
					// Check if input without space available with x is 2 digit integer
					if (input.length() > 3 
							&& (Integer.parseInt(input.substring(1,3)) >= 10) 
							&& (Integer.parseInt(input.substring(1,3)) <= this.fieldWidth)
							&& (Integer.parseInt(input.substring(3)) <= this.fieldHeight)) {
						x = Integer.parseInt(input.substring(1,3));
						y = Integer.parseInt(input.substring(3));

					// Check if input without space available with y is 2 digit integer
					} else if (input.length() > 3 
							&& (Integer.parseInt(input.substring(2)) >= 10) 
							&& (Integer.parseInt(input.substring(2)) <= this.fieldHeight)
							&& (Integer.parseInt(input.substring(1,2)) <= this.fieldWidth)) {
						x = Integer.parseInt(input.substring(1,2));
						y = Integer.parseInt(input.substring(2));

					// Check if input without space available with normal case
					} else if (input.length() == 3 
							&& (Integer.parseInt(input.substring(1,2)) <= this.fieldWidth)
							&& (Integer.parseInt(input.substring(2,3)) <= this.fieldHeight)) {
						x = Integer.parseInt(input.substring(1,2));
						y = Integer.parseInt(input.substring(2,3));

					// Index out of range	
					} else {
						System.out.println("Index out of range! ");
						x = Integer.parseInt(input.substring(0, 1));

					}

					if (command.equals("t")) {
						this.till(x, y);

						
					} else if (command.equals("h")) {
						this.harvest(x, y);
								
					} else if (command.equals("p")) {
						this.plant(x, y);
						
					} else if (command.equals("f")) {
						this.fertilizer(x, y);
					}
					
					// If beginner, farm doesn't punish for wrong decision making
					if (canFunction == true && mode.equals("beginner")) {
						this.farm.tick();
						
					// If normal, farm does punish for wrong decision making
					} else if (mode.equals("normal")) {
						this.farm.tick();
					}
					
				} catch (NumberFormatException nfe) {
					System.out.printf("\nInvalid command! Please follow the format <command> <x> <y>");
					System.out.println("\n" + nfe.getMessage());
					
				} catch (ArrayIndexOutOfBoundsException aioobe) {
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
