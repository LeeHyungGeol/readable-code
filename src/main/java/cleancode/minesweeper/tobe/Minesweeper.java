package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.config.GameConfig;
import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;
import cleancode.minesweeper.tobe.positoion.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

public class Minesweeper implements GameInitializable, GameRunnable {

	// BOARD 도 하는 일이 너무 많고 중요하기 때문에 Minesweeper 클래스 내부에 상수로 두기에는 너무 책임이 과도하다.
	// 이렇게 GameBoard 클래스를 두면 Minesweeper 입장에서는 Cell[][] 이중배열에 대해서는 모른다.
	// 객체로 추상화가 되었고, 데이터 구조에 대한 것은 캐슐화가 되었기 때문이다.
	private final GameBoard gameBoard;
	// SRP: cellInput 이라는 사용자의 입력을 받아서 rowIndex, colIndex 로 변환하는 역할을 하는 또 하나의 클래스로 볼 수 있지 않을까?

	// 게임이 진행되는 핵심 로직들과 사용자 입출력에 대한 로직 책임을 분리한다.
	// DIP: InputHandler, OutputHandler 는 이제 Console 에 관한 것은 모른다. 인터페이스만 의존하고 있다.
	// 구현체가 변경되어도 Minesweeper 클래스는 영향을 받지 않는다.
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

	public Minesweeper(GameConfig gameConfig) {
		gameBoard = new GameBoard(gameConfig.getGameLevel());
		this.inputHandler = gameConfig.getInputHandler();
		this.outputHandler = gameConfig.getOutputHandler();
	}

	@Override
	public void initialize() {
		gameBoard.initializeGame();
	}

	@Override
	public void run() {
		outputHandler.showGameStartComments();

		while (true) {
			try {
				outputHandler.showBoard(gameBoard);

				if (doesUserWinTheGame()) {
					outputHandler.showGameWinningComment();
					break;
				}
				if (doesUserLoseTheGame()) {
					outputHandler.showGameLosingComment();
					break;
				}

				CellPosition cellPosition = getCellInputFromUser();
				UserAction userActionInput = getUserActionInputFromUser();
				actOnCell(cellPosition, userActionInput);
			} catch (GameException e) {
				outputHandler.showExceptionMessage(e);
			} catch (Exception e) {
				outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
			}
		}
	}

	private void actOnCell(CellPosition cellPosition, UserAction userActionInput) {
		if (doesUserChooseToPlantFlag(userActionInput)) {
			gameBoard.flagAt(cellPosition);
			checkIfGameIsOver();
			return;
		}

		if (doesUserChooseToOpenCell(userActionInput)) {
			if (gameBoard.isLandMineCellAt(cellPosition)) {
				gameBoard.openAt(cellPosition);
				changeGameStatusToLose();
				return;
			}

			gameBoard.openSurroundedCells(cellPosition);
			checkIfGameIsOver();
			return;
		}
		throw new GameException("잘못된 번호를 선택하셨습니다.");
	}

	private void changeGameStatusToLose() {
		gameStatus = -1;
	}

	private boolean doesUserChooseToOpenCell(UserAction userAction) {
		return userAction == UserAction.OPEN;
	}

	private boolean doesUserChooseToPlantFlag(UserAction userAction) {
		return userAction == UserAction.FLAG;
	}

	private UserAction getUserActionInputFromUser() {
		outputHandler.showCommentForUserAction();
		return inputHandler.getUserActionFromUser();
	}

	private CellPosition getCellInputFromUser() {
		outputHandler.showCommentForSelectingCell();
		CellPosition cellPosition = inputHandler.getCellPositionFromUser();
		if (gameBoard.isInvalidCellPosition(cellPosition)) {
			throw new GameException("잘못된 좌표를 선택하셨습니다.");
		}

		return cellPosition;
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
