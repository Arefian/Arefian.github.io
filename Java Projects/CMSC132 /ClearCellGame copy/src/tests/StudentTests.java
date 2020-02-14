package tests;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

public class StudentTests {
	@Test
	public void collapseCells() {
		int maxRows = 8, maxCols = 12, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
	   
		ccGame.setBoardWithColor(BoardCell.RED);
		ccGame.setRowWithColor(1, BoardCell.BLUE);
		ccGame.setRowWithColor(2, BoardCell.BLUE);
		ccGame.setRowWithColor(3, BoardCell.YELLOW);
		
		ccGame.setBoardCell(1, 2, BoardCell.YELLOW);
		ccGame.setBoardCell(1, 1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, 3, BoardCell.BLUE);

		ccGame.setBoardCell(1, 4, BoardCell.YELLOW);
		ccGame.setBoardCell(1, 5, BoardCell.YELLOW);
		ccGame.setBoardCell(1, 6, BoardCell.YELLOW);
		ccGame.setBoardCell(1, 7, BoardCell.YELLOW);

		
		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 4);
	
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		/*
		ccGame.processCell(1, maxCols - 1);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);*/
		
		System.out.println(answer);
		//assertTrue(TestsSupport.isCorrect("pubCollapseCells.txt", answer));
	}
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}
