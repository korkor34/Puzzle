package puzzle;

public class Tally {
	private int solutions;
	private int counter;
	private int flag;
	private int pos[][][] = new int[][][]{{{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										 {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										 {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										 {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										 {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										 {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										 {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}}};
	
	public Tally(){
		solutions = 0;
		counter = 0;
		flag = 0;
	}
	
	public void addCounter(){
		counter++;
	}
	
	public void addSolutions(int[][][] hex){
		solutions++;
		for(int y = 0;y<7; y++){
		pos[y][0][0] = hex[y][0][0];
		pos[y][1][0] = hex[y][1][0];
		for(int x = 0; x < 12; x++){
			pos[y][2][x] = hex[y][2][x];
		}
		pos[y][3][0] = hex[y][3][0];
		}
	}
	
	public void totals(){
		System.out.println("The final count is: " + counter);
		System.out.println("Number of solutions: " + solutions);
		
		for(int i = 0; i < 7; i++){
			System.out.print("Position " + i + ": " + pos[i][3][0] + " | Turn: " + pos[i][1][0] + " | ");
			for(int q = 0; q < 6; q++){
				System.out.print(pos[i][2][q + pos[i][1][0]] + ",");
			}
			System.out.print("\n");
		}
	}
}
