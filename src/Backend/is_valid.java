package Backend;

public class is_valid {
	int row;
	int col;
	int num;
	int box_x;
	int box_y;

	public is_valid(int r, int c, int num){
		this.row = r;
		this.col = c;
		this.num = num;
	}
	public boolean check_validity() {
		for (int i = 0; i < 9; i++) {
			if (Global.board[i][col] == num) {
				return false;
			}
		}
		for (int j = 0; j < 9; j++) {
			if (Global.board[row][j] == num) {
				return false;
			}
		}

		box_x = col/3;
		box_y = row/3;
		for (int k = box_x*3; k < box_x*3+3; k++) {
			for (int l = box_y*3; l < box_y*3+3; l++) {
				if (Global.board[l][k] == num) {
					return false;
				}
			}
		}
		return true;
	}
}