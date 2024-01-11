package Sudoku;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainClass extends JPanel {
	public static void main(String[]args) {
	JFrame gui = new JFrame();
	drawing DC = new drawing();
	gui.add(DC);
	gui.setSize(800,800);
	gui.setVisible(true);
	gui.setTitle("Sudoku");
	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public static int[][] boardCreator(){
		//block1
		int  count = 0;
		int[][] board = new int[9][9];
		int [] block = new int[9];
		boolean numberChecker = false;
		Random r = new Random();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				count++;
				int randomNumber = r.nextInt(9) + 1;
				for(int m = 0; m < i*3+j; m++) {
					if (randomNumber == block[m]) {
						m=9;
						j--;
						numberChecker = true;
					}
				}	
				if(numberChecker == false) {
					board[i][j] = randomNumber;
					block[i*3+j] = randomNumber;
				}
				if(count == 1000) {
					return boardCreator();
				}
				numberChecker = false;
			}
		}

		//block2
		count = 0;
		block = new int[9];
		numberChecker = false;
		for(int i = 0; i < 3; i++) {
			for(int j = 3; j < 6; j++) {
				count++;
				int randomNumber = r.nextInt(9)+1;
				for(int m = 0; m < j; m++) {
					if(randomNumber == board[i][m]) {
						numberChecker = true;
						m=9;
						j--;
					}
				}
				for(int k = 0; k < i*3+j-3;k++) {
					if(randomNumber == block[k] && numberChecker == false) {
						k=9;
						j--;
						numberChecker = true;
					}
				}
				if(numberChecker == false){
					block[i*3+j-3] = randomNumber;
					board[i][j] = randomNumber;
				}
				if(count == 1000) {
					return boardCreator();
				}
				numberChecker = false;
			}
		}
	
	//block3
	count = 0;
	block = new int[9];
	numberChecker = false;
	for(int i = 0; i < 3; i++) {
	for(int j = 6; j < 9; j++) {
		count++;
		int randomNumber = r.nextInt(9)+1;
		for(int m = 0; m < j; m++) {
			if(randomNumber == board[i][m]) {
				numberChecker = true;
				m=9;
				j--;
			}
		}
		for(int k = 0; k < i*3+j-6;k++) {
			if(randomNumber == block[k] && numberChecker == false) {
				k=9;
				j--;
				numberChecker = true;
			}
		}
		if(numberChecker == false){
			block[i*3+j-6] = randomNumber;
			board[i][j] = randomNumber;
		}
		if(count == 1000) {
			return boardCreator();
		}
		numberChecker = false;
	}
}
	
	//block4
	count = 0;
	block = new int[9];
	numberChecker = false;	
	for(int i = 3; i < 6; i++) {
		for(int j = 0; j < 3;j++) {
			count++;
			int randomNumber = r.nextInt(9) + 1;
			for(int m = 0; m < i;m++) {
				if(randomNumber == board[m][j]) {
					m = i;
					j--;
					numberChecker = true;
				}
			}
			for(int k = 0;k < (i-3)*3+j;k++) {
				if(randomNumber == block[k] && numberChecker == false) {
					k = 9;
					j--;
					numberChecker = true;					
				}
			}
			if(numberChecker == false) {
				board[i][j] = randomNumber;
				block[(i-3)*3+j] = randomNumber;
			}
			if(count == 1000) {
				return boardCreator();
			}
			numberChecker = false;
		}
	}
	
	//block5
	count = 0;
	block = new int[9];
	numberChecker = false;	
	for(int i = 3; i < 6; i++) {
		for(int j = 3; j < 6;j++) {
			count++;
			int randomNumber = r.nextInt(9) + 1;
			for(int m = 0; m < i;m++) {
				if(randomNumber == board[m][j]) {
					m = i;
					j--;
					numberChecker = true;
				}
			}
			for(int n = 0; n < j; n++) {
				if(randomNumber == board[i][n] && numberChecker == false) {
					n = j;
					j--;
					numberChecker = true;
				}
			}
			for(int k = 0;k < (i-3)*3+j-3;k++) {
				if(randomNumber == block[k] && numberChecker == false) {
					k = 9;
					j--;
					numberChecker = true;					
				}
			}
			if(numberChecker == false) {
				board[i][j] = randomNumber;
				block[(i-3)*3+j-3] = randomNumber;
			}
			if(count == 1000) {
				return boardCreator();
			}
			numberChecker = false;
		}
	}
	
	//block 6
	count = 0;
	block = new int[9];
	numberChecker = false;	
	for(int i = 3; i < 6; i++) {
		for(int j = 6; j < 9;j++) {
			count++;
			int randomNumber = r.nextInt(9) + 1;
			for(int m = 0; m < i;m++) {
				if(randomNumber == board[m][j]) {
					m = i;
					j--;
					numberChecker = true;
				}
			}
			for(int n = 0; n < j; n++) {
				if(randomNumber == board[i][n] && numberChecker == false) {
					n = j;
					j--;
					numberChecker = true;
				}
			}
			for(int k = 0;k < (i-3)*3+j-6;k++) {
				if(randomNumber == block[k] && numberChecker == false) {
					k = 9;
					j--;
					numberChecker = true;					
				}
			}
			if(numberChecker == false) {
				board[i][j] = randomNumber;
				block[(i-3)*3+j-6] = randomNumber;
			}
			if(count == 1000) {
				return boardCreator();
			}
			numberChecker = false;
		}
	}
	
	//block7
	block = new int[9];
	numberChecker = false;	
	for(int i = 6; i < 9; i++) {
		for(int j = 0; j < 3;j++) {
			count++;
			int randomNumber = r.nextInt(9) + 1;
			for(int m = 0; m < i;m++) {
				if(randomNumber == board[m][j]) {
					m = i;
					j--;
					numberChecker = true;
				}
			}
			for(int k = 0;k < (i-6)*3+j;k++) {
				if(randomNumber == block[k] && numberChecker == false) {
					k = 9;
					j--;
					numberChecker = true;					
				}
			}
			if(numberChecker == false) {
				board[i][j] = randomNumber;
				block[(i-6)*3+j] = randomNumber;
			}
			if(count == 1000) {
				return boardCreator();
			}
			numberChecker = false;
		}
	}
	
	//block 8
	count = 0;
	block = new int[9];
	numberChecker = false;	
	for(int i = 6; i < 9; i++) {
		for(int j = 3; j < 6;j++) {
			count++;
			int randomNumber = r.nextInt(9) + 1;
			for(int m = 0; m < i;m++) {
				if(randomNumber == board[m][j]) {
					m = i;
					j--;
					numberChecker = true;
				}
			}
			for(int n = 0; n < j; n++) {
				if(randomNumber == board[i][n] && numberChecker == false) {
					n = j;
					j--;
					numberChecker = true;
				}
			}
			for(int k = 0;k < (i-6)*3+j-3;k++) {
				if(randomNumber == block[k] && numberChecker == false) {
					k = 9;
					j--;
					numberChecker = true;					
				}
			}
			if(numberChecker == false) {
				board[i][j] = randomNumber;
				block[(i-6)*3+j-3] = randomNumber;
			}
			if(count == 1000) {
				return boardCreator();
			}
			numberChecker = false;
		}
	}

	//block9
	count = 0;
	block = new int[9];
	numberChecker = false;	
	for(int i = 6; i < 9; i++) {
		for(int j = 6; j < 9;j++) {
			count++;
			int randomNumber = r.nextInt(9) + 1;
			for(int m = 0; m < i;m++) {
				if(randomNumber == board[m][j]) {
					m = i;
					j--;
					numberChecker = true;
				}
			}
			for(int n = 0; n < j; n++) {
				if(randomNumber == board[i][n] && numberChecker == false) {
					n = j;
					j--;
					numberChecker = true;
				}
			}
			for(int k = 0;k < (i-6)*3+j-6;k++) {
				if(randomNumber == block[k] && numberChecker == false) {
					k = 9;
					j--;
					numberChecker = true;					
				}
			}
			if(numberChecker == false) {
				board[i][j] = randomNumber;
				block[(i-6)*3+j-6] = randomNumber;
			}
			if(count == 1000) {
				return boardCreator();
			}
			numberChecker = false;
		}
	}
	
	for(int i = 0 ; i < board[0].length;i++) {
		for(int j = 0; j < board.length; j++) {
			System.out.print(board[i][j] + " ");
		}
		System.out.println();
	}	
		
		return board;
	}

	
	public static int[][] visibleBoard(int[][] list){
		System.out.println();
		Random r = new Random();
		int[][] visibleBoard = new int[9][9];
		for(int i = 0; i < visibleBoard.length; i++) {
			for(int j = 0; j < visibleBoard[0].length; j++) {
				visibleBoard[i][j] = list [i][j];
			}
		}
		int randomNumber;
		int tempK = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int[] randomList = new int[r.nextInt(5)+2];
				if(randomList.length > 0) {
					randomList[0] = r.nextInt(9);
				}
				for(int k = 0; k < randomList.length; k++) {
					randomNumber = r.nextInt(9);
					tempK = k;
					while(tempK > 0) {
						if(randomNumber == randomList[tempK-1]) {
							tempK = -1;
							k--;							
						}
						if(tempK != -1) {
							randomList[k] = randomNumber;
						}
						tempK--;
					}
				}
				for(int n = 0; n < randomList.length;n++) {
					visibleBoard[i*3+randomList[n]/3][j*3+randomList[n]%3] = 0;
				}
				
				
			}
		}
		for(int i = 0; i < visibleBoard.length;i++) {
			for(int k = 0; k < visibleBoard[0].length; k++) {
				System.out.print(visibleBoard[i][k] + " ");
			}
			System.out.println();
		}
		return visibleBoard;
		
	}
	public static boolean[][] state(int[][] visibleBoard){
		boolean[][] state = new boolean[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(visibleBoard[i][j] == 0) 
					state[i][j] = true;			
			}
		}
		return state;			
	}

}
