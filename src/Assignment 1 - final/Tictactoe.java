package students;

import students.items.Apples;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;
import students.items.UntilledSoil;
import students.items.Weed;

import java.lang.Math;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tictactoe {
	
	/* This is a Tictactoe NxN minigame playing with a bot that keep track of player's move and prevent player from winning.
	 * This version just follow the basic rules of Tictactoe 3x3 that player needs to have either N in a row, a column or in both 2 diagonals of a NxN board.
	 * The logic of the game can be extend to follow the rules of having 4 in a row, once being block, required 5 to win in any size of NxN board.
	 * The idea of Tictactoe can be opened by applying it to the field in the farm that challenges player to plant N plants in either a row, a column or in both 2 diagonals to earn prizes.
	 * */
	
	// Size of the board
	protected int size;
	
	// Create 2D String array board
	protected String[][] board;
	
	// Player choice
	protected String player = null;
	
	// Bot choice
	protected String bot = null;
	
	// Tictactoe playing mode, to set the basic mode to 3x3, extended mode to greater than 3
	private String mode;
	
	// Tictactoe constructor - declare and initialize board in Tictactoe
	// Set number from 1 to n^2 as placeholder in the board 
	public Tictactoe(int size)
	{
		this.size = size;
		
		this.board = new String[this.size][this.size]; 
		for (int row = 0; row < this.size; row++) {
	        for (int col = 0; col < this.size; col++) {
	        	String location = String.valueOf(col + 1 + this.size * row);
	        	this.board[row][col] = location ;
	        }
		}
	}
	
	// Return a String to display Tictactoe board  
	public String toString() {
		String getBoard = new String();
		for (int row = 0; row < this.size; row++) {
			getBoard += "|--------".repeat(this.size) + "|\n|   ";
			
	        for (int col = 0; col < this.size; col++) {
	        	if (this.board[row][col].length() > 1) {
	        		getBoard += this.board[row][col] + "   |   ";
	        	} else {
	        		getBoard += this.board[row][col] + "    |   ";
	        	}
	        	
	        }
	        getBoard += "\n";
		}
		getBoard += "|--------".repeat(this.size) + "|";
		return getBoard;
	}

    // Check for Winner, take rowNum, colNum and player (either human player or bot) as parameter
    public boolean checkWinner(int rowNum, int colNum,  String player)
    {
    	// Counter player symbol in each row, column and diagonal
    	// Checker if counter == n
    	// If yes, player win
    	// If no, reset count = 0
    	// If player win, return true
    	// Otherwise, return false
    	int count = 0;

    	// Horizontal check
    	
    	// For each column share the same row with current player location
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	for (int col = 0; col < this.size; col++) {
    	    if (this.board[rowNum][col].equals(player)){
        		count++;
        		
        		}
    	    
    	    else
    	        count = 0;

    	}
    	
    	// If count == n, player win
    	if (count == this.size)
	    	return true;
    	
	    else count = 0;
    	
    	// Vertical check
    	
    	// For each row share the same column with current player location
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	for (int row = 0; row < this.size; row++) {
    	    if (this.board[row][colNum].equals(player)){
        		count++;
        		
        		}
    	    
    	    else
    	        count = 0;
    	} 
    	
    	// If count == n, player win
    	if (count == this.size)	    	
	        return true;
	    else count = 0;
    	
    	// Top left to bottom right
    	
    	// For each location meet the condition row = column (y = x)
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	// **Notes: This algorithm can be extend by changing the condition to (|x - y| = const)
    	for (int row = 0; row < this.size; row++) {
	        for (int col = 0; col < this.size; col++) {
	        	if (row == col && this.board[row][col].equals(player)) {
	        		count++;
	        		
	        		}
	        }
    	}
    	
    	// If count == n, player win
    	if (count == this.size) {
    		
    	    return true;}
    	else count = 0;
    	
    	// Top right to bottom left
    	
    	// For each location meet the condition row + column = N <this.size> (x + y = N)
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	// **Notes: This algorithm can be extend by changing the condition to (x + y = const)
    	for (int row = 0; row < this.size; row++) {
	        for (int col = 0; col < this.size; col++) {
	        	if (row + col == this.size - 1 && this.board[row][col].equals(player)){
	        		count++;
	        		
	        		}
	        }
    	}
    	
    	// If count == n, player win
    	if (count == this.size) {
    		
    	    return true;}
    	else count = 0;
    	
    	// If none of the above case check count == this.size, return false
    	return false;
    }
    
    // Check if the position (row, col) is available, return true if it is available
    // A position is available if it doesn't contain neither player nor bot symbol
    public boolean checkValidPos(int row, int col) {
    	if (this.board[row][col].equals(this.player) || this.board[row][col].equals(this.bot)) {
    		return false;
    	}
    		
    	return true;
    }
    
    // Instruction for bot to play Tictactoe to prevent human player from winning 
    // Return position of bot that should be played
    public String botPlay(String botPosition, String player) {

    	// Counter player symbol in each row, column and diagonal
    	// Checker if counter == n
    	// If yes, player win
    	// If no, reset count = 0
    	// If player win, return true
    	// Otherwise, return false
    	int count = 0;
    	
    	// Horizontal check
    	
    	// For each column share the same row with current player location
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	for (int row = 0; row < this.size; row++) {
	        for (int col = 0; col < this.size; col++) {
	    	    if (this.board[row][col].equals(player)) {
	    	        count++;}

	        }
	        
	        // If count == n - 1, human player is going to win next round
	        // Bot will search for the location that block opponent's way
	        if (count == this.size - 1) {
    	    	for (int col1 = 0; col1 < this.size; col1++) {
    	    		if (this.checkValidPos(row, col1)) {
    	    			botPosition = this.board[row][col1];
    		    		return botPosition;
    	    		}
    	    		
    	    	}
    	    } else count = 0;
	    	    	
    	} 
    	
    	// Vertical check
    	
    	// For each row share the same column with current player location
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	for (int col = 0; col < this.size; col++) {
	        for (int row = 0; row < this.size; row++) {
	    	    if (this.board[row][col].equals(player)){
	    	        count++;}

	        }
	        
	        // If count == n - 1, human player is going to win next round
	        // Bot will search for the location that block opponent's way
	        if (count == this.size - 1) {
    	    	for (int row1 = 0; row1 < this.size; row1++) {
    	    		if (this.checkValidPos(row1, col)) {
    	    			botPosition = this.board[row1][col];
    		    		return botPosition;
    	    		}
    	    		
    	    	}
    	    } else count = 0;
    	} 
	        
    	
    	// Top left to bottom right
    	
    	// For each location meet the condition row = column (y = x)
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	// **Notes: This algorithm can be extend by changing the condition to (|x - y| = const)
    	for (int row = 0; row < this.size; row++) {
	        for (int col = 0; col < this.size; col++) {
	        	if (row == col && this.board[row][col].equals(player)){
	    	        count++;}

	        }
    	}
    	
    	// If count == n - 1, human player is going to win next round
        // Bot will search for the location that block opponent's way
    	if (count == this.size - 1){
    		for (int row1 = 0; row1 < this.size; row1++) {
    	        for (int col1 = 0; col1 < this.size; col1++) {
    	    		if (row1 == col1 && this.checkValidPos(row1, col1)) {
    	    			botPosition = this.board[row1][col1];
    		    		return botPosition;
    	    		}
    	        }
	    	}
	    } else count = 0;
    	
    	// Top right to bottom left
    	
    	// For each location meet the condition row + column = N <this.size> (x + y = N)
    	// If there is player symbol, counter++
    	// Else reset count = 0 
    	// **Notes: This algorithm can be extend by changing the condition to (x + y = const)
    	for (int row = 0; row < this.size; row++) {
	        for (int col = 0; col < this.size; col++) {
	        	if (row + col == this.size - 1 && this.board[row][col].equals(player)){
	    	        count++; }

	        }
    	}
    	
    	// If count == n - 1, human player is going to win next round
        // Bot will search for the location that block opponent's way
    	if (count == this.size - 1){
    		for (int row1 = 0; row1 < this.size; row1++) {
    	        for (int col1 = 0; col1 < this.size; col1++) {
    	    		if (row1 + col1 == this.size - 1 && this.checkValidPos(row1, col1)) {
    	    			botPosition = this.board[row1][col1];
    		    		return botPosition;
    	    		}
    	        }
	    	}
	    } else count = 0;
    	
    	return botPosition;
    }
    	
    // Check for current slot occupation in board
    // Return occupation counter
    // If counter == n^2, board run out of slot
    public int checkSlot(List<String >temp, List<String> optionList) {
    	
    	// Occupation counter
    	int countOccupied = 0;
		
    	// Count for number of current slots in board that stored in temporary List<String>
		for (String slot : temp) {
			if (optionList.contains(slot)) {
				countOccupied ++;
			} else countOccupied = 0;
		}
		return countOccupied;
    }
    
    // Run the Tictactoe game logic
    // Return String of winner
    public String run() {
    	
    	// Declare and initialize default winner
    	String winner = null;
    	
    	// Set "X" to go first
    	String goFirst = "X";
    	
    	// Set current mode default to "basic"
    	this.mode = "basic";
    	
    	// Store bot position (number from 1 to n^2) in both integer and string
    	int botPos;
		String botPosition = null;
		
		// Store human player position (number from 1 to n^2) in both integer and string
		int pos;
    	String position;
    
    	// List of available symbol, either "X" or "O"
    	List<String> optionList = Arrays.asList("X" , "O");
    	
    	Scanner inOption = new Scanner(System.in);
    	
    	// Display board
    	System.out.println(this);
    		
    	// Loop check while there is no winner identified
    	while (winner == null) {

    		// Prompt to get user choice of side, either "X" or "O"
    		while (this.player == null) {
        		System.out.println("\nWhich side do you want to choose?\nPlease choose either (X) or (O)");
            	String userChoice = inOption.nextLine();
            	
            	// Get rid of space in user input
            	userChoice.replaceAll(" ", "");
            	
            	// Input validation
            	if (!optionList.contains(userChoice)) {
            		System.out.println("\nYou must choose either (X) or (O). Please try again!");
            	
            	} else {
            		
            		// Set player side
            		this.player = userChoice;
            		
            		if (this.player.equals("X")) {
            			this.bot = "O";
            			System.out.printf("\nYou will start with %s\nBot will start with %s\n", this.player, this.bot);
            			System.out.println("As you chose (X), you will go first!");
            		} else {
            			this.bot = "X";
            			System.out.printf("\nYou will start with %s\nBot will start with %s\n", this.player, this.bot);
            			System.out.println("As you chose (O), bot will go first!");
            		}
            		
            	}
        	}
    		

    		// Check if bot go first, activate this scope
    		// Otherwise, prompt for user input
    		if (goFirst.equals(this.bot)) {
    			
    			// For 3x3 board, go in the middle slot of the board to either win or draw
    			// Set bot to go to slot number "5"
    			if (this.mode.equals("basic") && this.size == 3) {
    				botPosition = "5";
    				for (int row = 0; row < this.size; row++) {
    			        for (int col = 0; col < this.size; col++) {
    			        	if (this.board[row][col].equals(botPosition)) {
    			        		this.board[row][col] = this.bot;
    			        	}
    			        }
    				}
    				
    			// Set random position for bot in range 1 to n^2
    			} else {
    				botPos = new Random().nextInt((int) Math.pow(this.size, 2));
       			 	botPosition = String.valueOf(botPos + 1);
    			}
  			
    			// Reset goFirst
    			goFirst = "";
    			
    		}
    		
    		System.out.println(this);
    		Scanner inPosition = new Scanner(System.in);
    		
    		// Prompt for user position choice in board
    		System.out.printf("\nPlease choose an integer from 1 to %s to place your choice\n", (int) Math.pow(this.size, 2));
    		position = inPosition.nextLine();
    		
    		position.replaceAll(" ", "");

    		// Temporary List<String> to store current state of board
    		List<String> temp;
			temp = new ArrayList<String>();
			
			// Check if human can play
			// If yes, bot will play its turn
			// Otherwise, it will skip that turn and prompt another user input
			boolean canPlay = true;
    		
			// Try catch the integer converter of user position
			// Input integer validation
    		try {
    			pos = Integer.parseInt(position);
    			
    			// Initialize current board's state 
    			for (int row = 0; row < this.size; row++) {
			        for (int col = 0; col < this.size; col++) {
			        	
			        	temp.add(this.board[row][col]);
			        	
			        }
    			}
    			
    			// Check if slot is available to choose
    			if (!temp.contains(position) && 1 <= pos && pos <= Math.pow(this.size, 2)) {
        			System.out.println("It's already occupied! Please try another one!");

        			canPlay = false;
        			continue;
	        	}
    			
    			// Check if out of range
    			else if (pos > Math.pow(this.size, 2) || pos < 1) {
    				System.out.printf("\nOut of range! Please enter an integer from 1 to %s\n", (int) Math.pow(this.size, 2));
    				
    			} else {
    				
    				
    				temp = new ArrayList<String>();
	
    				// Replace number in user choice with user symbol
    				for (int row = 0; row < this.size; row++) {
    			        for (int col = 0; col < this.size; col++) {
 
    			        	if (position.equals(this.board[row][col])) {
    			        		this.board[row][col] = this.player;			        		
  
    			        		// Check if user win, winner set to player
    			        		// Print announcement "You won!"
			        			if (this.checkWinner(row, col, this.player)) {
			            			winner = "player";
			            			canPlay = false;
			            			System.out.println("You won!");
			            		}
    	        		
    			        	} 
    			        	
    			        	temp.add(this.board[row][col]);
    			        	
    			        	
    			        }
    				}
    				
    				// If human player can play and not run out of slot in board, bot play
        			if (canPlay && this.checkSlot(temp, optionList) != (int) Math.pow(this.size, 2)) {
        				
        				// If user play first and haven't chosen "5", bot will go with "5" 
        				// For 3x3 Tictactoe
        				if (temp.contains("5")) {
            				for (int row = 0; row < this.size; row++) {
                		        for (int col = 0; col < this.size; col++) {
                		        	if ("5".equals(this.board[row][col])) {
                		        		botPosition = "5";
                		        	}
                		        }
            				}
                		}
            			
            			// Set random bot position 
            			while (!temp.contains(botPosition)) {
        	    			botPos = new Random().nextInt((int) Math.pow(this.size, 2));
        	    			botPosition = String.valueOf(botPos + 1);
        	    	
            			}
            			
            			// Check for user winning state and reset bot position if needed
            			botPosition = this.botPlay(botPosition, this.player);
            			
            			temp = new ArrayList<String>();
            			
            			// Replace number in bot position with bot symbol
                		for (int row = 0; row < this.size; row++) {
            		        for (int col = 0; col < this.size; col++) {
            		        	if (botPosition.equals(this.board[row][col])) {
            		        		this.board[row][col] = this.bot;
            		        		
            		        		// Check if bot win, winner set to bot
        			        		// Print announcement "You lost!"
            		        		if (this.checkWinner(row, col, this.bot)) {
            	            			winner = "bot";
            	            			System.out.println("You lost!");
            	            		}
            		        	}
            		        	temp.add(this.board[row][col]);
            		        }
                		}
        			}
        			
    			}
    			
    			
    			// Check current state of board
    			// If there is no slot left and no winner, return winner = "draw"
    			int countOccupied = this.checkSlot(temp, optionList);
    			if (countOccupied == (int) Math.pow(this.size, 2)) {
    				winner = "draw";
    				System.out.println("You draw!");
    			}
        		
        		
    			
    			
    		} catch (Exception e) {
    			System.out.println("Please enter an integer to place your choice");
    			
    		}
    		
    		
    		
    	}
    	
    	
    	System.out.println(this);
    	System.out.println("GAME OVER");
		return winner;
    }
  
    public static void main(String[] args)
    {
    	Tictactoe n = new Tictactoe(5);
    	String winner = n.run();
    	System.out.println("WINNER " + winner);
//        
    }

	
	
}
