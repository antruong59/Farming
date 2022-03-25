package students;


public class Field {
	protected int height;
	protected int width;
	protected String[][] field;
	
	public Field(int height, int width)
	{
		this.height = height;
		this.width = width;
		
		this.field = new String[this.height][this.width]; 
		for (int row = 0; row < this.height; row++) {
	        for (int col = 0; col < this.width; col++) {
	        	field[row][col] = ".";
	        }
		}
		
		
//		for (String[] row : field) {
//            for (String i : row) {
//                System.out.print(i + "    ");
//            }
//            System.out.println("\n");
//        }
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
	        	
	        	System.out.print(this.field[row][col] + "    ");
	        }
	        System.out.println("\n");
		}
	}
	
	public void tick() {
		
	}
	public static void main(String[] args) {
		Field field = new Field(5, 10);
		System.out.println("Field height " + field.height);
		System.out.println("Field height " + field.width);
		field.getField();
	}
}
