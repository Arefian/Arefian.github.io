package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;
	private int rows;
	private int cols;
	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		this.rows = maxRows;
		this.cols = maxCols;
		board = new BoardCell[maxRows][maxCols];
		this.setBoardWithColor(BoardCell.EMPTY);
		/*for( int i =0; i<maxRows; i++) {
			for (int j = 0; j<m; i++){
				board[i][j] = BoardCell.EMPTY;
			}
		}*/
	}

	public int getMaxRows() {
		return this.rows;
	}

	public int getMaxCols() {
		return this.cols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		board[rowIndex][colIndex] = boardCell;

	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return this.board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {

		for(int i=0; i<this.getMaxCols(); i++){
			this.setBoardCell(rowIndex, i, cell);
		}

	}

	/**
	 * Initializes column with the specified color.
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for (int i=0; i<this.getMaxRows(); i++){
			this.setBoardCell(i, colIndex, cell);
			}

	}

	/**
	 * Initializes the board with the specified color.
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for(int i =0; i<this.rows; i++) {
			for(int j=0; j<this.board[i].length; j++) {
				this.board[i][j]= cell;
			}
		}
	}	

	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the
	 * selected cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}