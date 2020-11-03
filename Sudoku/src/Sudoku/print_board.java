package Sudoku;

public class print_board {
	
	public print_board(){

	}
	
	public void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(Global.board[i][j]);
			}
			System.out.print("\n");
		}
	}
}
