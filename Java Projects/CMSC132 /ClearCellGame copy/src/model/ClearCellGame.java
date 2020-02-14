package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game  {
	private int rows;
	private int cols;
	private int strat;
	private int score;
	private Random rand = new Random();


	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		// TODO Auto-generated constructor stub

		for(int i = 0; i<maxRows; i++){
			for(int j = 0; j<maxCols; j++){
				this.setBoardCell(i,j,BoardCell.EMPTY);
			}
		}
		this.rand = random;
		this.strat = strategy;
		this.score = 0;

	}


	@Override
	public boolean isGameOver() {
		//BoardCell[] temp = new BoardCell[this.rows];
		///for(int i < 0; i<this.rows; i++)

		int counter=0;
		for(int i =0; i<this.getMaxCols(); i++) {

			if(this.board[this.getMaxRows()-1][i].equals(BoardCell.EMPTY)) {
				counter++;
			}
		}
		if(counter == this.getMaxCols()) {
			return false;
		}

		return true;
	}

	@Override
	public int getScore() {

		return this.score;
	}



	public void nextAnimationStep() {
		if (this.isGameOver() == false) {
			for (int row = this.getMaxRows() - 2; row >= 0; row--) {
				for (int col = this.getMaxCols()-1; col >= 0; col--) {
					BoardCell tempCell = this.getBoardCell(row, col);
					this.setBoardCell(row + 1, col , tempCell);
				}
			}
			for (int i = 0; i < this.getMaxCols(); i++) {
				this.board[0][i] = BoardCell
						.getNonEmptyRandomBoardCell(rand);
			}
		}
	}
	//System.out.println("rows = " + this.rows+ "cols = " +this.cols)


	@Override
	public void processCell(int rowIndex, int colIndex) {
		int rowDown = rowIndex+1;
		int rowUp = rowIndex-1;
		int colRight = colIndex+1;
		int colLeft = colIndex-1;

		if(this.getBoardCell(rowIndex, colIndex).equals(BoardCell.EMPTY)){
			return;
		}
		//check cell to the top right
		if( rowUp >= 0&&colRight < this.getMaxCols()) {
			if(board[rowUp][colRight]==board[rowIndex][colIndex]){
				//	for(int row = rowUp; row >=0 ; row--) {
				int tempRow = rowIndex -1;
				for(int col = colRight; col <this.getMaxCols(); col++) {
					if((tempRow >=0) &&board[tempRow][col]==board[rowIndex][colIndex]){

						this.setBoardCell(tempRow, col, BoardCell.EMPTY);
						//colRight++;
						//rowUp--;
						tempRow--;
						score++;
					}else {
						break;
					}
					//break;

					//	}
				}
			}
		}
		//check cell to the top left
		if( rowUp >= 0&&colLeft >= 0) {
			if(board[rowUp][colLeft]==board[rowIndex][colIndex]){
				//for(int row = rowUp; row >= 0; row--) {
				int tempRow = rowIndex -1;
				for(int col = colLeft; col >=0; col-- ) {
					if((tempRow >=0) && board[tempRow][col]==board[rowIndex][colIndex] ){

						this.setBoardCell(tempRow, col, BoardCell.EMPTY);
						//colLeft--;
						//rowUp--;
						tempRow--;
						score++;
					}else {
						break;
					}
				}
				//break;
				//}
			}
		}
		//check cell to the bottom left
		if( rowDown<this.getMaxRows()&&colLeft >= 0) {
			if(board[rowDown][colLeft]==board[rowIndex][colIndex]){
				int tempRow = rowIndex +1;
				//for(int row = rowDown; row < this.getMaxRows(); row++) {
				for(int col = colLeft; col >=0; col-- ) {
					if((tempRow < this.getMaxRows())&&board[tempRow][col]==board[rowIndex][colIndex]){

						this.setBoardCell(tempRow, col, BoardCell.EMPTY);
						//colLeft--;
						//rowDown++;
						tempRow++;
						score++;
					}else {
						break;
					}
				}
				//}
			}
			//break;
		}
		//check cell to the bottom right
		if( rowDown<this.getMaxRows()&&colRight < this.getMaxCols()) {
			if(board[rowDown][colRight]==board[rowIndex][colIndex]){
				//for(int row = rowDown; row < this.getMaxRows(); row++) {
				int tempRow = rowIndex +1;
				for(int col = colRight; col <this.getMaxCols(); col++ ) {
					if(tempRow < this.getMaxRows()&&board[tempRow][col]==board[rowIndex][colIndex]){

						this.setBoardCell(tempRow, col, BoardCell.EMPTY);
						//colRight++;
						//rowDown++;
						score++;
						tempRow++;
					}else {
						break;
					}
				}
				//break;
				//}
			}
		}
		//check above current block
		if( rowDown < this.getMaxRows()) {
			if(board[rowDown][colIndex]==board[rowIndex][colIndex]){
				for(int i = rowDown; i<this.getMaxRows(); i++) {
					if(board[i][colIndex]==board[rowIndex][colIndex]){

						this.setBoardCell(i, colIndex, BoardCell.EMPTY);
						//rowDown++;
						score++;
					}else {
						break;
					}
				}
				//	break;

			}
		}
		//check cell below
		if( rowUp >= 0) {
			if(board[rowUp][colIndex]==board[rowIndex][colIndex]){
				for(int row = rowUp; row>= 0; row--) {	
					if(board[row][colIndex]==board[rowIndex][colIndex]){

						this.setBoardCell(row, colIndex, BoardCell.EMPTY);
						//rowUp--;
						score++;
					}else {
						break;
					}
				}
				//break;
				//fuck column game
			}
		}
		//check cell to the right
		if( colLeft >= 0) {
			if(board[rowIndex][colLeft]==board[rowIndex][colIndex]){

				for(int col =colLeft; col >= 0; col--) {
					if(board[rowIndex][col]==board[rowIndex][colIndex]){

						this.setBoardCell(rowIndex, col, BoardCell.EMPTY);
						//colLeft--;
						score++;
					}else {
						break;
					}

				}


			}
		}
		//check cell to the left
		if( colRight < this.getMaxCols()) {
			if(board[rowIndex][colRight]==board[rowIndex][colIndex]){
				for(int col = colRight; col < this.getMaxCols(); col++) {
					if(board[rowIndex][col]==board[rowIndex][colIndex]){

						this.setBoardCell(rowIndex, col, BoardCell.EMPTY);
						//	colRight++;
						score++;
					}else {
						break;
					}
				}
				//break;
			}
		}


		//turn .this cell empty
		this.setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
		score++;
		CollapseRow();

		//collapse row



	}
	public void CollapseRow() {

		for(int collapserow= this.getMaxRows()-1; collapserow>=0; collapserow--){
			if(RowEmpty(collapserow) == true){
				for(int rowShift = collapserow+1; rowShift<this.getMaxRows();rowShift++){
					for(int col = 0; col<this.getMaxCols(); col++){
						board[rowShift-1][col] = board[rowShift][col];
					}
				}
			}
		}
	}
	public boolean RowEmpty(int row) {
		boolean flag = false;
		int counter = 0;
		for(int i = 0; i<this.getMaxCols(); i++) {
			if(this.getBoardCell(row, i).equals(BoardCell.EMPTY)) {
				counter++;
			}
			if(counter == this.getMaxCols()) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}


}
