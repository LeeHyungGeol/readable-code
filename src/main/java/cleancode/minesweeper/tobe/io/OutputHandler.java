package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.GameBoard;
import cleancode.minesweeper.tobe.GameException;

public interface OutputHandler {

	// show, print 중에 show 는 추상적인 느낌이고, print 는 구체적인 느낌이 강하다.
	// print 는 console 에 print 한다는 느낌이 강하다.
	// 높은 추상화 레벨인 OutputHandler 입장에서는 print 보다는 show 가 더 낫겠다.

	void showGameStartComments();

	void showBoard(GameBoard board);


	void showPrintGameWinningComment();

	void showGameLosingComment();

	void showCommentForSelectingCell();

	void showCommentForUserAction();

	void showExceptionMessage(GameException e);

	void showSimpleMessage(String message);


//	void printGameWinningComment();

//	void printGameLosingComment();

//	void printCommentForSelectingCell();

//	void printCommentForUserAction();

//	void printExceptionMessage(GameException e);
//
//	void printSimpleMessage(String message);
}
