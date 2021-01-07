package Backend;

public class is_empty {
	int[] empty = new int[2];
	
	public is_empty() {
		
	}
	
	public int[] find_if_empty(){
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Global.board[i][j] == 0) {
					empty[0] = i;
					empty[1] = j;
					return empty;
				}
			}
		}
		empty[0] = -1;
		empty[1] = -1;
		return empty;
	}
}
