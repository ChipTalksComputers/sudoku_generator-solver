package Backend;

import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generate {
	
	Integer[] values = {1,2,3,4,5,6,7,8,9};
	int r, p, q, temp, s, toss, counter = 0;
	public static int[][] answer = new int[9][9];
        public static int[][] question;
	
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
                        System.out.print(Global.board[i][j]);
                        answer[i][j] = Global.board[i][j];
                    }
                    System.out.println("");
                }
                
                int[][] tempBoard = new int[9][9];
                    for (int i = 0; i < Global.board.length; i++) {
                        System.arraycopy(Global.board[i], 0, tempBoard[i], 0, Global.board[i].length);
                    }
		int num;      
                        
		for (int i = 0; i<9; i++) {
                    for (int j = 0; j<9; j++) {
                        toss = rand.nextInt(2);
                            if (toss == 0){
                            for (int p = 0; p < tempBoard.length; p++) {
                                System.arraycopy(tempBoard[p], 0, Global.board[p], 0, tempBoard[p].length);
                            }   
                            num = Global.board[i][j];
                            //System.out.println(num);
                            Global.board[i][j] = 0;
                            solve tempsol = new solve(values);
                            if(tempsol.solve_generate(num, i, j) == false && counter<64){
                        
                                tempBoard[i][j] = 0;
                                counter++;
                            }
                        }
                    }
                }
                for (int p = 0; p < tempBoard.length; p++) {
                    System.arraycopy(tempBoard[p], 0, Global.board[p], 0, tempBoard[p].length);
                }
                for (int i = 0; i<9; i++) {
                    for (int j = 0; j<9; j++) {
                        System.out.print(tempBoard[i][j]);
                    }
                    System.out.println("");
                }
                question = tempBoard.clone();
        }
}
