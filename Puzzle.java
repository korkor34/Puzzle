package puzzle;
/*
 * Created by Ethan Puller on 10-31-2017
 * 
 * This program solves the "Drive ya Nutz" puzzle by Hasbro.
 * 
 * It utilizes a 3d array, which is someone hard to understand, but very easy to forget.
 * 
 * The first array determines which hex it is. there are 0-6 (7)
 * The second array is what part of each hex you are looking at.
 * 		[z][0][0] is never used
 * 		[z][1][0] indicates the turn
 * 		[z][2][0-11] is the pattern of the hex. It is actually 0-5 (6),
 * 		but is repeated twice as to simulate a rotation by reading from the turn.
 * 		[z][3][0] is used to arrange each position in each permutation.
 * 
 * 
 */
public class Puzzle {
		
		static int hex[][][] = new int[][][]{{{0},{0},{1,2,3,4,5,6,1,2,3,4,5,6},{1}},
								 			 {{1},{0},{1,4,3,6,5,2,1,4,3,6,5,2},{2}},
											 {{2},{0},{1,4,6,2,3,5,1,4,6,2,3,5},{3}},
											 {{3},{0},{1,6,2,4,5,3,1,6,2,4,5,3},{4}},
											 {{4},{0},{1,6,4,2,5,3,1,6,4,2,5,3},{5}},
											 {{5},{0},{1,6,5,3,2,4,1,6,5,3,2,4},{6}},
											 {{6},{0},{1,6,5,4,3,2,1,6,5,4,3,2},{7}}};
		
	//main driver
	public static void main(String[] args) {
		
		//initialize starting positions
		//all tiles start at 1 at 1 o'clock position
		//in decending order
		
		Tally game = new Tally();//makes a tally object
		permutation("1234567", game);//starts the permutation and includes the game stats
		game.totals();//displays the results
	}
	
	public static void permutation(String str, Tally game) { 
	    permutation("", str, game); 
	}

	private static void permutation(String prefix, String str, Tally game) {
		//frankenstein combination of a permutation recursion algorithm and 
		//what I've made to iterate the hex rotations
	    int n = str.length();
	    if (n == 0){//if successfully permutated. Should run 7! times.
	    	
	    	//creates another 3d array to 
	    	int pos[][][] = new int[][][]{{{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
  										  {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										  {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										  {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										  {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										  {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}},
										  {{0},{0},{0,0,0,0,0,0,0,0,0,0,0,0},{0}}};
			
			//This puts the hexes in order of the permutation by copying the content
			//once it decides where it needs to go.
			for(int z = 0; z<7; z++){
				for(int y = 0;y<7; y++){
					if(hex[y][3][0] == Character.getNumericValue((prefix.charAt(z)))){
						pos[z][0][0] = hex[y][0][0];
						pos[z][1][0] = hex[y][1][0];
						for(int q = 0; q < 12; q++){
							pos[z][2][q] = hex[y][2][q];
						}
						pos[z][3][0] = hex[y][3][0];

					}
				}	
			}
			
			//This part of the code rotates and checks a hex 6 times. It does so
			//6^7 times totally.
			{
			do{	do{ do{ do{ do{	do{	do{
				
				//The truth tree. If all solutions are true, then it must be a real solution.
				if(pos[0][2][1 + pos[0][1][0]] == pos[1][2][4 + pos[1][1][0]]){
					if(pos[0][2][2 + pos[0][1][0]] == pos[3][2][5 + pos[3][1][0]]){
						if(pos[0][2][3 + pos[0][1][0]] == pos[2][2][0 + pos[2][1][0]]){
							if(pos[1][2][2 + pos[1][1][0]] == pos[4][2][5 + pos[4][1][0]]){
								if(pos[1][2][3 + pos[1][1][0]] == pos[3][2][0 + pos[3][1][0]]){
									if(pos[2][2][1 + pos[2][1][0]] == pos[3][2][4 + pos[3][1][0]]){
										if(pos[2][2][2 + pos[2][1][0]] == pos[5][2][5 + pos[5][1][0]]){
											if(pos[3][2][1 + pos[3][1][0]] == pos[4][2][4 + pos[4][1][0]]){
												if(pos[3][2][3 + pos[3][1][0]] == pos[5][2][0 + pos[5][1][0]]){
													if(pos[3][2][2 + pos[3][1][0]] == pos[6][2][5 + pos[6][1][0]]){
														if(pos[4][2][3 + pos[4][1][0]] == pos[6][2][0 + pos[6][1][0]]){
															if(pos[5][2][1 + pos[5][1][0]] == pos[6][2][4 + pos[6][1][0]]){
																game.addSolutions(pos);
															}}}}}}}}}}}}
			pos[0][1][0]++;//rotates the first hex (aka hex0) 1 time for 6 times
			game.addCounter();
			if(pos[0][1][0] > 5)
				pos[0][1][0] = 0;
			}while(pos[0][1][0] != 0);
			pos[1][1][0]++;//rotates the second hex 1 time
			if(pos[1][1][0] > 5)
				pos[1][1][0] = 0;
			}while(pos[1][1][0] != 0);
			pos[2][1][0]++;//etc...
			if(pos[2][1][0] > 5)
				pos[2][1][0] = 0;
			}while(pos[2][1][0] != 0);
			pos[3][1][0]++;
			if(pos[3][1][0] > 5)
				pos[3][1][0] = 0;
			}while(pos[3][1][0] != 0);
			pos[4][1][0]++;
			if(pos[4][1][0] > 5)
				pos[4][1][0] = 0;
			}while(pos[4][1][0] != 0);
			pos[5][1][0]++;
			if(pos[5][1][0] > 5)
				pos[5][1][0] = 0;
			}while(pos[5][1][0] != 0);
			pos[6][1][0]++;
			if(pos[6][1][0] > 5)
				pos[6][1][0] = 0;
			}while(pos[6][1][0] != 0);
			}
	    }
	    
	    else {//if it still needs to be permutated
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), game);
	    }
	}
}