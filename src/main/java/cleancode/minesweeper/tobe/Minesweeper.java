package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
	public static final Scanner SCANNER = new Scanner(System.in);
	public static final int BOARD_ROW_SIZE = 8;
	public static final int BOARD_COL_SIZE = 10;
	public static final int LAND_MINE_COUNT = 10;

	// BOARD 도 하는 일이 너무 많고 중요하기 때문에 Minesweeper 클래스 내부에 상수로 두기에는 너무 책임이 과도하다.
	// 이렇게 GameBoard 클래스를 두면 Minesweeper 입장에서는 Cell[][] 이중배열에 대해서는 모른다.
	// 객체로 추상화가 되었고, 데이터 구조에 대한 것은 캐슐화가 되었기 때문이다.
	private static final GameBoard gameBoard = new GameBoard(BOARD_ROW_SIZE, BOARD_COL_SIZE);

	// 게임이 진행되는 핵심 로직들과 사용자 입출력에 대한 로직 책임을 분리한다.
	private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
	private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
	private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

	public void run() {
		consoleOutputHandler.showGameStartComments();
		gameBoard.initializeGame();
		while (true) {
			try {
				consoleOutputHandler.showBoard(gameBoard);

				if (doesUserWinTheGame()) {
					consoleOutputHandler.printGameWinningComment();
					break;
				}
				if (doesUserLoseTheGame()) {
					consoleOutputHandler.printGameLosingComment();
					break;
				}
				String cellInput = getCellInputFromUser();
				String userActionInput = getUserActionInputFromUser();
				actOnCell(cellInput, userActionInput);
			} catch (GameException e) {
				// print 할 때 AppException 에서 어떤걸 꺼내서 쓸지는 내부에서 알아서 결정할 것이고,
				// 예외 상황(exception 에 대한 메시지)에 대한 메시지를 출력하겠다는 이 메서드명을 봤을 때
				// 파라미터는 exception 자체를 넣어주는 것이 더 자연스럽지 않을까 한다.
				consoleOutputHandler.printExceptionMessage(e);
			} catch (Exception e) {
				consoleOutputHandler.printSimpleMessage("프로그램에 문제가 생겼습니다.");
			}
		}
	}

	private void actOnCell(String cellInput, String userActionInput) {
		int selectedColumnIndex = getSelectedColIndex(cellInput);
		int selectedRowIndex = getSelectedRowIndex(cellInput);

		if (doesUserChooseToPlantFlag(userActionInput)) {
			gameBoard.flag(selectedRowIndex, selectedColumnIndex);
			checkIfGameIsOver();
			return;
		}

		if (doesUserChooseToOpenCell(userActionInput)) {
			if (gameBoard.isLandMineCell(selectedRowIndex, selectedColumnIndex)) {
				gameBoard.open(selectedRowIndex, selectedColumnIndex);
				changeGameStatusToLose();
				return;
			}

			gameBoard.openSurroundedCells(selectedRowIndex, selectedColumnIndex);
			checkIfGameIsOver();
			return;
		}
		throw new GameException("잘못된 번호를 선택하셨습니다.");
	}

	private void changeGameStatusToLose() {
		gameStatus = -1;
	}

	private boolean doesUserChooseToOpenCell(String userActionInput) {
		return userActionInput.equals("1");
	}

	private boolean doesUserChooseToPlantFlag(String userActionInput) {
		return userActionInput.equals("2");
	}

	private int getSelectedRowIndex(String cellInput) {
		char cellInputRow = cellInput.charAt(1);
		return convertRowFrom(cellInputRow);
	}

	private int getSelectedColIndex(String cellInput) {
		char cellInputCol = cellInput.charAt(0);
		return convertColFrom(cellInputCol);
	}

	private String getUserActionInputFromUser() {
		consoleOutputHandler.printCommentForUserAction();
		return consoleInputHandler.getUserInput();
	}

	private String getCellInputFromUser() {
		consoleOutputHandler.printCommentForSelectingCell();
		return consoleInputHandler.getUserInput();
	}

	private boolean doesUserLoseTheGame() {
		return gameStatus == -1;
	}

	private boolean doesUserWinTheGame() {
		return gameStatus == 1;
	}

	private void checkIfGameIsOver() {
		if (gameBoard.isAllCellChecked()) {
			changeGameStatusToWin();
		}
	}

	private void changeGameStatusToWin() {
		gameStatus = 1;
	}


	private int convertRowFrom(char cellInputRow) {
		int rowIndex = Character.getNumericValue(cellInputRow) - 1;
		if (rowIndex >= BOARD_ROW_SIZE) {
			throw new GameException("잘못된 입력입니다.");
		}

		return rowIndex;
	}

	private int convertColFrom(char cellInputCol) {
		switch (cellInputCol) {
			case 'a':
				return 0;
			case 'b':
				return 1;
			case 'c':
				return 2;
			case 'd':
				return 3;
			case 'e':
				return 4;
			case 'f':
				return 5;
			case 'g':
				return 6;
			case 'h':
				return 7;
			case 'i':
				return 8;
			case 'j':
				return 9;
			default:
				throw new GameException("잘못된 입력입니다.");
		}
	}
}
