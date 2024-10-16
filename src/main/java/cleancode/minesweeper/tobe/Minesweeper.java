package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

public class Minesweeper implements GameInitializable, GameRunnable {

	// BOARD 도 하는 일이 너무 많고 중요하기 때문에 Minesweeper 클래스 내부에 상수로 두기에는 너무 책임이 과도하다.
	// 이렇게 GameBoard 클래스를 두면 Minesweeper 입장에서는 Cell[][] 이중배열에 대해서는 모른다.
	// 객체로 추상화가 되었고, 데이터 구조에 대한 것은 캐슐화가 되었기 때문이다.
	private final GameBoard gameBoard;
	// SRP: cellInput 이라는 사용자의 입력을 받아서 rowIndex, colIndex 로 변환하는 역할을 하는 또 하나의 클래스로 볼 수 있지 않을까?
	private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

	// 게임이 진행되는 핵심 로직들과 사용자 입출력에 대한 로직 책임을 분리한다.
	private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
	private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
	private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

	public Minesweeper(GameLevel gameLevel) {
		gameBoard = new GameBoard(gameLevel);
	}

	@Override
	public void initialize() {
		gameBoard.initializeGame();
	}

	@Override
	public void run() {
		consoleOutputHandler.showGameStartComments();
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
		int selectedColumnIndex = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
		int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

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
}
