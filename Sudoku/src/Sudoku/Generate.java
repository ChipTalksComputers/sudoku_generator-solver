package Sudoku;
import java.util.Random;
import Sudoku.solve;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generate {
	
	Integer[] values = {1,2,3,4,5,6,7,8,9};
	int r, p, q, temp, s, toss, counter;
	
	
	public Generate(){
		
	}
	public void gen() {
		
		List<Integer> list = Arrays.asList(values);
		Collections.shuffle(list);
		list.toArray(values);		
		solve sol = new solve(values);
		Random rand = new Random();
		sol.solve_sudoku();
		
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				toss = rand.nextInt(2);
				if (toss == 0 && counter <= 64) {
					//temp = Global.board[i][j];
					Global.board[i][j] = 0;
					counter ++;
				}
			}
		}
	}
}
