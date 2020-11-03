import Sudoku.Generate;
import Sudoku.Global;
import Sudoku.is_empty;
import Sudoku.print_board;
import Sudoku.solve;
public class Sudoku {

	public static void main(String args[]) {
		Generate sol = new Generate();
		sol.gen();
		
		print_board pb = new print_board();
		pb.print();
		
		System.out.println("");
		
		Integer[] check_list = {1,2,3,4,5,6,7,8,9};
		solve solv = new solve(check_list);
		if (!solv.solve_sudoku())
			System.out.print("Unsolvable");
		
		pb.print();
	}
}
