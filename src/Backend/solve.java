package Backend;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class solve {

	is_empty is_mt;
	is_valid is_vd;
	int[] empty;
	int[] not_present = {-1, -1};
	Integer[] check_list;
	
	public solve(Integer[] check_list) {
		this.check_list = check_list;
	}
	
	public boolean solve_sudoku() {
		is_mt = new is_empty();
		empty = is_mt.find_if_empty();
		if (empty[0] == not_present[0] && empty[1] == not_present[1]) {
			return true;
		}
		else {
			int x = empty[0];
			int y = empty[1];			
			//System.out.println("Empty cell coords found"+x+y);
			for (int i=0; i<9; i++) {
                            if(check_list[i] != 0){
				is_vd = new is_valid(x, y, this.check_list[i]);
				if (is_vd.check_validity() == true) {
					Global.board[x][y] = this.check_list[i];
					//System.out.println(Global.board[0][0]);
					if (solve_sudoku() == true) {
						return true;
					}
					Global.board[x][y] = 0;
				}	
                            }
                        }
			List<Integer> list = Arrays.asList(check_list);
			Collections.shuffle(list);
			list.toArray(check_list);
		}
	return false;
	}
        
        public boolean solve_generate(int num, int r, int c){
        	is_mt = new is_empty();
		empty = is_mt.find_if_empty();
		if (empty[0] == not_present[0] && empty[1] == not_present[1]) {
			return true;
		}
		else {
			int x = empty[0];
			int y = empty[1];			
			//System.out.println("Empty cell coords found"+x+y);
			for (int i=0; i<9; i++) {
                            if(check_list[i] != 0 && !(check_list[i] == num && x==r && y==c)){
                                //System.out.println(check_list[i]);
				is_vd = new is_valid(x, y, this.check_list[i]);
				if (is_vd.check_validity() == true) {
					Global.board[x][y] = this.check_list[i];
					//System.out.println(Global.board[0][0]);
					if (solve_generate(num, r, c) == true) {
						return true;
					}
					Global.board[x][y] = 0;
				}	
                            }
                            //System.out.println("");
                        }
		}
	return false;
        }
        
    	public boolean solve_board() {
		is_mt = new is_empty();
		empty = is_mt.find_if_empty();
		if (empty[0] == not_present[0] && empty[1] == not_present[1]) {
			return true;
		}
		else {
			int x = empty[0];
			int y = empty[1];			
			//System.out.println("Empty cell coords found"+x+y);
			for (int i=0; i<9; i++) {
                            if(check_list[i] != 0){
				is_vd = new is_valid(x, y, this.check_list[i]);
				if (is_vd.check_validity() == true) {
					Global.board[x][y] = this.check_list[i];
					//System.out.println(Global.board[0][0]);
					if (solve_board() == true) {
						return true;
					}
					Global.board[x][y] = 0;
				}	
                            }
                        }
		}
	return false;
	}
}
